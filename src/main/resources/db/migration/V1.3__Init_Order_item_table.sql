CREATE TABLE order_item
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    quantity integer NOT NULL,
    coffee_order_id bigint,
    coffee_type_id bigint
);
ALTER TABLE order_item
    ADD CONSTRAINT order_item_pkey PRIMARY KEY (id);

ALTER TABLE order_item
        ADD CONSTRAINT order_constraint FOREIGN KEY (coffee_order_id)
        REFERENCES coffee_order (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE order_item
    ADD CONSTRAINT coffee_constraint FOREIGN KEY (coffee_type_id)
    REFERENCES coffee_type (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

