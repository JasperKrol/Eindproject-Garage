--Customers

INSERT INTO klanten (first_name, last_name, postal_code)
VALUES ('Jasper', 'Krol', '1234AB'),
       ('Willeke', 'Vossen', '4321BA'),
       ('Henk', 'Jansen', '2341CD');

INSERT INTO autos (license_plate, registration_papers, customer_id)
VALUES ('vv-11-ww', 'legalDocument', 2),
       ('aa-99-bc', 'legalDocument', 1),
       ('dd-23-qw', 'legalDocument', 3),
       ('ff-11-dd', 'legalDocument', 2);
