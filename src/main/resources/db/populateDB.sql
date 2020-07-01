DELETE FROM user_roles where 1=1;
DELETE FROM meals where 1=1;
DELETE FROM users where 1=1;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, dateTime, calories, user_id) VALUES
  ('Outmeal', '2020-06-15 19:10:11-00', 176, 100000),
  ('Omelette', '2020-06-16 14:10:26-00', 154, 100001),
  ('Borscht', '2020-06-17 15:10:25-00', 98, 100000),
  ('Tomato and cucumber salad', '2020-06-18 19:10:25-00', 135, 100001),
  ('Ice-cream', '2020-06-19 11:10:25-00', 207, 100001),
  ('Tea', '2020-06-20 10:10:25-00', 50, 100001),
  ('Milk pudding', '2020-06-21 18:10:25-00', 194, 100001),
  ('Olivier salad', '2020-06-22 19:10:25-00', 324, 100001),
  ('Pancakes with meat', '2020-06-23 17:10:25-00', 789, 100001),
  ('Cola', '2020-06-24 16:10:25-00', 38, 100001),
  ('Cheeseburger', '2020-06-25 14:10:25-00', 303, 100001),
  ('Pancakes with meat', '2020-06-26 13:10:25-00', 789, 100001),
  ('Fruit salad', '2020-06-27 10:10:25-00', 150, 100001);