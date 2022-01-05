--Users
INSERT INTO users(username, password, enabled, telephone_number, email)
VALUES ('Henk', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '061234567', 'user@eqries.nl'),
       ('Peter', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '067654321', 'peter@eqries.nl'),
       ('Jasper', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '066565445',
        'hoofd.admin@eqries.nl');

--Authorities
INSERT INTO authorities (username, authority)
VALUES ('Henk', 'ROLE_USER'),
       ('Peter', 'ROLE_USER'),
       ('Jasper', 'ROLE_USER'),
       ('Jasper', 'ROLE_ADMIN');

--Customers
INSERT INTO klanten (first_name, last_name, postal_code)
VALUES ('Paul', 'Krol', '1234AB'),
       ('Willeke', 'Vossen', '4321BA'),
       ('Henk', 'Jansen', '2341CD');

--Cars
INSERT INTO autos (license_plate, registration_papers, customer_id)
VALUES ('vv-11-ww', 'legalDocument', 1),
       ('aa-99-bc', 'legalDocument', 2),
       ('dd-23-qw', 'legalDocument', 2),
       ('ff-11-dd', 'legalDocument', 3);

--Appointments
INSERT INTO afspraken (appointment_date, appointment_status, car_pickup_date, customer_id, car_id, description)
VALUES ('2021-12-01T08:00:00', 0, '2021-12-20T08:00:00', 1, 1, 'motor doet raar'),
       ('2021-11-04T09:00:00', 1, '2021-12-04T12:00:00', 2, 2, 'ruitenwisser veegt strepen'),
       ('2021-09-02T12:00:00', 2, '2021-10-04T09:00:00', 2, 2, 'knipperlicht vloeistof is op'),
       ('2021-12-02T12:00:00', 2, '2021-12-31T09:00:00', 2, 3, 'Remmen lopen aan'),
       ('2021-09-02T12:00:00', 4, '2021-10-04T09:00:00', 3, 4, 'distributieriem versleten en koelvloeistof op');

--Onderdelen
INSERT INTO auto_onderdelen (item_description, price, stock, used_parts)
VALUES ('Remset voor', 99, 21, 2),
       ('Remset achter', 105, 33, 22),
       ('Koppeling', 799, 10, 4),
       ('Knipperlicht vloeistof', 600, 999, 1),
       ('Ruitenwisser', 33, 20, 10),
       ('Winterband', 100, 8, 35),
       ('Allroundband', 120, 20, 99),
       ('Zomerband', 95, 10, 95);

--Inspecties
INSERT INTO inspecties (findings, estimated_costs, inspection_status, inspection_date, car_id)
VALUES ('Remset voor en achter', 300, 0, '2022-01-01T10:45:00', 1 ),
       ('Ruitenwisser en vloeistof. Voor en achter', 1400, 1, '2022-01-01T10:45:00', 2 ),
       ('Banden vervangen 4x winter', 400, 0, '2022-01-03T10:45:00', 2 ),
       ('Koppeling vervangen en rem voor ', 1000, 0, '2022-01-04T16:45:00', 2 ),
       ('Remset voor en achter', 300, 0, '2022-01-02T10:45:00', 4 );
