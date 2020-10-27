CREATE TABLE coffee_order
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    delivery_address character varying(255),
    delivery_person character varying(255),
    order_date_time timestamp without time zone,
    total_sum double precision NOT NULL
);
ALTER TABLE coffee_order
    ADD CONSTRAINT coffee_order_pkey PRIMARY KEY (id);