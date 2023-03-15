CREATE TABLE IF NOT EXISTS public.test
(
    id serial NOT NULL,
    name_test character varying(100) NOT NULL,
    text_test text,
    ball integer NOT NULL,
    PRIMARY KEY (id)
);
