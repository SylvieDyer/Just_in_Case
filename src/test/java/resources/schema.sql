
CREATE TABLE just_in_case.building (
                                     "buildingID" bigint unsigned NOT NULL AUTO_INCREMENT,
                                     "buildingName" varchar(45) NOT NULL,
                                     "description" varchar(512) DEFAULT NULL,
                                     PRIMARY KEY ("buildingID"),
                                     UNIQUE KEY "buildingID_UNIQUE" ("buildingID")
);

CREATE TABLE IF NOT EXISTS just_in_case.facility (
                                                   "facilityID" bigint unsigned NOT NULL AUTO_INCREMENT,
                                                   "facilityName" varchar(45) NOT NULL,
  "status" varchar(45) NOT NULL,
  "statusLastUpdated" varchar(45) NOT NULL,
  PRIMARY KEY ("facilityID"),
  UNIQUE KEY "facilityID_UNIQUE" ("facilityID")
  );


CREATE TABLE IF NOT EXISTS just_in_case.livealertpost (
                                                        "postID" bigint unsigned NOT NULL AUTO_INCREMENT,
                                                        "postType" varchar(45) NOT NULL,
  "location" varchar(45) NOT NULL,
  "time" datetime NOT NULL,
  "numUpvotes" int DEFAULT NULL,
  "numDownvotes" int DEFAULT NULL,
  PRIMARY KEY ("postID"),
  UNIQUE KEY "postID_UNIQUE1" ("postID")
  );




CREATE TABLE just_in_case.app_users (
                                      "caseID" varchar(45) NOT NULL,
                                      "userName" varchar(45) NOT NULL,
                                      "postAnon" tinyint NOT NULL,
                                      "isAdmin" tinyint NOT NULL,
                                      "password" varchar(45) NOT NULL,
                                      PRIMARY KEY ("caseID")
);

CREATE TABLE IF NOT EXISTS just_in_case.posts (
                                                "postID" bigint unsigned NOT NULL,
                                                "caseID" varchar(45) NOT NULL,
  UNIQUE KEY "postID_UNIQUE2" ("postID")
  );

ALTER TABLE just_in_case.posts ADD FOREIGN KEY("caseID") REFERENCES just_in_case.app_users("caseID");
ALTER TABLE just_in_case.posts ADD FOREIGN KEY("postID") REFERENCES just_in_case.livealertpost("postID");



CREATE TABLE IF NOT EXISTS just_in_case.within (
                                                 "buildingID" bigint unsigned NOT NULL,
                                                 "facilityID" bigint unsigned NOT NULL,
                                                 UNIQUE KEY "facilityID_UNIQUE2" ("facilityID")
  );

ALTER TABLE just_in_case.within ADD FOREIGN KEY("buildingID") REFERENCES just_in_case.building("buildingID");
ALTER TABLE just_in_case.within ADD FOREIGN KEY("facilityID") REFERENCES just_in_case.facility("facilityID");

