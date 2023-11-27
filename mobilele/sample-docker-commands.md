```
docker network create softuni

docker run \
-e MYSQL_ALLOW_EMPTY_PASSWORD='yes' \
-e MYSQL_DATABASE='mobilele' \
--hostname=db \
--network=softuni \
-p 3306:3306 \
arm64v8/mysql:oracle --character-set-server=utf8mb4 --collation-server=utf8mb4_bin --default-authentication-plugin=mysql_native_password

docker run \
-e MYSQL_HOST='db' \
-e MYSQL_USER='root' \
-e MYSQL_PASSWORD='' \
-p 8080:8080 \
--hostname=mobilele \
--network=softuni \
luchob/mobilele
```