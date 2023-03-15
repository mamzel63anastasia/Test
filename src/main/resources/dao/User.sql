CREATE TABLE IF NOT EXISTS public.users
(
    id serial NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    fio character varying(100) NOT NULL,
    mail character varying(100) NOT NULL,
    tab_number character varying(10),
    department character varying(20),
    id_role integer,
    PRIMARY KEY (id),
    FOREIGN KEY (id_role)
     REFERENCES roles (id)

);
