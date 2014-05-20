DROP SCHEMA IF EXISTS `ecomsys`;
CREATE SCHEMA IF NOT EXISTS `ecomsys` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `ecomsys`;
DROP TABLE if EXISTS `users`;
DROP TABLE if EXISTS `user_roles`;
DROP TABLE if EXISTS `orders`;
DROP TABLE if EXISTS `order_products`;

/* User table */
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL, -- ensure unique
    password VARCHAR(30) NOT NULL
);

CREATE TABLE user_roles (
    user_name VARCHAR(30) NOT NULL,
    user_role VARCHAR(10) DEFAULT 'user',
    primary key (user_name, user_role)
);

/* Orders table */
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT(11) NOT NULL, -- make this a foreign key
    status ENUM('processing', 'shipping', 'delivered') DEFAULT 'processing',
    shipping_address_1 VARCHAR(255),
    shipping_address_2 VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    postcode INT(4),
    final_cost DECIMAL(9,2) DEFAULT NULL, /* Only set once shipping */
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


/* Table that stores all products in each order */
CREATE TABLE order_products (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    product_id INT(11) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    description MEDIUMTEXT NOT NULL,
    quantity INT(4) DEFAULT 1,
    order_id INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

INSERT INTO users (username, password) VALUES ("userA", "passwordA");
INSERT INTO user_roles(user_name, user_role) VALUES ("userA", "user");
INSERT INTO users (username, password) VALUES ("userB", "passwordB");
INSERT INTO user_roles(user_name, user_role) VALUES ("userB", "user");
INSERT INTO users (username, password) VALUES ("admin", "admin");
INSERT INTO user_roles(user_name, user_role) VALUES ("admin", "admin");
