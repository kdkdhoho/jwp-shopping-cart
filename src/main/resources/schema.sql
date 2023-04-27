DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(30) NOT NULL,
    price int NOT NULL,
    image_url varchar(2083) NOT NULL
);
