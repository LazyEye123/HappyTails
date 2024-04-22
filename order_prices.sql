-- Table: public.order_prices

-- DROP TABLE public.order_prices;

CREATE TABLE public.order_prices
(
    id bigint NOT NULL,
    walking_price real,
    furlough_price real,
    dogsitter_price real,
    CONSTRAINT order_prices_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.order_prices
    OWNER to postgres;