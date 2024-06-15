CREATE TABLE IF NOT EXISTS Cake_Order (
id IDENTITY,
placed_at TIMESTAMP NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
city VARCHAR(50) NOT NULL,
address VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS Cake (
id IDENTITY,
created_at TIMESTAMP NOT NULL,
inscription VARCHAR(255) NOT NULL,
cake_order BIGINT NOT NULL,
cake_order_key BIGINT NOT NULL
);


CREATE TABLE IF NOT EXISTS Ingredient_Ref (
ingredient VARCHAR(4) NOT NULL,
cake BIGINT NOT NULL,
cake_key BIGINT NOT NULL
);


CREATE TABLE IF NOT EXISTS Ingredient (
id VARCHAR(4) NOT NULL,
name VARCHAR(25) NOT NULL,
type VARCHAR(15) NOT NULL
);

ALTER TABLE Ingredient ADD PRIMARY KEY(ID);

alter table Cake
    add foreign key (cake_order) references Cake_Order(id);
alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);
