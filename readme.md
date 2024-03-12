## バックエンド実行方法
①Dockerを起動してください。
```
$ docker-compose up -d
```
②Dockerコンテナに入ってください。
```
$ docker exec -it study-app-1 /bin/bash
```
③以下のコマンドでSpringBootをコンパイル＆実行してください。
```
$ ./gradlew bootRun
```
④以下のURLにアクセスしてください。
```
http://localhost:8080/
```

## SQLについて
infra/mysql/【手動実行】マイグレーション用SQLファイル【要コピペ実行】  
上記フォルダにデータベースの定義用SQLを格納しています。  
また、readmeも設置しているので、そちらご確認ください。
