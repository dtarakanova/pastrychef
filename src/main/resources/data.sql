DELETE FROM Ingredient_Ref;
DELETE FROM Cake;
DELETE FROM Cake_Order;

DELETE FROM Ingredient;

INSERT INTO Ingredient(id, name, type) VALUES ('CHCK', 'Cheesecake', 'FILLING');
INSERT INTO Ingredient(id, name, type) VALUES ('BLFO', 'Black Forrest', 'FILLING');
INSERT INTO Ingredient(id, name, type) VALUES ('STCR', 'Strawberry Cream', 'FILLING');
INSERT INTO Ingredient(id, name, type) VALUES ('CARR', 'Carrot Cake', 'FILLING');
INSERT INTO Ingredient(id, name, type) VALUES ('PRAL', 'Praline', 'FILLING');
INSERT INTO Ingredient(id, name, type) VALUES ('WHIT', 'White', 'ICING');
INSERT INTO Ingredient(id, name, type) VALUES ('PINK', 'Pink', 'ICING');
INSERT INTO Ingredient(id, name, type) VALUES ('BLUE', 'Blue', 'ICING');
INSERT INTO Ingredient(id, name, type) VALUES ('YELL', 'Yellow', 'ICING');
INSERT INTO Ingredient(id, name, type) VALUES ('GREN', 'Green', 'ICING');
INSERT INTO Ingredient(id, name, type) VALUES ('SMLL', '2 kg', 'WEIGHT');
INSERT INTO Ingredient(id, name, type) VALUES ('MEDI', '4 kg', 'WEIGHT');
INSERT INTO Ingredient(id, name, type) VALUES ('LARG', '6 kg', 'WEIGHT');