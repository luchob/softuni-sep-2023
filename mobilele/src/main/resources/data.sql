INSERT INTO users (id, active, email, first_name, last_name, password)
VALUES
    (1, 1, 'admin@example.com', 'Admin', 'Adminov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151'),
    (2, 1, 'user@example.com', 'User', 'Userov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151');


INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);


INSERT INTO `brands` (`id`, `name`)
VALUES
    (1, 'Toyota'),
    (2, 'Ford'),
    (3, 'Trabant');

INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES
    (1, 'CAR', 1, 'Camry'),
    (2, 'CAR', 1, 'Corolla'),
    (3, 'CAR', 2, 'Focus'),
    (4, 'CAR', 2, 'Fiesta'),
    (5, 'CAR', 3, '601');
