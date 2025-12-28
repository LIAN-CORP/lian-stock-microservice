-- V1__init.sql
-- Migración inicial: creación de tablas principales

-- Habilitar la extensión uuid-ossp para generación de UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS  category (
   id uuid DEFAULT uuid_generate_v4() NOT NULL,
   name varchar(50) NOT NULL,
   description varchar(150) NOT NULL,
   CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS subcategory (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category_id UUID NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE IF NOT EXISTS product (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    stock INTEGER NOT NULL,
    price_sell numeric(10, 2) NOT NULL,
    price_buy numeric(10, 2) NOT NULL,
    subcategory_id uuid NOT NULL,
    image_path text NULL,
    CONSTRAINT price_buy_check CHECK ((price_buy > (0)::numeric)),
    CONSTRAINT price_sell_check CHECK ((price_sell > (0)::numeric)),
    CONSTRAINT stock_check CHECK ((stock >= 0)),
    CONSTRAINT fk_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategory(id)
);
