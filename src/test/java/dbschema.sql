CREATE SCHEMA IF NOT EXISTS just_in_case;
CREATE TABLE IF NOT EXISTS just_in_case.building (
    `buildingID` bigint AUTO_INCREMENT NOT NULL UNIQUE,
    `buildingName` varchar(45) NOT NULL,
    `description` varchar(512) DEFAULT NULL,
    PRIMARY KEY (`buildingID`)
);

CREATE TABLE IF NOT EXISTS just_in_case.facility (
  `facilityID` bigint NOT NULL AUTO_INCREMENT UNIQUE,
  `facilityName` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `statusLastUpdated` varchar(45) NOT NULL,
  PRIMARY KEY (`facilityID`)
);

CREATE TABLE IF NOT EXISTS just_in_case.livealertpost (
  `postID` bigint NOT NULL AUTO_INCREMENT UNIQUE,
  `postType` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `time` datetime NOT NULL,
  `numUpvotes` int DEFAULT NULL,
  `numDownvotes` int DEFAULT NULL,
  PRIMARY KEY (`postID`)
);

CREATE TABLE IF NOT EXISTS just_in_case.users (
  `caseID` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `postAnon` tinyint NOT NULL,
  `isAdmin` tinyint NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`caseID`)
);

CREATE TABLE IF NOT EXISTS just_in_case.posts (
  `postID` bigint NOT NULL UNIQUE,
  `caseID` varchar(45) NOT NULL,
  CONSTRAINT `fk_postID` FOREIGN KEY (`postID`) REFERENCES `livealertpost` (`postID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_caseID` FOREIGN KEY (`caseID`) REFERENCES `users` (`caseID`)
);

CREATE TABLE IF NOT EXISTS just_in_case.within (
  `buildingID` bigint NOT NULL,
  `facilityID` bigint NOT NULL UNIQUE,
  CONSTRAINT `fk_buildingID` FOREIGN KEY (`buildingID`) REFERENCES `building` (`buildingID`),
  CONSTRAINT `fk_facilityID` FOREIGN KEY (`facilityID`) REFERENCES `facility` (`facilityID`) ON DELETE CASCADE ON UPDATE CASCADE
);