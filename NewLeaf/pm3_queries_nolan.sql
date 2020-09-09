-- ------------------------------------------------------
-- What 10 neighborhoods have the least crimes reported?
-- ------------------------------------------------------
SELECT Name, COUNT(NeighborhoodFK) as Crimes 
FROM Mudhens.Crime
LEFT JOIN Mudhens.Neighborhood
ON NeighborhoodFK = NeighborhoodPK
GROUP BY NeighborhoodFK
ORDER BY Crimes ASC
LIMIT 10;

-- -------------------------------------------------------------
-- What 10 neighborhood has the least available rental property?
-- -------------------------------------------------------------
SELECT Name, AirBnBs
FROM Mudhens.Neighborhood
JOIN (
SELECT NeighborhoodFK, COUNT(AirPK) as AirBnBs 
FROM Mudhens.AirBNB 
LEFT JOIN Mudhens.Location ON LocationFK = LocationPK
GROUP BY NeighborhoodFK
) as Listings ON NeighborhoodPK = NeighborhoodFK
ORDER BY AirBnBs ASC
LIMIT 10;

-- --------------------------------------------------------------------------------
-- What are the top 20 neighborhoods in terms of average bar & restaurant rating?
-- --------------------------------------------------------------------------------
SELECT Name, Rating
FROM 
(SELECT NeighborhoodFK, Rating FROM
(
SELECT LocationFK, AVG(Stars) as Rating
FROM Mudhens.Business
WHERE Categories LIKE '%Restaurants%' OR '%Bars%'
GROUP BY LocationFK
) as Ratings
LEFT JOIN Mudhens.Location ON LocationPK = LocationFK
) as NeighborhoodRatings
LEFT JOIN Mudhens.Neighborhood on NeighborhoodFK = NeighborhoodPK
GROUP BY Name
ORDER BY Rating DESC
LIMIT 20;

-- -----------------------------------------------------------
-- What are people charging for a security deposit in Toronto? 
-- -----------------------------------------------------------
SELECT SecurityDeposit as AverageDeposit
FROM Mudhens.AirBnB 
WHERE SecurityDeposit != "" AND SecurityDeposit != "$0.00";


-- --------------------------------------------------------------
-- What are the best neighborhoods for biking (ranked by parking)
-- --------------------------------------------------------------
SELECT Name, ParkingSpots FROM
(
SELECT NeighborhoodFK, COUNT(BicycleParkingPK) as ParkingSpots
FROM Mudhens.BicycleParking 
WHERE IsActive = "Existing" 
GROUP BY NeighborhoodFK
) as Spots
JOIN Mudhens.Neighborhood ON NeighborhoodFK = NeighborhoodPK
ORDER BY ParkingSpots DESC;



