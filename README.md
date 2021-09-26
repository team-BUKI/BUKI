# 🔮 부키(BUKI)

<br>

<img src="images/buki_banner.png" alt="Buki Banner" width="500px"/>

> 취미 추천 및 기록 서비스, 부키(BUKI)

<br>

## 🧑‍💻 팀원 및 역할

- 원지연 : `Front-end` , `Project Manager`
- 김나영 : `Back-end`
- 박선아 : `Back-end`
- 유원선 : `Back-end`
- 최은송 : `Front-end`

<br>

## 📆 프로젝트 개요

- **진행 기간:** 2021.08.30 ~ 2021.10.08  `진행 중 🏃‍♀️`
- 자신만의 부캐를 만들고 싶은 사람들을 위한 취미 추천 및 기록 서비스
- **주요 기능**
  - 나의 취향에 맞는 취미 추천
  - 취미생활을 일기로 기록
  - 취미에 따른 나의 부캐 키우기
  - 부캐별 랭킹 보여주기

<br>

## ☑️ Git Covention

### Branch

master

|

develop

|

feature (각 기능 단위)

- feature의 기능 단위는 Jira Story 기준
    - 필요에 따라 통합해서 사용해도 괜찮음
    - `feature/구분-이슈번호-담당자`
- 예시
    - `feature/fe-18-jiyeon`
    - `feature/fe-18-eunsong`
    - `feature/be-50-victoria`
    - `feature/be-50-seona`
    - `feature/be-50-wonsun`

### Commit

```
[#Jira 이슈번호] type: 행위 요약

body
```

```
[#S05P21A303-99] feat: 대표 부캐 설정 api 구현

행위에 대한 자세한 설명 (What & Why)
```

- 제목은 필수, 본문은 설명 필요할 때만 선택적 작성

#### type 종류

| type     | 설명                                                         |
| -------- | ------------------------------------------------------------ |
| feat     | 새로운 기능 추가                                             |
| fix      | 버그 수정                                                    |
| document     | 문서 수정                                                    |
| style    | 코드 의미에 영향을 주지 않는 수정 (코드 포맷팅, 들여쓰기 추가 등) |
| refactor | 코드 리팩토링                                                |
| test     | 테스트 관련 코드 수정                                        |
| build    | 빌드 관련 파일 수정                                          |
| ci       | CI 설정 파일 수정                                            |
| chore    | 그 외 자잘한 수정                                            |
| design   | CSS 등 UI 디자인 수정                                        |
| comment  | 주석 추가 및 수정                                            |
| rename   | 파일 또는 폴더명 수정 및 이동                                |
| remove   | 파일 또는 폴더 삭제                                          |

### Git 사용법

- 문제가 발생할 경우 팀에 바로 공유하기
- `git branch`, `git status`, `git pull` 의 습관화 ✅
- conflict를 최소화 합시다 😊

1. develop branch로 이동 후 최신 버전 업데이트

    ```bash
    $ git switch develop
    $ git pull origin develop
    ```

2. 각 기능 단위 feature branch 생성

    ```bash
    $ git switch -c <브랜치명>
    ```

3. 해당 branch에 변경 내용 add - commit - push

    ```bash
    $ git add <파일 또는 폴더>
    $ git commit -m '커밋 메시지'
    $ git push origin <브랜치명>
    ```

4. GitLab에서 `create merge request` - 코드 리뷰 후 `merge`
5. 로컬에서 merge 완료된 feature branch 삭제

    ```bash
    $ git switch develop
    $ git merge <브랜치명>
    $ git pull # develop보다 커밋한 횟수만큼 앞서 있으므로
    $ git branch -d <브랜치명>
    ```

<br>

## 📝 회의 기록

<details open>
  <summary>1주차 회의록 (2021/08/30 ~ 2021/09/03)</summary>
  <ul>
      <li><a href="./document/dev_log/20210830_회의록.md">08월 30일 : 아이디어 기획</a></li>
      <li><a href="./document/dev_log/20210831_회의록.md">08월 31일 : 아이디어 기획, 컨설턴트 미팅</a></li>
      <li><a href="./document/dev_log/20210901_회의록.md">09월 01일 : 아이디어 기획</a></li>
      <li><a href="./document/dev_log/20210902_회의록.md">09월 02일 : 아이디어 기획, 와이어프레임 제작</a></li>
      <li><a href="./document/dev_log/20210903_회의록.md">09월 03일 : 아이디어 기획, 와이어프레임 제작</a></li>
  </ul>
</details>

<details open>
  <summary>2주차 회의록 (2021/09/06 ~ 2021/09/10)</summary>
  <ul>
      <li><a href="./document/dev_log/20210906_회의록.md">09월 06일 : 프로젝트 기능 구체화</a></li>
      <li><a href="./document/dev_log/20210907_회의록.md">09월 07일 : 서비스명 및 로고 컨셉 선정</a></li>
      <li><a href="./document/dev_log/20210908_회의록.md">09월 08일 : 프로젝트 기능 구현 방법 논의</a></li>
      <li><a href="./document/dev_log/20210909_회의록.md">09월 09일 : 프로젝트 기능 관련 수정사항</a></li>
      <li><a href="./document/dev_log/20210910_회의록.md">09월 10일 : 캐릭터 디자인 컨셉 선정</a></li>
  </ul>
</details>
<details open>
  <summary>3주차 회의록 (2021/09/13 ~ 2021/09/17)</summary>
  <ul>
      <li><a href="./document/dev_log/20210913_회의록.md">09월 13일 : 프로젝트 기능 개발</a></li>
      <li><a href="./document/dev_log/20210914_회의록.md">09월 14일 : 프로젝트 기능 개발</a></li>
      <li><a href="./document/dev_log/20210915_회의록.md">09월 15일 : 프로젝트 기능 개발</a></li>
      <li><a href="./document/dev_log/20210916_회의록.md">09월 16일 : 프로젝트 기능 개발</a></li>
      <li><a href="./document/dev_log/20210917_회의록.md">09월 17일 : 프로젝트 기능 개발</a></li>
  </ul>
</details>
