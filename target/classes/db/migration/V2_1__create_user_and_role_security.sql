-- Table users:
CREATE TABLE user(
  user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  login VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  email VARCHAR(255) NOT NULL ,
  first_name VARCHAR(255) NOT NULL ,
  last_name VARCHAR(255) NOT NULL ,
  position VARCHAR(255) NOT NULL ,
  level INT NOT NULL ,
  department INT NOT NULL ,
  start_date VARCHAR(255) NOT NULL ,
  finish_date VARCHAR(255) NOT NULL ,
  salary DOUBLE NOT NULL
)
  ENGINE = InnoDB;

-- Table roles:
CREATE TABLE role(
  role_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  role VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping users and roles:
CREATE TABLE user_role(
  user_id BIGINT NOT NULL ,
  roles_role_id BIGINT NOT NULL ,

  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (roles_role_id) REFERENCES role(role_id)
)
  ENGINE = InnoDB;

-- Insert data

INSERT INTO role VALUES (1, 'OWNER');
INSERT INTO role VALUES (2, 'ADMIN');
INSERT INTO role VALUES (3, 'MANAGER');
INSERT INTO role VALUES (4, 'WAITER');