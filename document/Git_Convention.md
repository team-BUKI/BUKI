# ☑️ Git Covention

## Branch

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

## Commit

```
[#Jira 이슈번호] type: 행위 요약

body
```

```
[#S05P21A303-99] feat: 대표 부캐 설정 api 구현

행위에 대한 자세한 설명 (What & Why)
```

- 제목은 필수, 본문은 설명 필요할 때만 선택적 작성

### type 종류

| type     | 설명                                                         |
| -------- | ------------------------------------------------------------ |
| feat     | 새로운 기능 추가                                             |
| fix      | 버그 수정                                                    |
| document | 문서 수정                                                    |
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

## Git 사용법 정리

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
