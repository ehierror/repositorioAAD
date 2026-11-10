-- creamos el usuario en la primera vez
CREATE USER IF NOT EXISTS 'erik'@'%' IDENTIFIED BY 'patata';

GRANT ALL PRIVILEGES ON airTortilla.* TO 'erik'@'localhost';

FLUSH PRIVILEGES;