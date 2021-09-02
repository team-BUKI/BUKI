# -*- coding: cp949 -*-
import json
import pandas as pd
import os
import shutil

DATA_DIR = "../data"
#json ������ �а�
DATA_FILE = os.path.join(DATA_DIR, "data.json")
#pandas df�� pkl�� ��ȯ�ϴ� �Լ�
DUMP_FILE = os.path.join(DATA_DIR, "dump.pkl")

store_columns = (
    "id",  # ������ ������ȣ
    "store_name",  # ������ �̸�
    "branch",  # ������ ���� ����
    "area",  # ������ ��ġ
    "tel",  # ������ ��ȣ
    "address",  # ������ �ּ�
    "latitude",  # ������ ����
    "longitude",  # ������ �浵
    "category",  # ������ ī�װ�
)

review_columns = (
    "id",  # ���� ������ȣ
    "store",  # ������ ������ȣ
    "user",  # ���� ������ȣ
    "score",  # ����
    "content",  # ���� ����
    "reg_time",  # ���� ��� �ð�
)

menu_columns = (
    "store", # ������ ������ȣ
    "menu_name", # �޴� �̸�
    "price" # ����
    )




def import_data(data_path=DATA_FILE):
    """
    Req. 1-1-1 ������ ������ ������ �о Pandas DataFrame ���·� �����մϴ�
    """

    try:
        with open(data_path, encoding="utf-8") as f:
            data = json.loads(f.read())
    except FileNotFoundError as e:
        print(f"`{data_path}` �� �������� �ʽ��ϴ�.")
        exit(1)

    stores = []  # ������ ���̺�
    reviews = []  # ���� ���̺�
    menus = [] # �޴� ���̺�


    for d in data:

        categories = [c["category"] for c in d["category_list"]]

        stores.append(
            [
                d["id"],
                d["name"],
                d["branch"],
                d["area"],
                d["tel"],
                d["address"],
                d["latitude"],
                d["longitude"],
                "|".join(categories),
            ]
        )

        for review in d["review_list"]:
            r = review["review_info"]
            u = review["writer_info"]

            reviews.append(
                [r["id"], d["id"], u["id"], r["score"], r["content"], r["reg_time"]]
            )

        for menu in d["menu_list"]:
            menus.append(
                [d["id"], menu["menu"], menu["price"]]
            )
        

    store_frame = pd.DataFrame(data=stores, columns=store_columns)
    review_frame = pd.DataFrame(data=reviews, columns=review_columns)
    menu_frame = pd.DataFrame(data=menus, columns=menu_columns)

    return {"stores": store_frame, "reviews": review_frame, "menus" : menu_frame}


def dump_dataframes(dataframes):
    pd.to_pickle(dataframes, DUMP_FILE)


def load_dataframes():
    return pd.read_pickle(DUMP_FILE)


def main():

    print("[*] Parsing data...")
    data = import_data()
    print("[+] Done")

    print("[*] Dumping data...")
    dump_dataframes(data)
    print("[+] Done\n")

    data = load_dataframes()

    term_w = shutil.get_terminal_size()[0] - 1
    separater = "-" * term_w

    print("[������]")
    print(f"{separater}\n")
    print(data["stores"].head())
    print(f"\n{separater}\n\n")

    print("[����]")
    print(f"{separater}\n")
    print(data["reviews"].head())
    print(f"\n{separater}\n\n")

    print("[�޴�]")
    print(f"{separater}\n")
    print(data["menus"].head())
    print(f"\n{separater}\n\n")


if __name__ == "__main__":
    main()
