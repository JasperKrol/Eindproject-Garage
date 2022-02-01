--Users
INSERT INTO users(username, password, enabled, telephone_number, email)
VALUES ('Henk', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '061234567','monteurs@eqries.nl'),
       ('Peter', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '067654321', 'peter@eqries.nl'),
       ('Jasper', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '066565445', 'admin@eqries.nl'),
       ('Pien', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '063123555', 'boffice@eqries.nl');

--Authorities
INSERT INTO authorities (username, authority)
VALUES ('Henk', 'ROLE_USER'),
       ('Henk', 'ROLE_MONTEUR'),
       ('Peter', 'ROLE_USER'),
       ('Peter', 'ROLE_MONTEUR'),
       ('Jasper', 'ROLE_USER'),
       ('Jasper', 'ROLE_ADMIN'),
       ('Pien', 'ROLE_OFFICE'),
       ('Pien', 'ROLE_USER');

--Customers
INSERT INTO klanten (first_name, last_name, postal_code, telephone_number)
VALUES ('Paul', 'Krol', '1234AB', '06123465'),
       ('Willeke', 'Vossen', '4321BA', '06999999'),
       ('Henk', 'Jansen', '2341CD', '061115555');

--Cars
INSERT INTO autos (license_plate, customer_id)
VALUES ('vv-11-ww', 1),
       ('aa-99-bc', 2),
       ('dd-23-qw', 2),
       ('ff-11-dd', 3);

--Appointments
INSERT INTO afspraken (appointment_date, appointment_status, car_pickup_date, customer_id, car_id, description)
VALUES ('2021-12-01T08:00:00', 0, '2021-12-20T08:00:00', 1, 1, 'motor doet raar, piept voor'),
       ('2021-11-04T09:00:00', 1, '2021-12-04T12:00:00', 2, 2, 'ruitenwisser veegt strepen'),
       ('2021-09-02T12:00:00', 4, '2021-10-04T09:00:00', 2, 2, 'knipperlicht vloeistof is op'),
       ('2021-12-02T12:00:00', 3, '2021-12-31T09:00:00', 2, 3, 'Remmen lopen aan'),
       ('2021-09-02T12:00:00', 5, '2021-10-04T09:00:00', 3, 4, 'distributieriem versleten en koelvloeistof op');

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
INSERT INTO inspecties (findings, estimated_costs, inspection_status, inspection_date, car_id, appointment_id)
VALUES ('Remset voor en achter', 300, 0, '2022-01-01T10:45:00', 1, 1),
       ('Ruitenwisser voor en achter + knip. vloeistof. ', 1400, 1, '2022-01-01T10:45:00', 2, 2),
       ('Banden vervangen 4x winter', 400, 0, '2022-01-03T10:45:00', 2, 3),
       ('Koppeling vervangen en remmen voor ', 1000, 0, '2022-01-04T16:45:00', 3, 4),
       ('Remset voor en achter', 300, 0, '2022-01-02T10:45:00', 4, 5);

--Reparaties
INSERT INTO reparaties (appointment_status, repair_date_workshop, car_id, appointment_id)
VALUES (0, '2022-01-01T00:45:00', 1, 1),
       (1, '2022-12-01T15:00:00', 2, 2),
       (0, '2022-01-11T18:30:00', 2, 3),
       (0, '2022-01-11T18:30:00', 3, 4),
       (5, '2022-12-24T10:45:00', 4, 5);

-- Repairs with items
INSERT INTO reparatie_items (repair_id, inventory_item_id, amount)
VALUES (1, 1, 1),
       (1, 2, 1),
       (2, 5, 2),
       (2, 4, 1),
       (3, 6, 4),
       (4, 3, 1),
       (4, 1, 1),
       (5, 1, 1),
       (5, 2, 1);

-- Facturen
INSERT INTO facturen (gross_amount, netto_amount, vat_amount, invoice_date, invoice_paid, customer_id, repair_id)
VALUES (300, 363, 63, '2022-01-01', true, 1, 1),
       (1400, 1694, 294, '2022-12-01', false, 2, 2),
       (450, 544.50, 94.50, '2022-12-04', false, 2, 3),
       (300, 363, 63, '2022-01-01', true, 3, 4),
       (300, 363, 63, '2022-01-01', true, 3, 5);

