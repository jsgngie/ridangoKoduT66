CREATE TABLE IF NOT EXISTS highscores(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(256),
    score BIGINT
);

CREATE TABLE IF NOT EXISTS drinks(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    drinkId BIGINT,
    strDrink varchar(256),
    strGlass varchar(256),
    strInstructions varchar(1000),
    strDrinkThumb varchar(256),
    isGuessed BOOLEAN
);

CREATE TABLE IF NOT EXISTS ingredients(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    drinkId BIGINT,
    name varchar(256),
    measure varchar(256),
    FOREIGN KEY (drinkId) REFERENCES drinks(Id)
);