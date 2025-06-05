INSERT INTO entity(entity_name) VALUES('Pedro Esteban');
INSERT INTO entity(entity_name) VALUES('Alex Alsina');

-- Create client table
CREATE TABLE IF NOT EXISTS client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    dni VARCHAR(20)
);

-- Add any other existing table creations here if needed
-- For example, if there was a ModelEntity table:
-- CREATE TABLE IF NOT EXISTS entity (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     entity_name VARCHAR(255) NOT NULL
-- );

-- You can also add some initial data if required
-- INSERT INTO client (name, address, phone, dni) VALUES ('John Doe', '123 Main St', '555-1234', '12345678A');