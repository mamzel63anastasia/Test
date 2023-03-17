CREATE TABLE IF NOT EXISTS public.material
(
    id serial NOT NULL,
    name_material  text NOT NULL,
    text_material text,
    id_user integer,
    PRIMARY KEY (id),
    FOREIGN KEY (id_user)
     REFERENCES users (id)

);
