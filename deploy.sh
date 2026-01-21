#!/bin/bash

# 1. 기존 컨테이너 중단 및 삭제
docker stop my-spring-app || true
docker rm my-spring-app || true

# 2. 기존 이미지 삭제
docker rmi my-spring-app || true

# 3. 빌드 (젠킨스 환경 등 리눅스면 ./gradlew)
./gradlew clean build -x test

# 4. 이미지 생성
docker build -t my-spring-app .

# 5. 컨테이너 실행 (여기가 중요!!!)
# --network: 젠킨스/MySQL이 쓰는 네트워크 이름 (아래 설명 꼭 참고)
# SPRING_DATASOURCE_URL: mysql_server(컨테이너이름)으로 직접 접속
docker run -d \
  --name my-spring-app \
  -p 8080:8080 \
  --network root_my-network \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql_server:3306/board_db?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true" \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  my-spring-app