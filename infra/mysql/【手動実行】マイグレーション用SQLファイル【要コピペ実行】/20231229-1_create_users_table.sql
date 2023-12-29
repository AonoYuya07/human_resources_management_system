CREATE TABLE Users (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主キー、オートインクリメント',
    userId VARCHAR(255) NOT NULL UNIQUE COMMENT 'ログイン用ユーザID、ユニーク',
    userName VARCHAR(255) NOT NULL COMMENT '日本語含むユーザ名',
    password VARCHAR(512) NOT NULL COMMENT '暗号化済パスワード',
    created_at TIMESTAMP NULL COMMENT '作成日時',
    updated_at TIMESTAMP NULL COMMENT '更新日時',
    PRIMARY KEY (id)
) COMMENT='Usersテーブル';
