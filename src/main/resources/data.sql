--Users
INSERT INTO users(username, password, enabled, telephone_number, email)
VALUES ('Henk', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '061234567', 'user@eqries.nl'),
       ('Peter', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE,'067654321', 'peter@eqries.nl'),
       ('Jasper', '$2y$10$YD.MlrfFbKHACWVwZxDQVOWuTR.3z0ZlUTVaT7gCcPwPDpZSXUV/q', TRUE, '066565445', 'hoofd.admin@eqries.nl');

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
VALUES ('vv-11-ww', 'legalDocument', 2),
       ('aa-99-bc', 'legalDocument', 1),
       ('dd-23-qw', 'legalDocument', 3),
       ('ff-11-dd', 'legalDocument', 2);

--Appointments
INSERT INTO afspraken (appointment_date, appointment_status, car_pickup_date, customer_id)
VALUES ('2021-12-01T08:00:00', 1, '2021-12-20T08:00:00', 1 ),
       ('2021-11-04T09:00:00', 1, '2021-12-04T12:00:00', 2),
       ('2021-09-02T12:00:00', 1, '2021-10-04T09:00:00', 3)

