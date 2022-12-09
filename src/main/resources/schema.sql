CREATE SCHEMA IF NOT EXISTS just_in_case;

CREATE TABLE IF NOT EXISTS just_in_case.building (
                                     buildingID bigint NOT NULL IDENTITY,
                                     buildingName varchar(45) NOT NULL,
                                     description varchar(512) DEFAULT NULL,
                                     PRIMARY KEY (buildingID)
);

CREATE TABLE IF NOT EXISTS just_in_case.facility (
                                                   facilityID bigint NOT NULL IDENTITY,
                                                   facilityName varchar(45) NOT NULL,
  status varchar(45) NOT NULL,
  statusLastUpdated varchar(45) NOT NULL,
  PRIMARY KEY (facilityID)
);


CREATE TABLE IF NOT EXISTS just_in_case.livealertpost (
                                                        postID bigint NOT NULL IDENTITY,
                                                        postType varchar(45) NOT NULL,
  location varchar(45) NOT NULL,
  time datetime NOT NULL,
  numUpvotes int DEFAULT NULL,
  numDownvotes int DEFAULT NULL,
  PRIMARY KEY (postID)
  );




CREATE TABLE just_in_case.app_users (
                                      caseID varchar(45) NOT NULL,
                                      userName varchar(45) NOT NULL,
                                      postAnon tinyint NOT NULL,
                                      isAdmin tinyint NOT NULL,
                                      password varchar(45) NOT NULL,
                                      PRIMARY KEY (caseID)
);

CREATE TABLE IF NOT EXISTS just_in_case.posts (
                                                postID bigint NOT NULL,
                                                caseID varchar(45) NOT NULL,
  );

ALTER TABLE just_in_case.posts ADD FOREIGN KEY(caseID) REFERENCES just_in_case.app_users(caseID);
ALTER TABLE just_in_case.posts ADD FOREIGN KEY(postID) REFERENCES just_in_case.livealertpost(postID);



CREATE TABLE IF NOT EXISTS just_in_case.within (
                                                 buildingID bigint NOT NULL,
                                                 facilityID bigint NOT NULL,
  );

ALTER TABLE just_in_case.within ADD FOREIGN KEY(buildingID) REFERENCES just_in_case.building(buildingID);
ALTER TABLE just_in_case.within ADD FOREIGN KEY(facilityID) REFERENCES just_in_case.facility(facilityID);

