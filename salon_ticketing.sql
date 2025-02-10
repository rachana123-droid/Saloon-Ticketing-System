CREATE DATABASE salon_ticketing;
USE salon_ticketing;
CREATE TABLE tickets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    services TEXT NOT NULL,
    total_amount INT NOT NULL
);
SELECT * FROM salon_ticketing.tickets;
