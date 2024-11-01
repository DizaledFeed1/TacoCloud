-- SET FOREIGN_KEY_CHECKS = 0;
--
-- DROP TABLE IF EXISTS taco_ingredients;
-- DROP TABLE IF EXISTS Ingredient;
-- DROP TABLE IF EXISTS Taco_Order;
-- DROP TABLE IF EXISTS Taco;
--
-- SET FOREIGN_KEY_CHECKS = 1;


-- Создание таблицы Taco_Order
create table if not exists Taco_Order (
id identity primary key,
delivery_Name varchar(50) not null,
delivery_Street varchar(50) not null,
delivery_City varchar(50) not null,
delivery_State varchar(2) not null,
delivery_Zip varchar(10) not null,
cc_number varchar(16) not null,
cc_expiration varchar(5) not null,
cc_cvv varchar(3) not null,
placed_at timestamp not null
);

-- Создание таблицы Taco
create table if not exists Taco (
id identity primary key,
name varchar(50) not null,
taco_order bigint not null,
created_at timestamp not null,
foreign key (taco_order) references Taco_Order(id) on delete cascade
);

-- Создание таблицы Ingredient
create table if not exists Ingredient (
id varchar(4) not null primary key,
name varchar(25) not null,
type varchar(10) not null
);

-- Создание таблицы Ingredient_Ref
create table if not exists Ingredient_Ref (
ingredient varchar(4) not null,
taco bigint not null,
foreign key (ingredient) references Ingredient(id) on delete cascade,
foreign key (taco) references Taco(id) on delete cascade
);
CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 50;
