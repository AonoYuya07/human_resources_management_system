## 命名規則
以下の通りに命名すること  
yyyymmdd-実行順_SQLの説明.sql  
例) 20200101-01_テーブル作成.sql

## ファイル構成
命名されたファイル名によって、記載内容を分けること。
create_〇〇_table.sql
```
CREATE TABLE IF NOT EXISTS `〇〇` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主キー、オートインクリメント',,
  `name` varchar(255) NOT NULL COMMENT '名前',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

insert_〇〇_data.sql
```
INSERT INTO `〇〇` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, '〇〇', '2020-01-01 00:00:00', '2020-01-01 00:00:00'),
(2, '〇〇', '2020-01-01 00:00:00', '2020-01-01 00:00:00'),
(3, '〇〇', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
```

update_〇〇_data.sql
```
UPDATE `〇〇` SET `name` = '〇〇' WHERE `id` = 1;
```

delete_〇〇_data.sql
```
DELETE FROM `〇〇` WHERE `id` = 1;
```

カラムの変更等を伴う場合は以下のようにすること
ファイル名：alter_〇〇_table.sql
```
ALTER TABLE `〇〇` CHANGE `name` `name` varchar(255) NOT NULL COMMENT '名前';
```

## ファイルの実行順
ファイル名の先頭に実行順を記載すること。
例）
```
20200101-01_テーブル作成.sql
20200101-02_データ投入.sql
20200101-03_データ更新.sql
20200101-04_データ削除.sql
```

## ファイルの実行方法
tableplusなどのアプリケーションに直接インポートORコピペして実行することを想定しています。  
勉強用アプリケーション前提なのでこれでいいかと。
