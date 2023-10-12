CREATE TABLE wheels(
    id INT NOT NULL PRIMARY KEY,
    size VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE engine(
    id INT NOT NULL PRIMARY KEY,
    power INT NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE car(
    id INT NOT NULL PRIMARY KEY,
    from_year VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    to_year VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    engine_id INT,
    wheels_id INT
    CONSTRAINT `engine_car_fk` FOREIGN KEY(`engine_id`) REFERENCES `engine`(`id`),
    CONSTRAINT `wheels_car_fk` FOREIGN KEY(`wheels_id`) REFERENCES `wheels`(`id`)
);