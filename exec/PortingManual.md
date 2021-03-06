# ๐  Porting Manual

> ๋ถํค๋ ์๋์ ๊ฐ์ ํ๊ฒฝ์์ ์๋น์ค์ค์ด๋ฉฐ, ๋ค์๊ณผ ๊ฐ์ ๋ฐฉ๋ฒ์ผ๋ก ์คํํ  ์ ์์ต๋๋ค.

#### โ๏ธ ์์คํ ํ๊ฒฝ

- CPU : `Intel(R) Xeon(R) CPU E5-2686 v4 @ 2.30GHz`
- OS : `Ubuntu 20.04 LTS (GNU/Linux 5.4.0-1018-aws x86_64)`
- Total Memory : `16396056 kB`

#### ๐ฅ ์์คํ ๊ตฌ์ฑ

- `Jenkins 3.7 - blueocean` : CI/CD tool
- `AWS EC2` : Deployment Server
- `AWS S3` : File Server
- `Docker` : Backend/Frontend/Bigdata/Nginx ์ปจํ์ด๋ ์คํ
- `Docker-compose` : ํ๋์ ๋คํธ์ํฌ ์์์ Docker Image๋ฅผ ์์ฑํ๋ ์ค์  ํ์ผ
- `Nginx` : Docker Container ์์ ๋์์ง๋ฉฐ, Reverse Proxy Server๋ก์ ์์ฒญ url์ ๋ฐ๋ผ Frontend/Backend/Bigdata Server๋ก redirecting ํฉ๋๋ค.

#### ๐ป ๊ฐ๋ฐ ํ๊ฒฝ

- Java `1.8`

- Python `3.8`

- MySQL `8.0`

- IntelliJ IDEA ULTIMATE `2020.3`

- Node.js `14.17.0 LTS`

- Visual Studio Code`1.59.0`

#### ๐น Install & Usage

##### ๐ต Docker & Docker-compose ์ค์น

```bash
# ํ์ ํจํค์ง ์ค์น
sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
# GPG Key ์ธ์ฆ
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
# docker repository ๋ฑ๋ก
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
# ๋์ปค ์ค์น
sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io
# ์์คํ ๋ถํ์ ๋์ปค ์์
sudo systemctl enable docker && service docker start
# ๋์ปค ํ์ธ
sudo service docker status

# ๋์ปค-์ปดํฌ์ฆ ์ค์น
sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# ๊ถํ ์ค์ผํฉ๋๋ค.
$ sudo chmod +x /usr/local/bin/docker-compose
```

##### ๐ต Jenkins ์ฐ๋

```bash
# ์  ํจ์ค ์ด๋ฏธ์ง ๋ค์ด๋ก๋
sudo docker pull jenkins/jenkins
# ์  ํจ์ค ๊ตฌ๋
## ํธ์คํธ 9090 ํฌํธ๋ก ์  ํจ์ค 8080ํฌํธ ์ ์
## ํธ์คํธ์ /home/jenkins์ ์  ํจ์ค ์ปจํ์ด๋ /var/jenkins_home์ ๋ณผ๋ฅจ ๋ง์ดํธ
## docker.sock ํ์ผ ๋ณผ๋ฅจ > ์  ํจ์ค image id๋ก ๊ตฌ๋
sudo docker run -d -p 9090:8080 -v /home/jenkins:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
-v $(which docker):/usr/bin/docker \
-v /usr/local/bin/docker-compose:/usr/local/bin/docker-compose \
-u root $(image id)
# ํ์ฌ ์  ํจ์ค ์ปจํ์ด๋ jenkins๋ก ์ด๋ฆ ๋ณ๊ฒฝ
sudo docker rename $(current container name) jenkins
# ์  ํจ์ค ์ด๋๋ฏผ ๋น๋ฐ๋ฒํธ ๋ฐ๊ธ
sudo docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

##### ๐ต SSL Key ๋ฐ๊ธ

> ์ด๋ฏธ ๋ฐ๊ธ๋ฐ์์ ์ด ์๋ค๋ฉด ํด๋น ์์์ ๋ฌด์ํด๋ ๋ฌด๊ดํฉ๋๋ค.

```
$ sudo apt-get install letsencrypt
$ sudo letsencrypt certonly --standalone -d <www์ ์ธํ ๋๋ฉ์ธ ์ด๋ฆ>
```

##### ๐  Docker Image ์์ฑ

```bash
$ docker-compose build
```

##### ๐  Docker-compose ์คํ

> Docker-compose ์ค์  ํ์ผ์ด ์๋ Dockerfile๋ค์ ์คํ์์ผ Docker image๋ฅผ ์์ฑํฉ๋๋ค.

- `../nginx/Dockerfile`
- `../frontend/Dockerfile`
- `../backend/Dockerfile`
- `../bigdata/Dockerfile`

##### ๐  Docker Container ์์ฑ ๋ฐ ์คํ

```bash
$ docker-compose -d up
```
