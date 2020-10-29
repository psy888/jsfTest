CREATE TABLE order_item
(
    id bigserial,
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

