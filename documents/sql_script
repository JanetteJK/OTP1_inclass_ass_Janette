DROP DATABASE IF EXISTS temperatureconverter;

CREATE DATABASE temperatureconverter;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON temperatureconverter.* TO 'user'@'localhost';

CREATE TABLE units (
   unit_id INT AUTO_INCREMENT NOT NULL,
   short VARCHAR (30) NOT NULL,
   PRIMARY KEY(unit_id)
);

CREATE TABLE units_translations (
   unit_id INT NOT NULL,
   lang VARCHAR (30) NOT NULL,
   name VARCHAR (30) NOT NULL,
   PRIMARY KEY(unit_id, lang),
   FOREIGN KEY (unit_id) REFERENCES units(unit_id) ON DELETE CASCADE
) CHARSET=utf8MB4;

CREATE TABLE temperature_records (
   record_id INT AUTO_INCREMENT NOT NULL,
   temperature_value double NOT NULL,
   temperature_start_unit VARCHAR (30) NOT NULL,
   temperature_unit VARCHAR (30) NOT NULL,
   timestamp DATETIME NOT NULL,
   PRIMARY KEY(record_id)
);

INSERT IGNORE INTO UNITS (short) VALUES ('C'), ('F'), ('K');

INSERT IGNORE INTO units_translations (unit_id, lang, name)
SELECT u.unit_id, u.short, v.lang, v.name FROM units u JOIN (
    SELECT 'C' as short, 'en' as lang, 'Celsius' as name UNION ALL
    SELECT 'C' as short, 'fi' as lang, 'Celsius' as name UNION ALL
    SELECT 'C' as short, 'sv' as lang, 'Celsius' as name UNION ALL
    SELECT 'C' as short, 'jp' as lang, '摂氏' as name UNION ALL
    SELECT 'C' as short, 'ar' as lang, 'فهرنهايت' as name UNION ALL
    SELECT 'F' as short, 'en' as lang, 'Fahrenheit' as name UNION ALL
    SELECT 'F' as short, 'fi' as lang, 'Fahrenheit' as name UNION ALL
    SELECT 'F' as short, 'sv' as lang, 'Fahrenheit' as name UNION ALL
    SELECT 'F' as short, 'jp' as lang, '華氏' as name UNION ALL
    SELECT 'F' as short, 'ar' as lang, 'كلفن' as name UNION ALL
    SELECT 'K' as short, 'en' as lang, 'Kelvin' as name UNION ALL
    SELECT 'K' as short, 'fi' as lang, 'Kelvin' as name UNION ALL
    SELECT 'K' as short, 'sv' as lang, 'Kelvin' as name UNION ALL
    SELECT 'K' as short, 'jp' as lang, 'ケルビン' as name UNION ALL
    SELECT 'K' as short, 'ar' as lang, 'لا شيء يمكن تحويله' as name
) v ON u.short = v.short;


INSERT IGNORE INTO units_translations (unit_id, lang, name)
SELECT u.unit_id, v.lang, v.name FROM units u JOIN (
    SELECT 'C' short, 'en' as lang, 'Celsius' as name UNION ALL
    SELECT 'C', 'fi' as lang, 'Celsius' as name UNION ALL
    SELECT 'C', 'sv' as lang, 'Celsius' as name UNION ALL
    SELECT 'C', 'jp' as lang, '摂氏' as name UNION ALL
    SELECT 'C', 'ar' as lang, 'فهرنهايت' as name UNION ALL
    SELECT 'F', 'en' as lang, 'Fahrenheit' as name UNION ALL
    SELECT 'F', 'fi' as lang, 'Fahrenheit' as name UNION ALL
    SELECT 'F', 'sv' as lang, 'Fahrenheit' as name UNION ALL
    SELECT 'F', 'jp' as lang, '華氏' as name UNION ALL
    SELECT 'F', 'ar' as lang, 'كلفن' as name UNION ALL
    SELECT 'K', 'en' as lang, 'Kelvin' as name UNION ALL
    SELECT 'K', 'fi' as lang, 'Kelvin' as name UNION ALL
    SELECT 'K', 'sv' as lang, 'Kelvin' as name UNION ALL
    SELECT 'K', 'jp' as lang, 'ケルビン' as name UNION ALL
    SELECT 'K', 'ar' as lang, 'لا شيء يمكن تحويله' as name
) v ON u.short = v.short;


