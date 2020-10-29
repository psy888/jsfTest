CREATE TABLE coffee_type
(
    id bigserial,
    is_enabled boolean NOT NULL,
    name character varying(255),
    price double precision NOT NULL
);
ALTER TABLE coffee_type
    ADD CONSTRAINT coffee_type_pkey PRIMARY KEY (id);
