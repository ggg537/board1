# 1. 자바 17 버전 환경을 가져온다
FROM openjdk:17-jdk-slim

# 2. 빌드된 jar 파일을 도커 안으로 가져온다
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 3. 실행한다
ENTRYPOINT ["java","-jar","/app.jar"]