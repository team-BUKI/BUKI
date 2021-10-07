# 🛠 Porting Manual

> 부키는 아래와 같은 환경에서 서비스중이며, 다음과 같은 방법으로 실행할 수 있습니다.

#### ⚙️ 시스템 환경

- CPU : `Intel(R) Xeon(R) CPU E5-2686 v4 @ 2.30GHz`
- OS : `Ubuntu 20.04 LTS (GNU/Linux 5.4.0-1018-aws x86_64)`
- Total Memory : `16396056 kB`

#### 🖥 시스템 구성

- `Jenkins 3.7 - blueocean` : CI/CD tool
- `AWS EC2` : Deployment Server
- `AWS S3` : File Server
- `Docker` : Backend/Frontend/Bigdata/Nginx 컨테이너 실행
- `Docker-compose` : 하나의 네트워크 안에서 Docker Image를 생성하는 설정 파일
- `Nginx` : Docker Container 위에 띄워지며, Reverse Proxy Server로서 요청 url에 따라 Frontend/Backend/Bigdata Server로 redirecting 합니다.

#### 💻 개발 환경

- Java `1.8`

- Python `3.8`

- MySQL `8.0`

- IntelliJ IDEA ULTIMATE `2020.3`

- Node.js `14.17.0 LTS`

- Visual Studio Code`1.59.0`

#### 🕹 Install & Usage

##### 🔵 Docker & Docker-compose 설치

```bash
# 필수 패키지 설치
sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
# GPG Key 인증
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
# docker repository 등록
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
# 도커 설치
sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io
# 시스템 부팅시 도커 시작
sudo systemctl enable docker && service docker start
# 도커 확인
sudo service docker status

# 도커-컴포즈 설치
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# 권한 줘야합니다.
$ sudo chmod +x /usr/local/bin/docker-compose
```

##### 🔵 Jenkins 연동

```bash
# 젠킨스 이미지 다운로드
sudo docker pull jenkins/jenkins
# 젠킨스 구동
## 호스트 9090 포트로 젠킨스 8080포트 접속
## 호스트의 /home/jenkins와 젠킨스 컨테이너 /var/jenkins_home을 볼륨 마운트
## docker.sock 파일 볼륨 > 젠킨스 image id로 구동
sudo docker run -d -p 9090:8080 -v /home/jenkins:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
-v $(which docker):/usr/bin/docker \
-v /usr/local/bin/docker-compose:/usr/local/bin/docker-compose \
-u root $(image id)
# 현재 젠킨스 컨테이너 jenkins로 이름 변경
sudo docker rename $(current container name) jenkins
# 젠킨스 어드민 비밀번호 발급
sudo docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

##### 🔵 SSL Key 발급

> 이미 발급받은적이 있다면 해당 작업은 무시해도 무관합니다.

```
$ sudo apt-get install letsencrypt
$ sudo letsencrypt certonly --standalone -d <www제외한 도메인 이름>
```

##### 🟠 Docker Image 생성

```bash
$ docker-compose build
```

##### 🟠 Docker-compose 실행

> Docker-compose 설정 파일이 아래 Dockerfile들을 실행시켜 Docker image를 생성합니다.

- `../nginx/Dockerfile`
- `../frontend/Dockerfile`
- `../backend/Dockerfile`
- `../bigdata/Dockerfile`

##### 🟠 Docker Container 생성 및 실행

```bash
$ docker-compose -d up
```
