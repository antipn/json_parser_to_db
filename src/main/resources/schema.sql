DROP TABLE IF EXISTS purchases;
DROP SEQUENCE IF EXISTS global_seq_purchases;

DROP TABLE IF EXISTS customers;
DROP SEQUENCE IF EXISTS global_seq_customers;

DROP TABLE IF EXISTS products;
DROP SEQUENCE IF EXISTS global_seq_products;

DROP TABLE IF EXISTS w_days;

-- ______________________________________________________--

CREATE SEQUENCE global_seq_customers START WITH 1 INCREMENT BY 1;
CREATE TABLE customers
(
    id        SMALLINT PRIMARY KEY DEFAULT nextval('global_seq_customers'),
    first_name VARCHAR(20),
    last_name  VARCHAR(20)
);

CREATE SEQUENCE global_seq_products START WITH 1 INCREMENT BY 1;
CREATE TABLE products
(
    id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq_products'),
    product_name  VARCHAR(30) NOT NULL,
    product_price INTEGER     NOT NULL
);

CREATE SEQUENCE global_seq_purchases START WITH 1 INCREMENT BY 1;
CREATE TABLE purchases
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_purchases'),
    id_customer       INTEGER NOT NULL,
    id_product        INTEGER NOT NULL,
    quantity_purchase INTEGER NOT NULL,
    date_purchase     DATE    NOT NULL,

    FOREIGN KEY (id_customer) REFERENCES customers (id) ON DELETE CASCADE,
    FOREIGN KEY (id_product) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE w_days(working_days date);