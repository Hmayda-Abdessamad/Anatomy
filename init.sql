-- docker exec -it postgres psql -U hmayda

CREATE DATABASE "3dObjects" OWNER hamza;

\connect "3dObjects"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: category; Type: TABLE; Schema: public; Owner: hamza
--

CREATE TABLE public.category (
                                 id integer NOT NULL,
                                 description character varying(255),
                                 image character varying(255),
                                 name character varying(255)
);


ALTER TABLE public.category OWNER TO hamza;

--
-- Name: category_seq; Type: SEQUENCE; Schema: public; Owner: hamza
--

CREATE SEQUENCE public.category_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.category_seq OWNER TO hamza;

--
-- Name: note; Type: TABLE; Schema: public; Owner: hamza
--

CREATE TABLE public.note (
                             id bigint NOT NULL,
                             content character varying(255),
                             objet_id integer,
                             user_id integer
);


ALTER TABLE public.note OWNER TO hamza;

--
-- Name: note_id_seq; Type: SEQUENCE; Schema: public; Owner: hamza
--

CREATE SEQUENCE public.note_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.note_id_seq OWNER TO hamza;

--
-- Name: note_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: hamza
--

ALTER SEQUENCE public.note_id_seq OWNED BY public.note.id;


--
-- Name: objet3d; Type: TABLE; Schema: public; Owner: hamza
--

CREATE TABLE public.objet3d (
                                id integer NOT NULL,
                                data character varying(255),
                                description character varying(255),
                                image character varying(255),
                                is_animated boolean,
                                name character varying(255),
                                taille double precision NOT NULL,
                                category_id integer
);


ALTER TABLE public.objet3d OWNER TO hamza;

--
-- Name: objet3d_seq; Type: SEQUENCE; Schema: public; Owner: hamza
--

CREATE SEQUENCE public.objet3d_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.objet3d_seq OWNER TO hamza;

--
-- Name: token; Type: TABLE; Schema: public; Owner: hamza
--

CREATE TABLE public.token (
                              id integer NOT NULL,
                              expired boolean NOT NULL,
                              revoked boolean NOT NULL,
                              token character varying(255),
                              token_type character varying(255),
                              user_id integer,
                              CONSTRAINT token_token_type_check CHECK (((token_type)::text = 'BEARER'::text))
);


ALTER TABLE public.token OWNER TO hamza;

--
-- Name: token_seq; Type: SEQUENCE; Schema: public; Owner: hamza
--

CREATE SEQUENCE public.token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.token_seq OWNER TO hamza;

--
-- Name: user-table; Type: TABLE; Schema: public; Owner: hamza
--

CREATE TABLE public."user-table" (
                                     id integer NOT NULL,
                                     email character varying(255),
                                     firstname character varying(255),
                                     lastname character varying(255),
                                     password character varying(255),
                                     role character varying(255),
                                     CONSTRAINT "user-table_role_check" CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying, 'MANAGER'::character varying])::text[])))
);


ALTER TABLE public."user-table" OWNER TO hamza;

--
-- Name: user-table_seq; Type: SEQUENCE; Schema: public; Owner: hamza
--

CREATE SEQUENCE public."user-table_seq"
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."user-table_seq" OWNER TO hamza;

--
-- Name: note id; Type: DEFAULT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.note ALTER COLUMN id SET DEFAULT nextval('public.note_id_seq'::regclass);


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: hamza
--

COPY public.category (id, description, image, name) FROM stdin;
\.


--
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: hamza
--

COPY public.note (id, content, objet_id, user_id) FROM stdin;
\.


--
-- Data for Name: objet3d; Type: TABLE DATA; Schema: public; Owner: hamza
--

COPY public.objet3d (id, data, description, image, is_animated, name, taille, category_id) FROM stdin;
\.


--
-- Data for Name: token; Type: TABLE DATA; Schema: public; Owner: hamza
--

COPY public.token (id, expired, revoked, token, token_type, user_id) FROM stdin;
\.


--
-- Data for Name: user-table; Type: TABLE DATA; Schema: public; Owner: hamza
--

COPY public."user-table" (id, email, firstname, lastname, password, role) FROM stdin;
1	admin@admin	admin	admin	$2a$10$dppxXJe7tZs8/e9di.nGtOHOsnNktxPEKDNxfgX8YflD.CwOl3J/W	ADMIN
\.


--
-- Name: category_seq; Type: SEQUENCE SET; Schema: public; Owner: hamza
--

SELECT pg_catalog.setval('public.category_seq', 1, false);


--
-- Name: note_id_seq; Type: SEQUENCE SET; Schema: public; Owner: hamza
--

SELECT pg_catalog.setval('public.note_id_seq', 1, false);


--
-- Name: objet3d_seq; Type: SEQUENCE SET; Schema: public; Owner: hamza
--

SELECT pg_catalog.setval('public.objet3d_seq', 1, false);


--
-- Name: token_seq; Type: SEQUENCE SET; Schema: public; Owner: hamza
--

SELECT pg_catalog.setval('public.token_seq', 1, false);


--
-- Name: user-table_seq; Type: SEQUENCE SET; Schema: public; Owner: hamza
--

SELECT pg_catalog.setval('public."user-table_seq"', 1, true);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: note note_pkey; Type: CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT note_pkey PRIMARY KEY (id);


--
-- Name: objet3d objet3d_pkey; Type: CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.objet3d
    ADD CONSTRAINT objet3d_pkey PRIMARY KEY (id);


--
-- Name: token token_pkey; Type: CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (id);


--
-- Name: token uk_pddrhgwxnms2aceeku9s2ewy5; Type: CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5 UNIQUE (token);


--
-- Name: user-table user-table_pkey; Type: CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public."user-table"
    ADD CONSTRAINT "user-table_pkey" PRIMARY KEY (id);


--
-- Name: token fkbk3lti6bu5kokffp7ld7w0a1u; Type: FK CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.token
    ADD CONSTRAINT fkbk3lti6bu5kokffp7ld7w0a1u FOREIGN KEY (user_id) REFERENCES public."user-table"(id);


--
-- Name: note fkeyqo7j55ccxe6jjua4xp2x5rn; Type: FK CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT fkeyqo7j55ccxe6jjua4xp2x5rn FOREIGN KEY (user_id) REFERENCES public."user-table"(id);


--
-- Name: note fklnj2w958jno05vits1op2wqah; Type: FK CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT fklnj2w958jno05vits1op2wqah FOREIGN KEY (objet_id) REFERENCES public.objet3d(id);


--
-- Name: objet3d fkna5hmku4k73sh4wkvjf0b9win; Type: FK CONSTRAINT; Schema: public; Owner: hamza
--

ALTER TABLE ONLY public.objet3d
    ADD CONSTRAINT fkna5hmku4k73sh4wkvjf0b9win FOREIGN KEY (category_id) REFERENCES public.category(id);



-------fill the  database

--- les catégories
INSERT INTO public.category (id, description, image, name) VALUES
                                                               (2, 'Dive into ''Anatomy by Regions'' to explore detailed insights into specific body areas. Uncover the complexities of organs and systems for a comprehensive understanding.', 'images-img02.png-c36fb9cd-ca52-4d8b-a4de-4c116a1b005b', 'Anatomy by Regions'),
                                                               (3, 'Explore the intricacies of the human body through ''Anatomy by Systems,'' categorizing organs and structures based on physiological systems. Gain comprehensive insights into how various systems collaborate to sustain life.', 'images-img01.png-d3b02797-407b-4127-b139-13d535ac0041', 'Anatomy by Systems'),
                                                               (4, 'Dive into ''Origins & Insertions'' to study the anatomical points where muscles originate and insert, unraveling the mechanics of movement and understanding muscle function.', 'images-img03.png-2187f0e4-c162-4b9a-8317-6786d2b0459c', 'Origins & Insertions'),
                                                               (5, 'Delve into ''Micro Anatomy'' to explore the intricate details of cells, tissues, and microscopic structures, providing a microscopic perspective on the building blocks of the human body.', 'images-img04.png-0e0b4a83-797b-46f4-bbbf-818efb988c4c', 'Micro Anatomy');



----- les organs
INSERT INTO public.objet3d (category_id, id, is_animated, taille, data, description, image, name) VALUES
                                                                                                      (2, 1, 'f', 5062656, 'objects-human_brain_cerebrum__brainstem.glb-6c6e3887-59f1-4e9f-9013-c9ed785cd616', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-human brain.png-48228c12-f6eb-451f-990f-f28267fcb6b0', 'Brain'),
                                                                                                      (2, 2, 'f', 3255220, 'objects-heart.glb-d409a74d-7d6e-4cd2-8f29-80d4876d67cc', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-heart.png-c18a22ce-1463-4b5b-a9b2-43fdaf973828', 'Human Heart'),
                                                                                                      (2, 4, 'f', 10210144, 'objects-thorax_and_abdomen_a_few_important_muscles.glb-7d3daab4-c501-4ed8-8cd9-4ad0a717081b', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview.png-ebd1a966-bd62-4a04-9db7-b40bf3321861', 'Male Abdomen'),
                                                                                                      (2, 3, 't', 5505596, 'objects-beating-heart.glb-39f81e1c-411d-4879-8941-6ca0bb176017', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-heart2.png-7a8d9bf7-694e-47cf-8320-00d21f9e8424', 'Beating Heart'),
                                                                                                      (3, 5, 'f', 30035020, 'objects-full_body.glb-2ea3292d-cb5c-4dd6-83da-ab55fed2cc72', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-full body.png-aff1be10-ca6a-48f2-b8f2-c210e4050194', 'Male Musculoskeletal System'),
                                                                                                      (3, 6, 'f', 15642140, 'objects-lymphatic_system_an_overview.glb-2441791b-15e1-4bb3-b5b7-3d62c825317f', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (1).png-3b8e9ba2-068c-4d2e-8086-9f78f793bb12', 'Lymphatic System'),
                                                                                                      (3, 7, 'f', 7702740, 'objects-urinary_tract.glb-b94ceab1-59ab-4214-a90e-d0b2575e9432', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (2).png-54842051-a41d-494b-b14f-e3952ed7bb8b', 'Male Urinary System'),
                                                                                                      (3, 8, 'f', 19074920, 'objects-human_skeleton_high_detailed.glb-898e5349-4f84-4abe-9f58-8c56a0b4de6a', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-skeleton.png-c4ece6d2-c82d-44dd-85e0-e3c3e66fb5f7', 'Male Skeletal System'),
                                                                                                      (3, 9, 'f', 48764784, 'objects-anatomy_of_vision_part_2.glb-e77a44db-7ba0-4158-8b74-44a16cc6f7a5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-1.png-49507b9d-3bea-4e3a-ba25-af06b4c5f6b1', 'Male Visual System'),
                                                                                                      (3, 10, 't', 6837248, 'objects-respiratory_system.glb-8cf39820-75d1-4a53-9ac3-1c9dcb03f7b6', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (4).png-2258d2ef-e402-4aae-a347-52efc6982773', 'Respiratory System'),
                                                                                                      (3, 11, 'f', 3678080, 'objects-realistic_human_stomach.glb-8d0ccd31-317e-4041-8a8b-40f086479ae7', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-human stomach.png-ce393f1e-af79-4294-a7fb-d6177d3a2c41', 'Digestive System'),
                                                                                                      (5, 12, 'f', 7630236, 'objects-realistic_human_lungs.glb-c2da2aad-f690-4619-bcd5-b027b50c37ea', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-lungs.png-b685b71f-5319-4e35-8b9b-584914c0475f', 'Lungs: Coronal Cross Section'),
                                                                                                      (5, 13, 'f', 7014712, 'objects-kidney (1).glb-751078e1-67ff-4b8c-9cc7-5071964cd1ba', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (5).png-63f92203-070e-475a-9035-9ce741be6a00', 'Kidney: Coronal Cross Section'),
                                                                                                      (5, 14, 'f', 6898312, 'objects-human_liver_and_gallbladder.glb-ca8f98bc-c4bf-4a94-a778-8bee3e3c13a3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (6).png-88038bb3-efb1-4ac2-83b5-7e7635b1ca11', 'Human Liver'),
                                                                                                      (5, 15, 'f', 8078492, 'objects-kidney.glb-e3f215e8-8127-4989-b19a-01b56805a074', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-kidney.png-6e506cf7-27ca-41f4-ad23-674058e3064b', 'Human Kidney'),
                                                                                                      (5, 16, 'f', 6974036, 'objects-lung_right.glb-851c757e-8a2f-45fa-9ace-a09e4be09869', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (7).png-0410361b-778b-4550-8921-f6901fb416ed', 'Hilum of the Lung'),
                                                                                                      (4, 17, 'f', 4650908, 'objects-human_skull.glb-1e7923fc-b6f3-42b1-8dd2-851800828b77', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (8).png-cd253b1f-de15-4845-9720-ab4cc8332b56', 'Skull: Origins & Insertions'),
                                                                                                      (4, 18, 'f', 6854700, 'objects-3d_thorax_model.glb-db6b383c-e0b8-4349-9a50-56b972bb351c', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (9).png-7193d085-a037-4954-9e98-d896799b5353', 'Human Thorax'),
                                                                                                      (4, 19, 'f', 2338264, 'objects-anartomy_-_blood_supply_to_the_lower_limb.glb-897549ae-886d-41c2-9d31-8ec9e237f847', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (10).png-19813562-ace3-4424-8077-daf36d695146', 'Human Lower Limb'),
                                                                                                      (4, 20, 'f', 12394076, 'objects-bones_of_the_hand..glb-4f216a95-cf0d-41dc-a5aa-0f6af3e03d70', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (11).png-f1a02c81-9a2e-4853-88ee-10ba1a7855ca', 'Bones of the Hand'),
                                                                                                      (4, 52, 'f', 12394076, 'objects-human_mouth_detailed.glb-a695b8c5-d437-4fb9-8e3d-42be8c883c09', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (3).png-f1d19cf6-bd59-402c-903c-ad182615ed74', 'Human mouth'),
                                                                                                      (4, 53, 'f', 12394076, 'objects-permanent_dentition.glb-f3f796e3-6365-4b0f-b4d3-5d62232450c1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (1).png-8fa9b6ae-6706-4a3f-ad1c-1b6029deb708', 'Human teeth'),
                                                                                                      (4, 54, 'f', 12394076, 'objects-mandible.glb-01e7ca2f-000d-4964-b14c-9d5ea4ef902c', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (4).png-3e91f7b4-408b-4c75-93aa-7a7d42d7a28a', 'Human  lower jaw'),
                                                                                                      (4, 55, 'f', 12394076, 'objects-post-mortem_pink_teeth.glb-2c29c91c-0359-4ae5-bcd9-225a007b600e', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. In lobortis, massa vitae consequat posuere, mi lacus imperdiet turpis', 'images-image-removebg-preview (2).png-7757159e-3c47-4c99-94ca-b317cf41bca9', 'Human dental (damaged)');
