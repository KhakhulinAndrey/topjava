DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories) VALUES
  (100000, '2017-08-13 16:24', 'BigMac', 500),
  (100000, '2017-08-13 16:24', 'Wopper', 580),
  (100000, '2017-08-13 16:24', 'Vodka', 220),
  (100001, '2017-08-13 11:24', 'Admin eat1', 888),
  (100001, '2017-08-13 09:24', 'Admin eat2', 777);
