CREATE USER 'user1'@'localhost' IDENTIFIED BY 'user1';
CREATE SCHEMA musicDB1;
GRANT ALL PRIVILEGES ON musicDB1.* TO 'user1'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'user2'@'localhost' IDENTIFIED BY 'user2';
CREATE SCHEMA musicDB2;
GRANT ALL PRIVILEGES ON musicDB2.* TO 'user2'@'localhost';
FLUSH PRIVILEGES;


/*in case of java.sql.SQLException: The server timezone value 'UTC' is unrecognized or represents more than one timezone. */
-- SET GLOBAL time_zone = '+3:00';