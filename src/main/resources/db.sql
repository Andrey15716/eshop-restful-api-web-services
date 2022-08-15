--------------------------------------------------------
--  DDL for schema ESHOP
--------------------------------------------------------
DROP SCHEMA IF EXISTS eshop2;
CREATE SCHEMA IF NOT EXISTS eshop2;

--------------------------------------------------------
--  DDL for Table CATEGORY
--------------------------------------------------------
DROP TABLE IF EXISTS eshop2.categories;
CREATE TABLE IF NOT EXISTS eshop2.categories (
                                                 id INT NOT NULL AUTO_INCREMENT,
                                                 name VARCHAR(45) NOT NULL,
    image VARCHAR(100) NOT NULL,
    PRIMARY KEY (id));

--------------------------------------------------------
--  DDL for Table USER
--------------------------------------------------------
DROP TABLE IF EXISTS eshop2.user;
CREATE TABLE IF NOT EXISTS eshop2.user (
                                           id INT NOT NULL AUTO_INCREMENT,
                                           name VARCHAR(50) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL,
    date_of_birthday DATE,
    PRIMARY KEY (id),
    UNIQUE INDEX idx_user_id_unique (id ASC));

--------------------------------------------------------
--  DDL for Table PRODUCTS
--------------------------------------------------------
DROP TABLE IF EXISTS eshop2.products;
CREATE TABLE IF NOT EXISTS eshop2.products (
                                               id INT NOT NULL AUTO_INCREMENT,
                                               name VARCHAR(50) NOT NULL,
    description VARCHAR(300) NOT NULL,
    price INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX idx_products_id_unique (id ASC),
    CONSTRAINT FK_products_category_id_categories_id
    FOREIGN KEY (category_id)
    REFERENCES eshop2.categories (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

--------------------------------------------------------
--  DDL for Table ORDER
--------------------------------------------------------
DROP TABLE IF EXISTS eshop2.orders;
CREATE TABLE IF NOT EXISTS eshop2.orders (
                                             id INT NOT NULL AUTO_INCREMENT,
                                             user_id INT NOT NULL,
                                             price VARCHAR(45) NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id));

--------------------------------------------------------
--  DDL for Table ORDER_PRODUCT
--------------------------------------------------------
DROP TABLE IF EXISTS eshop2.order_product;
CREATE TABLE IF NOT EXISTS eshop2.order_product (
                                                    order_id INT NOT NULL,
                                                    product_id INT NOT NULL,
                                                    PRIMARY KEY (order_id, product_id),
    CONSTRAINT FK_ORDERS_PRODUCTS_ORDER_ID_ORDERS_ID
    FOREIGN KEY (order_id)
    REFERENCES eshop2.orders (id),
    CONSTRAINT FK_ORDERS_PRODUCTS_PRODUCT_ID_PRODUCTS_ID
    FOREIGN KEY (product_id)
    REFERENCES eshop2.products (id));

INSERT INTO eshop2.user (name ,surname, password, date_of_birthday)
VALUES ('admin', 'admin', 'admin', '2015-03-31');