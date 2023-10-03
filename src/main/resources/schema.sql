DROP TABLE IF EXISTS purchases;
DROP SEQUENCE IF EXISTS global_seq_purchases;

DROP TABLE IF EXISTS customers;
DROP SEQUENCE IF EXISTS global_seq_customers;

DROP TABLE IF EXISTS products;
DROP SEQUENCE IF EXISTS global_seq_products;

-- ______________________________________________________--

CREATE SEQUENCE global_seq_customers START WITH 1 INCREMENT BY 1;
CREATE TABLE customers
(
    id        SMALLINT PRIMARY KEY DEFAULT nextval('global_seq_customers'),
    firstName VARCHAR(20),
    lastName  VARCHAR(20)
);

CREATE SEQUENCE global_seq_products START WITH 1 INCREMENT BY 1;
CREATE TABLE products
(
    id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq_products'),
    productName  VARCHAR(30) NOT NULL,
    productPrice INTEGER     NOT NULL
);

CREATE SEQUENCE global_seq_purchases START WITH 1 INCREMENT BY 1;
CREATE TABLE purchases
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq_purchases'),
    idCustomer       INTEGER NOT NULL,
    idProduct        INTEGER NOT NULL,
    quantityPurchase INTEGER NOT NULL,
    datePurchase     DATE    NOT NULL,

    FOREIGN KEY (idCustomer) REFERENCES customers (id) ON DELETE CASCADE,
    FOREIGN KEY (idProduct) REFERENCES products (id) ON DELETE CASCADE
);
