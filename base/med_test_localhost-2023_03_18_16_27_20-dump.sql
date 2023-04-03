CREATE TABLE IF NOT EXISTS public.role
(
    id serial NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.users
(
    id serial NOT NULL,
    login character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(50) COLLATE pg_catalog."default" NOT NULL,
    fio character varying(100) COLLATE pg_catalog."default",
    mail character varying(100) COLLATE pg_catalog."default",
    tab_number character varying(10) COLLATE pg_catalog."default",
    department character varying(20) COLLATE pg_catalog."default",
    id_role integer NOT NULL DEFAULT 1,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT id_role FOREIGN KEY (id_role)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.material
(
    id serial NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    txt text COLLATE pg_catalog."default",
    id_user integer NOT NULL,
    CONSTRAINT material_pkey PRIMARY KEY (id),
    CONSTRAINT id_user FOREIGN KEY (id_user)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.material
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.test
(
    id serial NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    ball integer NOT NULL,
    id_user integer NOT NULL,
    id_material integer,
    CONSTRAINT test_pkey PRIMARY KEY (id),
    CONSTRAINT id_user FOREIGN KEY (id_user)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    CONSTRAINT id_material FOREIGN KEY (id_material)
        REFERENCES public.material (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.test
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.questionData
(
    id serial NOT NULL,
    txt text COLLATE pg_catalog."default" NOT NULL ,
    id_test integer NOT NULL,
    CONSTRAINT question_pkey PRIMARY KEY (id),
    CONSTRAINT id_test FOREIGN KEY (id_test)
        REFERENCES public.test (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE

)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.questionData
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.answerData
(
    id serial NOT NULL,
    txt text COLLATE pg_catalog."default" NOT NULL,
    correct int  NOT NULL,
    id_question integer NOT NULL,
    CONSTRAINT answer_pkey PRIMARY KEY (id),
    CONSTRAINT id_question FOREIGN KEY (id_question)
        REFERENCES public.questionData (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE

)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.answerData
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.get_test
(
    id serial NOT NULL,
    id_users integer NOT NULL,
    id_test integer,
    id_material integer,
    id_admin integer NOT NULL,
    CONSTRAINT get_test_pkey PRIMARY KEY (id),
    CONSTRAINT id_users FOREIGN KEY (id_users)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    CONSTRAINT id_test FOREIGN KEY (id_test)
        REFERENCES public.test (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    CONSTRAINT id_material FOREIGN KEY (id_material)
        REFERENCES public.material (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,

    CONSTRAINT id_admin FOREIGN KEY (id_admin)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.answerData
    OWNER to postgres;


