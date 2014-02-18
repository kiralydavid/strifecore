-- ----------------------------
-- Sequence structure for bonus_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."bonus_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for component_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."component_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for item_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."item_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

 -- ----------------------------
-- Sequence structure for item_subcomponent_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."item_subcomponent_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS "public"."bonus";
CREATE TABLE "public"."bonus" (
"id" int4 DEFAULT nextval('bonus_id_seq'::regclass) NOT NULL,
"type" varchar(15) COLLATE "default" NOT NULL,
"amount" numeric(4,1) NOT NULL,
"component" int4 NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Table structure for component
-- ----------------------------
DROP TABLE IF EXISTS "public"."component";
CREATE TABLE "public"."component" (
"id" int4 DEFAULT nextval('component_id_seq'::regclass) NOT NULL,
"name" varchar(100) COLLATE "default" NOT NULL,
"craft_value" numeric(2) NOT NULL,
"price" numeric(4) NOT NULL,
"type" varchar(10) COLLATE "default" NOT NULL,
"description" text COLLATE "default" NOT NULL,
"image" varchar(103) COLLATE "default" NOT NULL,
"dev_name" varchar(100) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS "public"."item";
CREATE TABLE "public"."item" (
"id" int4 DEFAULT nextval('item_id_seq'::regclass) NOT NULL,
"component" int4 NOT NULL,
"crafter" int4
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Table structure for item_subcomponent
-- ----------------------------
DROP TABLE IF EXISTS "public"."item_subcomponent";
CREATE TABLE "public"."item_subcomponent" (
"id" int4 DEFAULT nextval('item_subcomponent_id_seq'::regclass) NOT NULL,
"item" int4 NOT NULL,
"component" int4 NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Alter Sequences Owned By
-- ----------------------------
ALTER SEQUENCE "public"."bonus_id_seq" OWNED BY "bonus"."id";
ALTER SEQUENCE "public"."component_id_seq" OWNED BY "component"."id";
ALTER SEQUENCE "public"."item_id_seq" OWNED BY "item"."id";
ALTER SEQUENCE "public"."item_subcomponent_id_seq" OWNED BY "item_subcomponent"."id";

-- ----------------------------
-- Primary Key structure for table bonus
-- ----------------------------
ALTER TABLE "public"."bonus" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table component
-- ----------------------------
ALTER TABLE "public"."component" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table item
-- ----------------------------
ALTER TABLE "public"."item" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table item_subcomponent
-- ----------------------------
ALTER TABLE "public"."item_subcomponent" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."bonus"
-- ----------------------------
ALTER TABLE "public"."bonus" ADD FOREIGN KEY ("component") REFERENCES "public"."component" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."item"
-- ----------------------------
ALTER TABLE "public"."item" ADD FOREIGN KEY ("component") REFERENCES "public"."component" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."item" ADD FOREIGN KEY ("crafter") REFERENCES "public"."users" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."item_subcomponent"
-- ----------------------------
ALTER TABLE "public"."item_subcomponent" ADD FOREIGN KEY ("item") REFERENCES "public"."item" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."item_subcomponent" ADD FOREIGN KEY ("component") REFERENCES "public"."component" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT;