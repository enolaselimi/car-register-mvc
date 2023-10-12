USE cars_db;

INSERT INTO wheels (id, size, type)
VALUES  (1,"13","car"),
        (2,"13","alloy"),
        (3,"14","powersport"),
        (4,"14","alloy"),
        (5,"15","powersport"),
        (6,"15","car");

INSERT INTO engine (id, power, type)
VALUES  (1,200,"V6 Engine"),
        (2,250,"V6 Engine"),
        (3,350,"V8 Engine"),
        (4,700,"V10 Engine"),
        (5,100,"Electric Motors"),
        (6,200,"Diesel Engines");

INSERT INTO car (id, from_year, name, to_year, type, engine_id, wheels_id)
VALUES  (1, '2020', 'Toyota Camry', '2023', 'Sedan', 1, 1),
        (2, '2019', 'Honda Civic', '2022', 'Sedan', 2, 2),
        (3, '2018', 'Ford Mustang', '2021', 'Sports Car', 3, 3),
        (4, '2017', 'Chevrolet Silverado', '2024', 'Truck', 4, 4),
        (5, '2016', 'Volkswagen Golf', '2022', 'Hatchback', 5, 5),
        (6, '2021', 'Jeep Wrangler', '2024', 'SUV', 6, 6);