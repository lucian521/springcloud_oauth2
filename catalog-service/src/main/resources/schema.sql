DROP TABLE IF EXISTS catalog_info;
CREATE TABLE catalog_info(id VARCHAR(255) PRIMARY KEY NOT NULL ,active BIT(1),catalog_id  BIGINT(20),name  VARCHAR(255),hobby VARCHAR(255),userid VARCHAR(255)
);