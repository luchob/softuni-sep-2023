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

INSERT INTO `offers` (`id`, `description`, `engine`, `image_url`, `mileage`, `price`, `transmission`, `uuid`, `year`, `model_id`)
VALUES
    (1, 'Top Trabi!', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG/1200px-AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG', 24000, 2223, 'MANUAL', 'b72e6550-e365-43bf-aab2-b57cafc2db7c', 1985, 5),
    (2, 'Top Trabi!', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG/1200px-AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG', 24000, 2225, 'MANUAL', 'b72e6550-e365-43bf-aab2-b57cafc2db71', 1986, 5),
    (3, 'Top Trabi!', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG/1200px-AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG', 24000, 2227, 'MANUAL', 'b72e6550-e365-43bf-aab2-b57cafc2db72', 1987, 5),
    (4, 'Top Trabi!', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG/1200px-AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG', 24000, 2221, 'MANUAL', 'b72e6550-e365-43bf-aab2-b57cafc2db73', 1988, 5),
    (5, 'Top Trabi!', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG/1200px-AWZ_Trabant_601S%2C_Verkehrszentrum_des_Deutschen_Museums.JPG', 24000, 2220, 'MANUAL', 'b72e6550-e365-43bf-aab2-b57cafc2db74', 1989, 5);
