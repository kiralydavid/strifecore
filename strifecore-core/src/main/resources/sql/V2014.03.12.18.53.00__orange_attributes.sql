ALTER TABLE "public"."attribute" ALTER COLUMN "map" DROP NOT NULL;
ALTER TABLE "public"."attribute" ALTER COLUMN "name" DROP NOT NULL;

-- ----------------------------
-- Sequence structure for skill_orange_attribute_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."skill_orange_attribute_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;

 -- ----------------------------
-- Table structure for skill_orange_attribute
-- ----------------------------
DROP TABLE IF EXISTS "public"."skill_orange_attribute";
CREATE TABLE "public"."skill_orange_attribute" (
"id" int4 DEFAULT nextval('skill_orange_attribute_id_seq'::regclass) NOT NULL,
"skill" int4 NOT NULL,
"attribute" int4 NOT NULL,
"position" numeric(1) DEFAULT 0 NOT NULL
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "public"."skill_orange_attribute_id_seq" OWNED BY "skill_orange_attribute"."id";

-- ----------------------------
-- Primary Key structure for table skill_orange_attribute
-- ----------------------------
ALTER TABLE "public"."skill_orange_attribute" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."skill_orange_attribute"
-- ----------------------------
ALTER TABLE "public"."skill_orange_attribute" ADD FOREIGN KEY ("attribute") REFERENCES "public"."attribute" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."skill_orange_attribute" ADD FOREIGN KEY ("skill") REFERENCES "public"."skill" ("id") ON DELETE CASCADE ON UPDATE CASCADE;