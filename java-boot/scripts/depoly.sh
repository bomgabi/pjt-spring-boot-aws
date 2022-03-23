#!/bin/bash

REPOSITORY=/home/ec2-user/app/step1
BUILD_NAME=pjt-spring-boot-aws
PROJECT_NAME=java-boot

cd $REPOSITORY/$BUILD_NAME/$PROJECT_NAME

echo "> Git pull"                                                                                                                                                        37,1          All


echo "> step1 디렉토리로 이동"
cd $REPOSITORY


echo "> Build 파일 복사"
cp $REPOSITORY/$BUILD_NAME/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/


CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)
echo "> 현재 구동 중인 애플리케이션 pid 확인 : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> Build 파일 복사"
else
  ehco "> kill -15 $CURRENT_PID"
#!/bin/bash

REPOSITORY=/home/ec2-user/app/step1
BUILD_NAME=pjt-spring-boot-aws
PROJECT_NAME=java-boot

cd $REPOSITORY/$BUILD_NAME/$PROJECT_NAME

echo "> Git pull"
git pull


echo "> 프로젝트 build 시작"
./gradlew build


echo "> step1 디렉토리로 이동"
cd $REPOSITORY


echo "> Build 파일 복사"
cp $REPOSITORY/$BUILD_NAME/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/


CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)
echo "> 현재 구동 중인 애플리케이션 pid 확인 : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> Build 파일 복사"
else
  ehco "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
echo "> JAR_NAME: 성"
echo "> $JAR_NAME 에 실행 권한추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
nohup java -jar \
    -Dspring.config.location=classpath:/application.yml,classpath:/application-real.yml,/home/ec2-uesr/app/application-oauth.yml,/home/ec2-uesr/app/application-real-db.yml \
    -Dspring.profiles.active=real \
    $JAR_NAME                                                                                                                                                37,1          All