USE MudHens;

-- -----------------------------------------------------
-- Create Neighborhood table
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Neighborhoods.csv' 
INTO TABLE NEIGHBORHOOD FIELDS TERMINATED BY ',' ENCLOSED BY '\'' LINES TERMINATED BY '\n' 
IGNORE 1 LINES
(@ignore, name, neighborhoodPK);
SET FOREIGN_KEY_CHECKS=1;

-- -----------------------------------------------------
-- Create Location staging table
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Location.csv' 
INTO TABLE LOCATIONSTAGING FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n' 
IGNORE 1 LINES
(LocationPK, Address, City, State, Zipcode, Latitude, Longitude, NeighborhoodFK);

-- -----------------------------------------------------
-- Insert cleaned data into Location table
-- -----------------------------------------------------
INSERT INTO Location (LocationPK,Address,City,State,Zipcode,Latitude,Longitude,NeighborhoodFK) 
SELECT LocationPK, Address, 'Toronto', 'ON', Zipcode, Latitude, Longitude, NeighborhoodFK 
FROM LocationStaging 
WHERE NeighborhoodFK != 0 AND LENGTH(Zipcode) < 8;

-- -----------------------------------------------------
-- Remove some invalid addresses in Location
-- -----------------------------------------------------
SET SQL_SAFE_UPDATES=0;
UPDATE Location 
SET Address=NULL
WHERE Address='Toronto, ON, Canada';
SET SQL_SAFE_UPDATES=1;

-- -----------------------------------------------------
-- Create AirBnB Table 
-- -----------------------------------------------------
/*

Running into an error - Row 1 truncated; contained more data than were input columns.
Moving on and come back to later
*/

-- This changes the restrictions on the size of the input, now when an input is too large it will simply truncate.
-- I think this is okay for us since these will only be the very large dscriptions etc...
SET @@SESSION.sql_mode = '';


SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Air.csv'
INTO TABLE AIRBNB FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@ignore, @ignore, @id, @summary, @ignore, @space, @description, @ignore, @ignore, @property_type,
@room_type, @ignore, @ignore, @bedrooms, @ignore, @bathrooms, @square_feet, @price, @weekly_price,
@monthly_price, @security_deposit, @ignore, @ignore, @ignore, @ignore, @review_scores_rating,
@ignore, @LocationKey)


SET AirPK = @id, Summary = @summary, Space = @space, Description = @description, PropertyType = @property_type,
RoomType = @room_type, Bedrooms = @bedrooms, Bathrooms = @bathrooms, SquareFeet = @square_feet,
Price = @price, WeeklyPrice = @weekly_price, MonthlyPrice = @monthly_price, 
SecurityDeposit = @security_deposit, Rating = IF(@review_scores_rating = '', NULL, @review_scores_rating), LocationFK = @LocationKey;
SET FOREIGN_KEY_CHECKS=1;


-- -----------------------------------------------------
-- Create Crime Table 
-- -----------------------------------------------------

SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Crimes.csv' 
INTO TABLE CRIME FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n' 
IGNORE 1 LINES
(@ignore, @ignore, @premisetype, @offense, @occurenceyear, @ignore, @ignore, @ignore, @ignore, @ignore,
@Long, @Lat, @neighbourhood_pk) 
SET PremiseType = @premisetype, Offense = @offense,
Year = @occurenceyear, Latitude = @Lat, Longitude = @Long, NeighborhoodFK = @neighbourhood_pk;
SET FOREIGN_KEY_CHECKS=1;

-- -----------------------------------------------------
-- Create Business Table 
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Biz.csv' 
INTO TABLE BUSINESS FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@ignore, @ignore, @name, @stars, @review_count, @attributes, @categories, @hours, @LocationKey)
SET Name = @name, Stars = @stars, ReviewCount = @review_count, Attributes = @attributes,
Categories = @categories, Hours = @hours, LocationFK = @LocationKey;
SET FOREIGN_KEY_CHECKS=1;

-- -----------------------------------------------------
-- Create FireIncident Table 
-- Had to clean table in python to remove blank nhoodPk and changed to -1. not sure if thats what
-- we want but had to change it for insert statement
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Fires.csv' 
INTO TABLE FIREINCIDENT FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore,
@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore,
@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore,
@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore,
@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, 
@ignore, @ignore, @neighbourhood_pk) SET Neighborhood_NeighborhoodPK = @neighbourhood_pk;
SET FOREIGN_KEY_CHECKS=1;

-- -----------------------------------------------------
-- SetUp AirBnB reviews INSERT 
--
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './Air_reviews.csv' 
INTO TABLE Reviews FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@listingID, @ID, @date, @ignore, @ignore, @comments)
SET ReviewPK = @ID,
	AirBnB_AirPK = @listingID,
    Date = @date,
    Comments = @comments;
SET FOREIGN_KEY_CHECKS=1;


-- -----------------------------------------------------
-- AirBnB calendar insert
-- -----------------------------------------------------

SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Air_Cal.csv' 
INTO TABLE CalendarEntry FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@listing_id, @date, @available, @price, @ignore, @ignore, @ignore)
SET IsAvailable = @available,
	Price = @price,
    Date = @date,
    AirBNBFK = @listing_id;
SET FOREIGN_KEY_CHECKS=1;



-- Bike Shops
SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Bicycle_Shops.csv' 
INTO TABLE BicycleShop FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @address, @zipcode, @ignore, @city, @ignore,
@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @longitude,
 @lat, @synthPK, @name, @ignore, @phone, @email, @hasrental, @ignore, @ignore, @ignore, @neighborhoodFK)
SET Name = @name,
	Phone = @phone,
    Email = @email,
    HasRental = @hasrental,
    NeighborhoodFK = @neighborhoodFK,
    Address = @address;
SET FOREIGN_KEY_CHECKS=1;
-- ------------------------------
-- Bike Parking
-- ------------------------------
SET FOREIGN_KEY_CHECKS=0;
LOAD DATA INFILE './V2_Bicycle_Parking.csv'
INTO TABLE BicycleParking FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @addNum, @addStreet, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore, @ignore,
@assetType, @isActive, @ignore, @ignore, @ignore, @long, @lat, @ignore, @ignore, @neighborhoodPK)
SET Address = @addNUm + ' ' + @addStreet,
    AssetType = @assetType,
	IsActive = @isActive,
    NeighborhoodFK = @neighborhoodPK;
SET FOREIGN_KEY_CHECKS=1;

