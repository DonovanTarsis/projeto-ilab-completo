CREATE DATABASE kintsugi_tracking;

DROP TABLE IF EXISTS client CASCADE;

CREATE TABLE client(
	id SERIAL NOT NULL PRIMARY KEY, 
	name TEXT NOT NULL, 
	adress TEXT NOT NULL
);
  
DROP TABLE IF EXISTS driver CASCADE;

CREATE TABLE driver(
	id SERIAL NOT NULL PRIMARY KEY, 
	name TEXT NOT NULL, 
	phone_number CHAR(11) UNIQUE NOT NULL, 
    password TEXT NOT NULL,
    email CHAR(50) UNIQUE NOT NULL,
    latitude DECIMAL,
    longitude DECIMAL
);
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE orders(
	id SERIAL NOT NULL PRIMARY KEY, 
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
	id SERIAL NOT NULL PRIMARY KEY,
    timestamp TEXT,
    latitude DECIMAL,
    longitude DECIMAL,
	order_id INTEGER NOT NULL,
  	constraint fk_orders foreign key (order_id) references orders(id)
);
