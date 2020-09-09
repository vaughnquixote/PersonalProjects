USE MudHens;

-- find number of bars within 3 km of lat long 43.65 and -79.4
SELECT COUNT(*) FROM(
    SELECT *, (((acos(sin((43.65*pi()/180)) 
    * sin((Latitude*pi()/180))+cos((43.65*pi()/180)) 
    * cos((Latitude*pi()/180)) 
    * cos(((-79.4 - Longitude)*pi()/180))))*180/pi())*60*1.1515*1.609344) 
    as distance FROM Location INNER JOIN Business ON Location.LocationPK = Business.LocationFK) as t
WHERE t.distance <= 3
 and (Categories LIKE '%Bars%' OR Categories LIKE '%Pubs%' OR Categories LIKE '%bars%' OR Categories LIKE '%pubs%');

-- Whats the average monthly price of 3 bedrooms in neighborhood x? 
SELECT AVG(CAST(REPLACE(TRIM('$' from MonthlyPrice), ',','') as float))
FROM ((SELECT * FROM Neighborhood WHERE Name = 'Alderwood') as N 
	INNER JOIN Location as L ON N.NeighborhoodPK = L.NeighborhoodFK) 
    INNER JOIN AirBnB as A ON A.LocationFK = L.LocationPK
WHERE MonthlyPrice != '';


-- Neighborhood with least expensive 2 beds per night on average
SELECT Name, AVG(CAST(REPLACE(TRIM('$' from Price), ',','') as float)) as AvgPrice
FROM Neighborhood as N 
	INNER JOIN Location as L ON N.NeighborhoodPK = L.NeighborhoodFK
    INNER JOIN AirBnB as A ON A.LocationFK = L.LocationPK
GROUP BY N.Name
ORDER BY AvgPrice;