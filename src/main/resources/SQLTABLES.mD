DROP TABLE IF EXISTS auto_eigenaren;
DROP TABLE IF EXISTS autos;
DROP TABLE IF EXISTS klanten;

CREATE TABLE klanten
(
id         INT GENERATED ALWAYS AS IDENTITY,
first_name CHAR(55) NOT NULL,
last_name  CHAR(55) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE autos
(
id           INT GENERATED ALWAYS AS IDENTITY,
kenteken     TEXT NOT NULL UNIQUE,
autopapieren INTEGER DEFAULT 5,
eigenaar_id  INT  NOT NULL,
PRIMARY KEY (id),
CONSTRAINT fk_eigenaar FOREIGN KEY (eigenaar_id) REFERENCES klanten (id)
);

CREATE TABLE auto_eigenaren
(
eigenaar_id INT,
auto_id INT,
kenteken_id TEXT,
CONSTRAINT fk_klanten FOREIGN KEY (eigenaar_id) REFERENCES klanten (id),
CONSTRAINT fk_autos FOREIGN KEY (auto_id) REFERENCES autos (id),
CONSTRAINT fk_kenteken FOREIGN KEY (kenteken_id) REFERENCES autos (kenteken)
);
