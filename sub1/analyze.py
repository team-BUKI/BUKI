# -*- coding: cp949 -*-
from parse import load_dataframes
import pandas as pd
import shutil


def sort_stores_by_score(dataframes, n=20, min_reviews=30):
    """
    Req. 1-2-1 �� �������� ��� ������ ����Ͽ� ���� ������ ������ ������ `n`���� �������� �����Ͽ� �����մϴ�
    Req. 1-2-2 ���� ������ `min_reviews` �̸��� �������� �����մϴ�.
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )
 
    scores_group = stores_reviews.groupby(["store", "store_name"])
    #pd.set_option('display.max_columns', None)
    #print(scores_group.head())
    temp = scores_group.filter(lambda g: len(g) > min_reviews).groupby(["store", "store_name"])
    #print(temp)

    scores = temp.mean() # ���ڵ��� ��� ��
#    print(scores.head())

    df_sorted_by_values = scores.sort_values(by="score", ascending=False) #����������� ����
 #   print(df_sorted_by_values.head())
    
    return df_sorted_by_values.head(n=n).reset_index()


def get_most_reviewed_stores(dataframes, n=20):
    """
    Req. 1-2-3 ���� ���� ���並 ���� `n`���� �������� �����Ͽ� �����մϴ�
    
    """
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )

    
    raise NotImplementedError


def get_most_active_users(dataframes, n=20):
    """
    Req. 1-2-4 ���� ���� ���並 �ۼ��� `n`���� ������ �����Ͽ� �����մϴ�.
    """
    raise NotImplementedError


def main():
    data = load_dataframes()

    term_w = shutil.get_terminal_size()[0] - 1
    separater = "-" * term_w

    stores_most_scored = sort_stores_by_score(data)

    print("[�ְ� ���� ������]")
    print(f"{separater}\n")
    for i, store in stores_most_scored.iterrows():
        print(
            "{rank}��: {store}({score}��)".format(
                rank=i + 1, store=store.store_name, score=store.score
            )
        )
    print(f"\n{separater}\n\n")


if __name__ == "__main__":
    main()
