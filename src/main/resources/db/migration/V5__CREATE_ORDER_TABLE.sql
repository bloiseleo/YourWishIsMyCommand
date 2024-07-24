CREATE TYPE order_status AS ENUM ('OPEN', 'EVALUATING', 'WAIT_PAYMENT', 'IN_PROGRESS', 'REJECTED', 'FINISHED');
CREATE CAST (varchar as order_status) WITH INOUT AS IMPLICIT;
CREATE CAST ( order_status as varchar ) WITH INOUT AS IMPLICIT;
CREATE TABLE IF NOT EXISTS orders(
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    professional_id INTEGER NOT NULL,
    description TEXT NOT NULL,
    medias TEXT NOT NULL,
    status order_status NOT NULL DEFAULT 'OPEN',
    CONSTRAINT fk_clients
        FOREIGN KEY (client_id)
            REFERENCES profiles(id),
    CONSTRAINT fk_professional
        FOREIGN KEY (professional_id)
                REFERENCES profiles(id)
);