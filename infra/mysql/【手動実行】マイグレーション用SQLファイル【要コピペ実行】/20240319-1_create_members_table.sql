CREATE TABLE IF NOT EXISTS `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主キー、オートインクリメント',
  `name` varchar(255) NOT NULL COMMENT '名前',
  `age` int(11) NOT NULL COMMENT '年齢',
  `gender` varchar(255) NOT NULL COMMENT '性別',
  `platform` varchar(255) NOT NULL COMMENT 'プラットフォーム(メイン活動場所)',
  `url` varchar(255) NOT NULL COMMENT 'URL',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--初期データ作成
INSERT INTO members (
    name,
    age,
    gender,
    platform,
    url,
    created_at,
    updated_at
) VALUES (
    'Dummy User',
    20,
    '男',
    'Twitter',
    'https://twitter.com/dummy_user',
    NOW(),
    NOW()
);
