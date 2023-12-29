## バックエンド実行方法
①Dockerを起動してください。
```
$ docker-compose up -d
```
②Dockerコンテナに入ってください。
```
$ $docker exec -it study-app-1 /bin/bash
```
③以下のコマンドでSpringBootをコンパイル＆実行してください。
```
$ ./gradlew bootRun
```
④以下のURLにアクセスしてください。
```
http://localhost:8080/
```

ｓ
