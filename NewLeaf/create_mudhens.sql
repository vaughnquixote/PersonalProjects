-- -----------------------------------------------------
-- Database MudHens
-- -----------------------------------------------------
DROP DATABASE IF EXISTS MudHens;
CREATE DATABASE MudHens;
USE MudHens;

-- -----------------------------------------------------
-- Conditional drop statements on all tables in MudHens
-- -----------------------------------------------------
DROP TABLE IF EXISTS Neighborhood;
DROP TABLE IF EXISTS Crime;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS LocationStaging;
DROP TABLE IF EXISTS Business;
DROP TABLE IF EXISTS AirBnB;
DROP TABLE IF EXISTS BicycleParkingStaging;
DROP TABLE IF EXISTS BicycleParking;
DROP TABLE IF EXISTS BicycleShopStaging;
DROP TABLE IF EXISTS BicycleShop;
DROP TABLE IF EXISTS FireIncident;
DROP TABLE IF EXISTS CalendarEntry;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS CrimeRatings;
DROP TABLE IF EXISTS BenchPrice;
DROP TABLE IF EXISTS CrimeRatings;


-- -----------------------------------------------------
-- Table Neighborhood
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Neighborhood (
  `NeighborhoodPK` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`NeighborhoodPK`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table Crime
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Crime (
  `CrimePK` INT NOT NULL AUTO_INCREMENT,
  `PremiseType` VARCHAR(45) NOT NULL,
  `Offense` VARCHAR(45) NOT NULL,
  `Year` VARCHAR(45) NOT NULL,
  `Latitude` VARCHAR(45) NOT NULL,
  `Longitude` VARCHAR(45) NOT NULL,
  `NeighborhoodFK` INT NOT NULL,
  PRIMARY KEY (`CrimePK`),
  INDEX `fk_Crime_Neighborhood_idx` (`NeighborhoodFK` ASC) VISIBLE,
  CONSTRAINT `fk_Crime_Neighborhood`
    FOREIGN KEY (`NeighborhoodFK`)
    REFERENCES Neighborhood (`NeighborhoodPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table Location
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Location (
  `LocationPK` INT NOT NULL AUTO_INCREMENT,
  `Address` VARCHAR(100) NULL,
  `City` VARCHAR(45) NULL,
  `State` VARCHAR(2) NULL,
  `Zipcode` VARCHAR(7) NULL,
  `Latitude` VARCHAR(45) NULL,
  `Longitude` VARCHAR(45) NULL,
  `NeighborhoodFK` INT NOT NULL,
  PRIMARY KEY (`LocationPK`),
  INDEX `fk_Location_Neighborhood1_idx` (`NeighborhoodFK` ASC) VISIBLE,
  CONSTRAINT `fk_Location_Neighborhood1`
    FOREIGN KEY (`NeighborhoodFK`)
    REFERENCES Neighborhood (`NeighborhoodPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
-- -----------------------------------------------------
-- Table Location_Staging
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS LocationStaging (
  `LocationPK` INT NOT NULL AUTO_INCREMENT,
  `Address` VARCHAR(100) NULL,
  `City` VARCHAR(100) NULL,
  `State` VARCHAR(100) NULL,
  `Zipcode` VARCHAR(100) NULL,
  `Latitude` VARCHAR(100) NULL,
  `Longitude` VARCHAR(100) NULL,
  `NeighborhoodFK` INT NULL,
  PRIMARY KEY (`LocationPK`),
  INDEX `fk_Location_Staging_Neighborhood1_idx` (`NeighborhoodFK` ASC) VISIBLE,
  CONSTRAINT `fk_Location_Staging_Neighborhood1`
    FOREIGN KEY (`NeighborhoodFK`)
    REFERENCES Neighborhood (`NeighborhoodPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table Business
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Business (
  `ParksPK` INT NOT NULL AUTO_INCREMENT, #changed to auto increment by matt 6/5
  `Name` VARCHAR(100) NOT NULL,
  `Stars` DECIMAL(4,1) NOT NULL, #changed to 4, 1 by matt 6/5
  `ReviewCount` INT NULL,
  `Attributes` LONGTEXT NULL, #changed to LONGTEXT by matt 6/5
  `Categories` LONGTEXT NULL, #changed to LONGTEXT by matt 6/5
  `Hours` VARCHAR(1000) NULL, #changed to 1000 by matt 6/5
  `LocationFK` INT NOT NULL,
  PRIMARY KEY (`ParksPK`, `LocationFK`),
  INDEX `fk_Parks_Location1_idx` (`LocationFK` ASC) VISIBLE,
  CONSTRAINT `fk_Parks_Location1`
    FOREIGN KEY (`LocationFK`)
    REFERENCES Location (`LocationPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table AirBnB
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS AirBnB (
  `AirPK` INT NOT NULL AUTO_INCREMENT,
  `Summary` VARCHAR(500) NULL,
  `Space` LONGTEXT NULL,
  `Description` LONGTEXT NULL, 
  `PropertyType` VARCHAR(45) NULL,
  `RoomType` VARCHAR(45) NULL,
  `Bedrooms` VARCHAR(45) NULL,
  `Bathrooms` VARCHAR(45) NULL,
  `SquareFeet` DECIMAL(7,2) NULL,
  `Price` VARCHAR(50) NULL,
  `WeeklyPrice` VARCHAR(50) NULL,
  `MonthlyPrice` VARCHAR(50) NULL,
  `SecurityDeposit` VARCHAR(50) NULL,
  `Rating` INT NULL,
  `LocationFK` INT NOT NULL,
  PRIMARY KEY (`AirPK`, `LocationFK`),
  INDEX `fk_AirBnB_Location1_idx` (`LocationFK` ASC) VISIBLE,
  CONSTRAINT `fk_AirBnB_Location1`
    FOREIGN KEY (`LocationFK`)
    REFERENCES Location (`LocationPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table BicycleParking
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS BicycleParking (
  `BicycleParkingPK` INT NOT NULL AUTO_INCREMENT,
  `AssetType` VARCHAR(45) NULL,
  `IsActive` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `NeighborhoodFK` INT NOT NULL,
  PRIMARY KEY (`BicycleParkingPK`),
  CONSTRAINT `fk_BicycleParking_Neighborhood`
    FOREIGN KEY (`NeighborhoodFK`)
    REFERENCES Neighborhood (`NeighborhoodPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table BicycleShop
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS BicycleShop (
  `BicycleShopPK` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  `Email` VARCHAR(100) NULL,
  `HasRental` VARCHAR(10),
  `Address` VARCHAR(45),
  `NeighborhoodFK` INT NOT NULL,
  PRIMARY KEY (`BicycleShopPK`),
  CONSTRAINT `fk_BicycleShop_Neighborhood`
    FOREIGN KEY (`NeighborhoodFK`)
    REFERENCES Neighborhood(`NeighborhoodPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
-- -----------------------------------------------------
-- Table FireIncident
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS FireIncident (
  `FireIncidentPK` INT NOT NULL AUTO_INCREMENT, #changed to auto_incrememnt by matt 6/5
  `Neighborhood_NeighborhoodPK` INT NOT NULL,
  PRIMARY KEY (`FireIncidentPK`, `Neighborhood_NeighborhoodPK`),
  INDEX `fk_FireIncident_Neighborhood1_idx` (`Neighborhood_NeighborhoodPK` ASC) VISIBLE,
  CONSTRAINT `fk_FireIncident_Neighborhood1`
    FOREIGN KEY (`Neighborhood_NeighborhoodPK`)
    REFERENCES Neighborhood (`NeighborhoodPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table CalendarEntry
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS CalendarEntry (
  `CalendarEntryPK` INT NOT NULL AUTO_INCREMENT,
  `IsAvailable` VARCHAR(10) NULL,
  `Price` VARCHAR(45) NULL,
  `Date` DATE NULL,
  `AirBNBFK` INT NOT NULL,
  PRIMARY KEY (`CalendarEntryPK`, `AirBNBFK`),
  INDEX `fk_CalendarEntry_AirBnB1_idx` (`AirBNBFK` ASC) VISIBLE,
  CONSTRAINT `fk_CalendarEntry_AirBnB1`
    FOREIGN KEY (`AirBNBFK`)
    REFERENCES AirBnB (`AirPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table Review
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Reviews (
  `ReviewPK` INT NOT NULL AUTO_INCREMENT,
  `AirBnB_AirPK` INT NOT NULL,
  `Date` DATE NULL,
  `Comments` TEXT,
  PRIMARY KEY (`ReviewPK`,`AirBnB_AirPK`),
  CONSTRAINT `fk_Review_AirBnB1`
    FOREIGN KEY (`AirBnB_AirPK`)
    REFERENCES AirBnB (`AirPK`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
-- -----------------------------------------------------
-- Table TennisCourt
-- -----------------------------------------------------
DROP TABLE IF EXISTS TennisCourt;
CREATE TABLE IF NOT EXISTS TennisCourt (
	`TennisCourtPK` INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(100),
    `ParkName` VARCHAR(100),
    `Address` VARCHAR(100),
    `District` VARCHAR(50),
    `Ward` VARCHAR(50),
    `PermitStatus` VARCHAR(50),
    `AssetCategory` VARCHAR(50),
    `Lights` VARCHAR(5),
    `SurfaceMaterial` VARCHAR(50),
    `GisCoordinates` VARCHAR(50), 
    `NeighbourhoodFK` INT NOT NULL,
    PRIMARY KEY (`TennisCourtPK`),
    CONSTRAINT `fk_tennis_court_neighborhood_pk`
    FOREIGN KEY (`NeighbourhoodFK`) REFERENCES Neighborhood(`NeighborhoodPK`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
    
    
-- -----------------------------------------------------
-- Table CrimeRatings
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CrimeRatings (
  `CrimeRatingsPK` INT NOT NULL AUTO_INCREMENT,
  `NeighborhoodFK` INT NOT NULL,
  `Neighborhood` VARCHAR(100) NOT NULL,
  `AvgStars` DECIMAL(12,2) NOT NULL,
  `NumCrimes` LONG NOT NULL,
  PRIMARY KEY (`CrimeRatingsPK`),
  CONSTRAINT `fk_crime_rating_neighborhood_pk`
  FOREIGN KEY (`NeighborhoodFK`) REFERENCES Neighborhood(`NeighborhoodPK`)
  ON DELETE CASCADE 
  ON UPDATE CASCADE);
  

CREATE TABLE IF NOT EXISTS BenchPrice (
	`BenchPricePK` INT PRIMARY KEY AUTO_INCREMENT,
	`NeighborhoodFK` INT NOT NULL,
	`Neighborhood` VARCHAR(100) NOT NULL,
    `BenchCount` INT NOT NULL,
    `AvgPrice` FLOAT NOT NULL,
    `AvgPricePerBench` FLOAT NOT NULL,
    CONSTRAINT `fk_BenchPrice_neighborhood_pk`
	FOREIGN KEY (`NeighborhoodFK`) REFERENCES Neighborhood(`NeighborhoodPK`)
	ON DELETE CASCADE 
	ON UPDATE CASCADE
);
