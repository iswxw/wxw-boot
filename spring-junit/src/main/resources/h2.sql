DROP TABLE IF EXISTS ticket;

CREATE TABLE ticket (
      ticket_id INT AUTO_INCREMENT  PRIMARY KEY,
      passenger_name VARCHAR(250) NOT NULL,
      from_station VARCHAR(250) NOT NULL,
      to_station VARCHAR(250) DEFAULT NULL,
      booking_date DATE NOT NULL,
      email VARCHAR(250) DEFAULT NULL
);
