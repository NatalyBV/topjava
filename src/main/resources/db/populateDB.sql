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

INSERT INTO meals (description, calories, user_id) VALUES
  ('Outmeal', 176, 100000),
  ('Omelette', 154, 100001),
  ('Borscht', 98, 100000),
  ('Tomato and cucumber salad', 135, 100000),
  ('Ice-cream', 207, 100000),
  ('Tea', 1, 100000),
  ('Milk pudding', 194, 100001),
  ('Olivier salad', 324, 100001),
  ('Pancakes with meat', 789, 100001),
  ('Cola', 38, 100001),
  ('Cheeseburger', 303, 100001),
  ('Pancakes with meat', 789, 100001),
  ('Fruit salad', 150, 100001);