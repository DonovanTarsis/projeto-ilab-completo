CREATE DATABASE kintsugi_tracking;
USE kintsugi_tracking;

DROP TABLE IF EXISTS client CASCADE;

CREATE TABLE client(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	name TEXT NOT NULL, 
	adress TEXT NOT NULL
);
  
DROP TABLE IF EXISTS driver CASCADE;

CREATE TABLE driver(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	name TEXT NOT NULL, 
	phone_number CHAR(11) UNIQUE NOT NULL, 
    password TEXT NOT NULL,
    email CHAR(50) UNIQUE NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE
);
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE orders(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	value INTEGER NOT NULL, 
	timestamp TEXT NOT NULL,
    status INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    driver_id INTEGER,
  	constraint fk_client foreign key (client_id) references client(id),
	constraint fk_driver foreign key (driver_id) references driver(id)
);

DROP TABLE IF EXISTS tracking CASCADE;

CREATE TABLE tracking(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    timestamp TEXT,
    latitude DOUBLE,
    longitude DOUBLE,
	order_id INTEGER NOT NULL,
  	constraint fk_orders foreign key (order_id) references orders(id)
);
