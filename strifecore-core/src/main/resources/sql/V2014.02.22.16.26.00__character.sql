-- ----------------------------
-- Sequence structure for character_id_seq
-- ----------------------------
CREATE SEQUENCE "public"."character_id_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for character
-- ----------------------------
DROP TABLE IF EXISTS "public"."character";
CREATE TABLE "public"."character" (
  "id" int4 DEFAULT nextval('character_id_seq'::regclass) NOT NULL,
  "name" varchar(50) COLLATE "default" NOT NULL,
  "type" varchar(4) COLLATE "default" NOT NULL,
  "attribute_map" int4
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "public"."character_id_seq" OWNED BY "character"."id";

-- ----------------------------
-- Primary Key structure for table character
-- ----------------------------
ALTER TABLE "public"."character" ADD PRIMARY KEY ("id");

ALTER TABLE "public"."skill" DROP CONSTRAINT "skill_hero_fkey";

DROP TABLE "public"."hero";

ALTER TABLE "public"."skill" RENAME "hero" TO "character";

ALTER TABLE "public"."skill" ADD FOREIGN KEY ("character") REFERENCES "public"."character" ("id") ON DELETE CASCADE ON UPDATE CASCADE;