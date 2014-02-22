-- ----------------------------
-- Sequence structure for entity_order_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."entity_order_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

 -- ----------------------------
-- Sequence structure for item_order_element_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."item_order_element_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

 -- ----------------------------
-- Sequence structure for skill_order_element_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."skill_order_element_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

 -- ----------------------------
-- Table structure for entity_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."entity_order";
CREATE TABLE "public"."entity_order" (
"id" int4 DEFAULT nextval('entity_order_id_seq'::regclass) NOT NULL,
"title" varchar(100) COLLATE "default" NOT NULL,
"comment" varchar(200) COLLATE "default" NOT NULL,
"position" numeric(1) NOT NULL,
"type" numeric(1) NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Table structure for item_order_element
-- ----------------------------
DROP TABLE IF EXISTS "public"."item_order_element";
CREATE TABLE "public"."item_order_element" (
"id" int4 DEFAULT nextval('item_order_element_id_seq'::regclass) NOT NULL,
"entity_order" int4 NOT NULL,
"item" int4 NOT NULL,
"position" numeric(2) NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
-- Table structure for skill_order_element
-- ----------------------------
DROP TABLE IF EXISTS "public"."skill_order_element";
CREATE TABLE "public"."skill_order_element" (
"id" int4 DEFAULT nextval('skill_order_element_id_seq'::regclass) NOT NULL,
"entity_order" int4 NOT NULL,
"skill_slot" numeric(1) NOT NULL,
"position" numeric(2) NOT NULL
)
WITH (OIDS=FALSE);


 ALTER SEQUENCE "public"."entity_order_id_seq" OWNED BY "entity_order"."id";
 ALTER SEQUENCE "public"."item_order_element_id_seq" OWNED BY "item_order_element"."id";
 ALTER SEQUENCE "public"."skill_order_element_id_seq" OWNED BY "skill_order_element"."id";

 -- ----------------------------
-- Primary Key structure for table entity_order
-- ----------------------------
ALTER TABLE "public"."entity_order" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table item_order_element
-- ----------------------------
ALTER TABLE "public"."item_order_element" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table skill_order_element
-- ----------------------------
ALTER TABLE "public"."skill_order_element" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."item_order_element"
-- ----------------------------
ALTER TABLE "public"."item_order_element" ADD FOREIGN KEY ("item") REFERENCES "public"."item" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE "public"."item_order_element" ADD FOREIGN KEY ("entity_order") REFERENCES "public"."entity_order" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."skill_order_element"
-- ----------------------------
ALTER TABLE "public"."skill_order_element" ADD FOREIGN KEY ("entity_order") REFERENCES "public"."entity_order" ("id") ON DELETE CASCADE ON UPDATE CASCADE;