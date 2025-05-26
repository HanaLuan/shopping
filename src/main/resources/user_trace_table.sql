CREATE TABLE user_trace_table (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(64) NOT NULL, -- 用户ID或NO_LOGIN
    ip VARCHAR(64) NOT NULL,
    region VARCHAR(128), -- 地区，预留
    action VARCHAR(32) NOT NULL, -- login/register/navigation等
    action_data VARCHAR(255), -- 相关数据，如商品ID
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
