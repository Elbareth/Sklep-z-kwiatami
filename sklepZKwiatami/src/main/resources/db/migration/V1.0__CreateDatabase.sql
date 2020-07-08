CREATE TABLE d_user(
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(1000) NOT NULL,
    granted_authority VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    is_account_non_expired TINYINT(1) NOT NULL,
    is_account_non_locked TINYINT(1) NOT NULL,
    is_credentials_non_expired TINYINT(1) NOT NULL,
    is_enabled TINYINT(1) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE d_flower(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price DECIMAL(4,2) NOT NULL,
    barcode BLOB NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE d_history(
    id INT NOT NULL AUTO_INCREMENT,
    user INT NOT NULL,
    flower INT NOT NULL,
    quantity INT NOT NULL,
    localDateTime DATETIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user) REFERENCES d_user(id),
    FOREIGN KEY (flower) REFERENCES d_flower(id)
);