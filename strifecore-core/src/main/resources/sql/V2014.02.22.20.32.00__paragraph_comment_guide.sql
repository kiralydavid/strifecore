-- ----------------------------
-- Sequence structure for comment_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."comment_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for guide_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."guide_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

 -- ----------------------------
-- Sequence structure for paragraph_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."paragraph_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

 -- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS "public"."comment";
CREATE TABLE "public"."comment" (
"id" int4 DEFAULT nextval('comment_id_seq'::regclass) NOT NULL,
"author" int4 NOT NULL,
"created" timestamp(6) NOT NULL,
"content" text COLLATE "default" NOT NULL,
"guide" int4 NOT NULL
)
WITH (OIDS=FALSE);

 -- ----------------------------
-- Table structure for guide
-- ----------------------------
DROP TABLE IF EXISTS "public"."guide";
CREATE TABLE "public"."guide" (
"id" int4 DEFAULT nextval('guide_id_seq'::regclass) NOT NULL,
"title" varchar(100) COLLATE "default" NOT NULL,
"author" int4 NOT NULL,
"created" timestamp(6) NOT NULL,
"last_edited" timestamp(6) NOT NULL,
"pet" int4 NOT NULL,
"hero" int4 NOT NULL,
"published" bool NOT NULL,
"active" bool NOT NULL,
"upvotes" numeric(5) NOT NULL,
"downvotes" numeric(5) NOT NULL,
"views" numeric(6) NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Table structure for paragraph
-- ----------------------------
DROP TABLE IF EXISTS "public"."paragraph";
CREATE TABLE "public"."paragraph" (
"id" int4 DEFAULT nextval('paragraph_id_seq'::regclass) NOT NULL,
"title" varchar(300) COLLATE "default" NOT NULL,
"content" text COLLATE "default" NOT NULL,
"position" numeric(2) NOT NULL,
"guide" int4 NOT NULL
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "public"."comment_id_seq" OWNED BY "comment"."id";
ALTER SEQUENCE "public"."guide_id_seq" OWNED BY "guide"."id";
ALTER SEQUENCE "public"."paragraph_id_seq" OWNED BY "paragraph"."id";

-- ----------------------------
-- Primary Key structure for table comment
-- ----------------------------
ALTER TABLE "public"."comment" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table guide
-- ----------------------------
ALTER TABLE "public"."guide" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table paragraph
-- ----------------------------
ALTER TABLE "public"."paragraph" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."comment"
-- ----------------------------
ALTER TABLE "public"."comment" ADD FOREIGN KEY ("guide") REFERENCES "public"."guide" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."comment" ADD FOREIGN KEY ("author") REFERENCES "public"."users" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."guide"
-- ----------------------------
ALTER TABLE "public"."guide" ADD FOREIGN KEY ("author") REFERENCES "public"."users" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."guide" ADD FOREIGN KEY ("hero") REFERENCES "public"."character" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."guide" ADD FOREIGN KEY ("pet") REFERENCES "public"."character" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."paragraph"
-- ----------------------------
ALTER TABLE "public"."paragraph" ADD FOREIGN KEY ("guide") REFERENCES "public"."guide" ("id") ON DELETE CASCADE ON UPDATE CASCADE;



ALTER TABLE "public"."entity_order" ADD COLUMN "guide" int4 NOT NULL;

ALTER TABLE "public"."entity_order" ADD FOREIGN KEY ("guide") REFERENCES "public"."guide" ("id") ON DELETE CASCADE ON UPDATE CASCADE;