# 1. (수정됨) AWS에서 제공하는 안정적인 Java 17 버전 사용
FROM amazoncorretto:17

# 2. 빌드된 jar 파일을 도커 안으로 가져온다
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 3. 실행한다
ENTRYPOINT ["java","-jar","/app.jar"]