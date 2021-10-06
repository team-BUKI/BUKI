from sqlalchemy.sql.expression import true, func
import pandas as pd

from flask import Blueprint, make_response, jsonify, request
from flask_restx import Api, Resource
from sqlalchemy import and_
from flask_sqlalchemy import SQLAlchemy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from pandas import DataFrame

from models.HobbyClass import HobbyClass
from models.InterestCategory import InterestCategory
from models.InterestHobbyClass import InterestHobbyClass
from models.SmallCategory import SmallCategory
from models.ClickedHobbyClass import ClickedHobbyClass

recommendation = Blueprint("recommendation", __name__)
api = Api(recommendation)
db = SQLAlchemy()

def get_hobby_class_list():
  hobby_class_list = {}
  hobby_class_list['hobby_class_id'] = [_.id for _ in HobbyClass.query.all()]

  return DataFrame(hobby_class_list)

def get_click_log():
  click_log = {}
  click_log['count'] = []
  click_log['user_id'] = []
  click_log['hobby_class_id'] = []

  for _ in ClickedHobbyClass.query.all():
    click_log['count'].append(_.count)
    click_log['user_id'].append(_.user_id)
    click_log['hobby_class_id'].append(_.hobby_class_id)

  return DataFrame(click_log)

def get_hobby_class_info(userId, hobby_class_ids):
  '''
  클래스 id를 받아서 해당 클래스 정보 저장
  '''
  recom_hobby_classes = list()
  
  # 클래스 정보 가져오기 
  for id in hobby_class_ids:
    hobby = HobbyClass.query.filter_by(id = id).first()
    # hobby_dict = HobbyClass.as_dict(hobby)
    hobby_dict = {}
    hobby_dict['id'] = hobby.id
    hobby_dict['title'] = hobby.title
    hobby_dict['type'] = hobby.type
    hobby_dict['site'] = hobby.site
    hobby_dict['siteUrl'] = hobby.site_url
    hobby_dict['price'] = hobby.price
    hobby_dict['likeCnt'] = hobby.like_cnt
    hobby_dict['imageUrl'] = hobby.image_url
    hobby_dict['sidoId'] = hobby.sido_id
    hobby_dict['sigunguId'] = hobby.sigungu_id
    hobby_dict['bigcategoryId'] = hobby.big_category_id
    hobby_dict['smallcategoryId'] = hobby.small_category_id

    # 추천된 클래스가 현재 관심 클래스로 등록되어있는지 판별
    isExist = InterestHobbyClass.query.filter(and_(InterestHobbyClass.user_id == userId, InterestHobbyClass.hobby_class_id == id)).first()
    if isExist:
      hobby_dict['interest'] = True
    else:
      hobby_dict['interest'] = False

    recom_hobby_classes.append(hobby_dict)

  return recom_hobby_classes

def get_user_category_cosine_sim():
  '''
  사용자들의 관심 카테고리를 비교하여 유사도 계산
  '''
  user_category_columns = ("user_id", "categories")
  user_categories = []

  # 관심 카테고리를 1개 이상 저장한 user_id 불러오기
  user_list = [_.user_id for _ in InterestCategory.query.with_entities(InterestCategory.user_id).group_by(InterestCategory.user_id)]

  # 각 유저 별로 관심 카테고리 이름 묶음을 저장
  for user_id in user_list:
    categories = [_.name.replace(' · ', '').replace(' ', '') for _ in InterestCategory.query\
    .join(SmallCategory, InterestCategory.small_category_id == SmallCategory.id)\
    .add_columns(SmallCategory.name)\
    .filter(InterestCategory.user_id == user_id)\
    .all()]
    user_categories.append([user_id, categories])

  # user_id, categories 열의 dataframe 생성
  user_category_data = pd.DataFrame(data = user_categories, columns = user_category_columns)
  # 배열 형식의 관심 카테고리 묶음을 공백을 사이에 두고 이어붙인 string 형태로 전처리 
  user_category_data['categories'] = user_category_data['categories'].apply(lambda x : " ".join(x))

  # TF-IDF 벡터화 : 카테고리들의 묶음을 tfidf vector로 생성
  tfidf_vector = TfidfVectorizer()
  tfidf_matrix = tfidf_vector.fit_transform(user_category_data['categories']).toarray()
  tfidf_matrix_feature = tfidf_vector.get_feature_names()

  # 데이터프레임 변환
  tfidf_matrix = pd.DataFrame(tfidf_matrix, columns=tfidf_matrix_feature, index = user_category_data['user_id'])
 
  # 코사인 유사도 구하기 
  cosine_sim = cosine_similarity(tfidf_matrix)
  cosine_sim_df = pd.DataFrame(cosine_sim, index = user_category_data['user_id'], columns = user_category_data['user_id'])
  return cosine_sim_df, user_category_data

def recommend_by_categories(userId, matrix, user_category_data):
  '''
  사용자 관심 카테고리 기반 가장 유사한 성향의 유저 관심 클래스 추천
  '''
  # 가장 유사한 성향의 userId 도출
  recom_idx = matrix.loc[:, int(userId)].values.reshape(1, -1).argsort()[:, ::-1].flatten()[1:2]
  # 가장 유사한 성향의 유저와의 유사도가 0.5 이상일 때만 추천
  if matrix.loc[int(userId)].sort_values(ascending=False).values[1] > 0:
    recom_user_id = user_category_data.iloc[recom_idx, :].user_id.values

    # 유사한 성향을 가진 userId가 저장한 관심 클래스 id 가져오기
    recom_hobby_class_ids = [_.hobby_class_id for _ in InterestHobbyClass.query.filter_by(user_id = recom_user_id[0]).order_by(InterestHobbyClass.id).limit(10)]    
    return recom_hobby_class_ids
  else:
    return None

def recommend_by_click_log(userId):
  '''
  사용자별, 클래스별 클릭 로그에 따른 협업필터링 적용, 클래스 추천
  '''

  recom_hobby_class_ids = []
  # 클래스 id 가져오기
  class_data = get_hobby_class_list()
  # 클릭 로그 가져오기
  click_data = get_click_log()

  # 클래스 id 기반 각 유저별 클릭 수
  user_class_click = pd.merge(click_data, class_data, on = 'hobby_class_id')

  # item based 이므로 인덱스를 클래스 id로 설정한 pivot table 생성
  class_user_click = user_class_click.pivot_table('count', index='hobby_class_id', columns='user_id')
  # user_class_click = user_class_click.pivot_table('count', index='user_id', columns='hobby_class_id')

  # item based 협업 필터링 진행
  # NaN 값을 0으로 바꿔주기
  class_user_click.fillna(0, inplace=True)

  # 아이템 기반 유사도 측정
  item_based_collabor = cosine_similarity(class_user_click)
  item_based_collabor = pd.DataFrame(data = item_based_collabor, index = class_user_click.index, columns = class_user_click.index)

  # 가장 많이 클릭한 클래스, 클릭 수가 동일하다면 최근에 클릭한 클래스 순으로 하나 추출
  clicked_class = ClickedHobbyClass.query.filter_by(user_id = userId).order_by(ClickedHobbyClass.count.desc(), ClickedHobbyClass.date.desc()).first()
  # 해당 클래스와 유사한 클래스들 10개까지 추출
  item_based_collabor_by_id = item_based_collabor[clicked_class.hobby_class_id].sort_values(ascending=False)[:10]
  
  for (idx, val) in zip(item_based_collabor_by_id.index, item_based_collabor_by_id):
    isExist = InterestHobbyClass.query.filter(and_(InterestHobbyClass.user_id == userId, InterestHobbyClass.hobby_class_id == idx)).first()
    if isExist is None:
      recom_hobby_class_ids.append(idx)

  return recom_hobby_class_ids

def recommend_by_random():
  '''
  랜덤으로 클래스 15개 추천
  '''
  recom_hobby_class_ids = [_.id for _ in HobbyClass.query.order_by(func.rand()).limit(15)]
  return recom_hobby_class_ids

@recommendation.route("/<user_id>", methods=["GET"])
def recommend(user_id):
  recom_classes = []
  recom_classes_by_categories = []
  recom_classes_by_click = []

  # 본인 관심 카테고리에 하나 이상 저장되어 있어야 추천 가능
  isInterestExist = InterestCategory.query.filter_by(user_id = user_id).first()
  if isInterestExist is not None:
    matrix, data = get_user_category_cosine_sim()
    recom_classes_by_categories = recommend_by_categories(user_id, matrix, data)

  # 클릭 로그가 하나 이상 저장되어 있어야 추천 가능
  isClickExist = ClickedHobbyClass.query.filter_by(user_id = user_id).first()
  if isClickExist is not None:
    recom_classes_by_click = recommend_by_click_log(user_id)

  # 랜덤으로 15개 클래스 추천
  recom_classes_by_random = recommend_by_random()

  if recom_classes_by_categories is not None:
    recom_classes.extend(recom_classes_by_categories)
  if recom_classes_by_click is not None:
    recom_classes.extend(recom_classes_by_click)
  
  # 활동 데이터 기반 추천 클래스 수가 10개 미만이면 랜덤 클래스 추천으로 총 15개까지 채우기
  if len(recom_classes) < 1:
    recom_classes.extend(recom_classes_by_random)

  # 중복 추천 클래스 제거
  recom_classes = set(recom_classes)
  recom_classes = get_hobby_class_info(user_id, recom_classes)

  return jsonify(recom_classes)