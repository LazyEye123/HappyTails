-- Table: public.animal_types

-- DROP TABLE public.animal_types;

CREATE TABLE public.animal_types
(
    id bigint NOT NULL,
    cat boolean,
    dog boolean,
    CONSTRAINT animal_types_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.animal_types
    OWNER to postgres;