CREATE TABLE profiles(
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    address TEXT NOT NULL,
    address_number INTEGER NOT NULL,
    complement VARCHAR(255),
    address_code VARCHAR(8) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    role user_roles not null DEFAULT 'CLIENT',
    trading_name VARCHAR(255) NOT NULL  DEFAULT '',
    CONSTRAINT fk_users
                     FOREIGN KEY (user_id)
                        REFERENCES users(id)
);