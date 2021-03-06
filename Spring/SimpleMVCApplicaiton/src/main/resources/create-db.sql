DROP TABLE IF EXISTS PUBLIC.users;
CREATE TABLE PUBLIC.users
(
  id        BIGINT AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(50),
  lastName  VARCHAR(50),
  email  VARCHAR(50),
  password  VARCHAR(100),
  roles  VARCHAR(100),
  processed SMALLINT,
  enabled SMALLINT,
  birthday DATE
);
DROP TABLE IF EXISTS PUBLIC.executions;
CREATE TABLE PUBLIC.executions
(
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  method_name       VARCHAR(50),
  duration          BIGINT,
  execution_started TIMESTAMP,
  is_long           SMALLINT
)