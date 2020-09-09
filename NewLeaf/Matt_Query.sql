USE `Mudhens`;

-- Which 10 neighborhoods have the most restaurants and food options?
-- Could also filter this where Stars > x for 'quality restauraunts'

SELECT Name as Neighborhood, Cnt_Restaurants FROM 
(SELECT NeighborhoodFK, Count(*) AS Cnt_Restaurants FROM Business 
LEFT JOIN Location ON Business.LocationFK = Location.LocationPK 
WHERE (Categories LIKE "%Food%" OR Categories LIKE "%Restaurants%")
GROUP BY NeighborhoodFK) AS q1
LEFT JOIN Neighborhood ON Neighborhood.NeighborhoodPK = q1.NeighborhoodFK 
ORDER BY Cnt_Restaurants DESC LIMIT 10;

-- Which Neighborhoods have the most popular Restaurants? 

SELECT Name as Neighborhood, Cnt_Restaurants FROM (SELECT NeighborhoodFK, Count(*) AS Cnt_Restaurants FROM Business 
LEFT JOIN Location ON Business.LocationFK = Location.LocationPK 
WHERE (Categories LIKE "%Food%" OR Categories LIKE "%Restaurants%") 
AND Stars > 3.5 AND ReviewCount > 50
GROUP BY NeighborhoodFK) AS q1
LEFT JOIN Neighborhood ON Neighborhood.NeighborhoodPK = q1.NeighborhoodFK 
ORDER BY Cnt_Restaurants DESC LIMIT 10;

-- Which neighborhoods have the most ethnic food options?

SELECT Name as Neighborhood, Cnt_Restaurants FROM (SELECT NeighborhoodFK, Count(*) AS Cnt_Restaurants FROM Business 
LEFT JOIN Location ON Business.LocationFK = Location.LocationPK 
WHERE (Categories LIKE "%Food%" OR Categories LIKE "%Restaurants%") 
AND (Categories LIKE "%Chinese%" OR Categories LIKE "%Korean%" OR Categories LIKE "%Japanese%"
OR Categories LIKE "%Indian%" OR Categories LIKE "%Ethnic Food%")
GROUP BY NeighborhoodFK) AS q1
LEFT JOIN Neighborhood ON Neighborhood.NeighborhoodPK = q1.NeighborhoodFK 
ORDER BY Cnt_Restaurants DESC LIMIT 10;

-- What is the avg price/bedroom  in neighborhood?

SELECT Name as Neighborhood, price_per_bedroom FROM (SELECT NeighborhoodFK, AVG(cast(substr(Price, 2) as decimal(8, 2))/Bedrooms) as price_per_bedroom
FROM AirBnB LEFT JOIN Location ON AirBnB.LocationFK = Location.LocationPK 
WHERE cast(substr(Price, 2) as decimal(8, 2))/Bedrooms IS NOT NULL GROUP BY NeighborhoodFK) as q1
LEFT JOIN Neighborhood ON Neighborhood.NeighborhoodPK = q1.NeighborhoodFK 
ORDER BY price_per_bedroom DESC;

