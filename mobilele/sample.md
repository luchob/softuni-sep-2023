```
docker run \
-e MYSQL_ALLOW_EMPTY_PASSWORD='yes' \
-e MYSQL_DATABASE='mobilele' \
-p 3306:3306 \
--hostname=db \
--network=softuni \
arm64v8/mysql:oracle --character-set-server=utf8mb4 --collation-server=utf8mb4_bin --default-authentication-plugin=mysql_native_password


docker build -t luchob/mobilele:v1 -f deployment/Dockerfile .


docker run \
-e MYSQL_HOST='db' \
-e MYSQL_USER='root' \
-p 8080:8080 \
--hostname=mobilele \
--network=softuni \
luchob/mobilele:v1
```