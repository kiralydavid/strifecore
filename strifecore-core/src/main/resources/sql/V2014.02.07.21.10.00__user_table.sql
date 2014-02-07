CREATE SEQUENCE "public"."users_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

CREATE TABLE "public"."users" (
"id" int4 DEFAULT nextval('users_id_seq'::regclass) NOT NULL,
"name" varchar(20) COLLATE "default" NOT NULL,
"password" varchar(60) COLLATE "default" NOT NULL,
"email" varchar(254) COLLATE "default" NOT NULL,
"active" bool DEFAULT false NOT NULL,
"admin" bool DEFAULT false NOT NULL
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "public"."users_id_seq" OWNED BY "users"."id";

ALTER TABLE "public"."users" ADD UNIQUE ("name");
ALTER TABLE "public"."users" ADD UNIQUE ("email");

ALTER TABLE "public"."users" ADD PRIMARY KEY ("id");
