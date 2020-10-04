CREATE TABLE Restaurant (
restaurantid INT(6) AUTO_INCREMENT PRIMARY KEY,
restaurantname VARCHAR(30) NOT NULL,
login VARCHAR(30) NOT NULL,
passwordhash VARCHAR(200) NOT NULL
)

CREATE TABLE product (
productid INT(6) AUTO_INCREMENT PRIMARY KEY,
productname VARCHAR(30) NOT NULL,
calories INT(30) NOT NULL
)

CREATE TABLE menuitem (
menuitemid INT(6) AUTO_INCREMENT PRIMARY KEY,
menuitemname VARCHAR(30) NOT NULL,
calories INT(30) NOT NULL
)

CREATE TABLE ordertable (
orderid INT(6) AUTO_INCREMENT PRIMARY KEY,
restaurantid INT(6) NOT NULL,
FOREIGN KEY (restaurantid) REFERENCES Restaurant(restaurantid)
)

CREATE TABLE menuitemorder (
orderid INT(6) NOT NULL,
menuitemid INT(6) NOT NULL,
FOREIGN KEY (orderid) REFERENCES ordertable(orderid),
FOREIGN KEY (menuitemid) REFERENCES menuitem(menuitemid)
)