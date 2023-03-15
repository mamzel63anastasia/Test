CREATE TABLE IF NOT EXISTS public.answers
(
    id serial NOT NULL,
    text_answer character varying(400) NOT NULL,
    correct_answer boolean NOT NULL,
    PRIMARY KEY (id)
);
