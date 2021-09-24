import numpy as np
import pandas as pd

from flask import Blueprint, make_response, jsonify, request
from flask_restx import Api, Resource

surveys = Blueprint("survey", __name__)
api = Api(surveys)

'''
  weight_category 행
  1. 외향성(사교성, 사람들과의 만남)
  2. 활동성(수치가 낮으면 정적/아니면 동적)
  3. 지적 탐구(학습)
  4. 흥미 추구(재미)
  5. 예술 관심(감성적)
  6. 손재주
  7. 끈기(반복적인 활동)
  8. 습득력(적응력)
  9. 편안함(안정감)
'''
# 각 카테고리별 성향 가중치
weight_category = np.array([
  [0, 0, 0, 0, 5, 1, 4, 0, 2, 0, 1, 1, 1, 0, 6, 2, 6, 7, 4, 6, 2, 2, 1, 2, 0, 0, 6, 1, 1, 3, 4, 4, 3, 5, 1, 1],
  [1, 1, 1, 1, 1, 0, 5, 4, 4, 0, 0, 4, 0, 1, 7, 5, 7, 8, 8, 8, 7, 8, 0, 0, 1, 1, 0, 7, 2, 4, 3, 5, 5, 2, 0, 0],
  [3, 4, 2, 3, 0, 2, 1, 1, 0, 0, 2, 0, 5, 4, 1, 7, 4, 1, 2, 3, 3, 4, 8, 1, 3, 6, 7, 3, 8, 5, 6, 7, 7, 8, 5, 8],
  [5, 7, 6, 8, 7, 5, 6, 5, 5, 0, 8, 5, 4, 8, 8, 4, 2, 6, 5, 7, 6, 7, 3, 8, 8, 3, 4, 2, 6, 6, 5, 3, 2, 4, 6, 5],
  [6, 5, 5, 5, 8, 4, 8, 7, 8, 0, 5, 8, 2, 6, 5, 3, 5, 0, 3, 2, 1, 1, 7, 7, 2, 5, 5, 6, 0, 2, 1, 0, 1, 0, 8, 3],
  [7, 8, 7, 7, 6, 7, 7, 8, 7, 0, 7, 7, 8, 7, 0, 1, 3, 2, 0, 0, 0, 0, 2, 6, 4, 2, 3, 8, 3, 1, 2, 2, 4, 1, 7, 2],
  [8, 6, 8, 6, 4, 8, 3, 3, 6, 0, 3, 2, 6, 3, 3, 6, 1, 4, 6, 5, 8, 6, 6, 3, 6, 7, 2, 5, 7, 7, 7, 6, 6, 7, 3, 7],
  [2, 2, 3, 4, 2, 3, 2, 2, 1, 0, 4, 6, 7, 2, 4, 8, 8, 5, 7, 1, 5, 5, 5, 4, 7, 4, 1, 4, 5, 8, 8, 8, 8, 6, 4, 6],
  [4, 3, 4, 5, 3, 6, 0, 6, 3, 0, 6, 3, 3, 5, 2, 0, 0, 3, 1, 4, 4, 3, 4, 5, 5, 8, 8, 0, 4, 0, 0, 1, 0, 3, 2, 4]
])

@surveys.route("", methods=["POST"])
def survey():
  survey_result = list()
  user_survey = np.array(request.json['survey_data'])

  # 설문 결과 가중치와 카테고리 가중치 간 행렬 계산
  matrix = np.dot(user_survey, weight_category)
  matrix_index = np.zeros(shape=(36,2))
  
  # 인덱스 부여하고 상위 3개 카테고리 뽑기
  for i, v in enumerate(matrix):
    matrix_index[i] = [i+1, v]
  matrix_index = sorted(matrix_index, key=lambda k : -k[1])[:3]
  
  for _ in matrix_index:
    survey_result.append(int(_[0]))
  
  return jsonify(survey_result)