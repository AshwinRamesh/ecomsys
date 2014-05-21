DROP SCHEMA IF EXISTS `ecomsys`;
CREATE SCHEMA IF NOT EXISTS `ecomsys` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `ecomsys`;
DROP TABLE if EXISTS `users`;
DROP TABLE if EXISTS `user_roles`;
DROP TABLE if EXISTS `orders`;
DROP TABLE if EXISTS `order_products`;

-- Create syntax for TABLE 'order_products'
CREATE TABLE `order_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `product_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(4) DEFAULT '1',
  `order_id` int(11) NOT NULL,
  `cost` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_products_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Create syntax for TABLE 'orders'
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `status` enum('processing','shipping','delivered') COLLATE utf8_unicode_ci DEFAULT 'processing',
  `shipping_address_1` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shipping_address_2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postcode` int(4) DEFAULT NULL,
  `final_cost` decimal(9,2) DEFAULT NULL,
  `ship_cost` decimal(9,2) DEFAULT NULL,
  `product_cost` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Create syntax for TABLE 'user_roles'
CREATE TABLE `user_roles` (
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_role` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'user',
  PRIMARY KEY (`username`,`user_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Create syntax for TABLE 'users'
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO users (username, password) VALUES ("userA", "passwordA");
INSERT INTO user_roles(user_name, user_role) VALUES ("userA", "user");
INSERT INTO users (username, password) VALUES ("userB", "passwordB");
INSERT INTO user_roles(user_name, user_role) VALUES ("userB", "user");
INSERT INTO users (username, password) VALUES ("admin", "admin");
INSERT INTO user_roles(user_name, user_role) VALUES ("admin", "admin");
