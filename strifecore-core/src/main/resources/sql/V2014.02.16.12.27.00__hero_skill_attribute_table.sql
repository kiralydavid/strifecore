-- ----------------------------
-- Sequence structure for attribute_fixed_val_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."attribute_fixed_val_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for attribute_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."attribute_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for attribute_map_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."attribute_map_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for hero_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."hero_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for skill_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."skill_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for attribute
-- ----------------------------
DROP TABLE IF EXISTS "public"."attribute";
CREATE TABLE "public"."attribute" (
  "id" int4 DEFAULT nextval('attribute_id_seq'::regclass) NOT NULL,
  "value" numeric(6,2),
  "increase_per_level" numeric(6,2),
  "max_level" numeric(6,2),
  "map" int4 NOT NULL,
  "name" varchar(30) COLLATE "default" NOT NULL,
  "type" numeric(1) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for attribute_fixed_val
-- ----------------------------
DROP TABLE IF EXISTS "public"."attribute_fixed_val";
CREATE TABLE "public"."attribute_fixed_val" (
  "id" int4 DEFAULT nextval('attribute_fixed_val_id_seq'::regclass) NOT NULL,
  "attribute" int4 NOT NULL,
  "value" numeric(6,2) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for attribute_map
-- ----------------------------
DROP TABLE IF EXISTS "public"."attribute_map";
CREATE TABLE "public"."attribute_map" (
  "id" int4 DEFAULT nextval('attribute_map_id_seq'::regclass) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for hero
-- ----------------------------
DROP TABLE IF EXISTS "public"."hero";
CREATE TABLE "public"."hero" (
  "id" int4 DEFAULT nextval('hero_id_seq'::regclass) NOT NULL,
  "name" varchar(30) COLLATE "default" NOT NULL,
  "attribute_map" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS "public"."skill";
CREATE TABLE "public"."skill" (
  "id" int4 DEFAULT nextval('skill_id_seq'::regclass) NOT NULL,
  "name" varchar(50) COLLATE "default" NOT NULL,
  "description" text COLLATE "default" NOT NULL,
  "slot" numeric(1) NOT NULL,
  "attribute_map" int4 NOT NULL,
  "hero" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By
-- ----------------------------
ALTER SEQUENCE "public"."attribute_fixed_val_id_seq" OWNED BY "attribute_fixed_val"."id";
ALTER SEQUENCE "public"."attribute_id_seq" OWNED BY "attribute"."id";
ALTER SEQUENCE "public"."attribute_map_id_seq" OWNED BY "attribute_map"."id";
ALTER SEQUENCE "public"."hero_id_seq" OWNED BY "hero"."id";
ALTER SEQUENCE "public"."skill_id_seq" OWNED BY "skill"."id";

-- ----------------------------
-- Primary Key structure for table attribute
-- ----------------------------
ALTER TABLE "public"."attribute" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table attribute_fixed_val
-- ----------------------------
ALTER TABLE "public"."attribute_fixed_val" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table attribute_map
-- ----------------------------
ALTER TABLE "public"."attribute_map" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table hero
-- ----------------------------
ALTER TABLE "public"."hero" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table skill
-- ----------------------------
ALTER TABLE "public"."skill" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."attribute"
-- ----------------------------
ALTER TABLE "public"."attribute" ADD FOREIGN KEY ("map") REFERENCES "public"."attribute_map" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."attribute_fixed_val"
-- ----------------------------
ALTER TABLE "public"."attribute_fixed_val" ADD FOREIGN KEY ("attribute") REFERENCES "public"."attribute" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."hero"
-- ----------------------------
ALTER TABLE "public"."hero" ADD FOREIGN KEY ("attribute_map") REFERENCES "public"."attribute_map" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT;

-- ----------------------------
-- Foreign Key structure for table "public"."skill"
-- ----------------------------
ALTER TABLE "public"."skill" ADD FOREIGN KEY ("attribute_map") REFERENCES "public"."attribute_map" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE "public"."skill" ADD FOREIGN KEY ("hero") REFERENCES "public"."hero" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "public"."skill" ADD UNIQUE ("slot", "hero");
