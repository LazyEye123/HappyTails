-- Table: public.rating

-- DROP TABLE public.rating;

CREATE TABLE public.rating
(
    id bigint NOT NULL,
    one integer,
    two integer,
    three integer,
    four integer,
    five integer,
    CONSTRAINT rating_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.rating
    OWNER to postgres;