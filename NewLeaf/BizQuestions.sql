
USE MudHens;
/*
1. Whats the average  price of an X bedroom in neighborhood Y?
*/
SELECT Name, AVG(Price) AS Average_Price
FROM
(SELECT AirPK, Bedrooms, CAST(REPLACE(Price,'$' ,'') AS UNSIGNED) As Price, N.Name, NeighborhoodPK
FROM AirBnB A
LEFT JOIN
location L
ON A.LocationFk = L.LocationPK
LEFT JOIN 
Neighborhood N
ON L.NeighborhoodFK = N.NeighborhoodPK
WHERE Bedrooms = 3) Temp
WHERE Name LIKE 'Regent Park';


/*
 which months are booked most in neighborhood X?
*/
SELECT  N.Name, MONTH(Date) As Month,  COUNT(CalendarEntryPK) AS count
FROM CalendarEntry C
LEFT JOIN 
AirBnB A
ON C.AirBNBFK = A.AirPK
LEFT JOIN
location L
ON A.LocationFk = L.LocationPK
LEFT JOIN 
Neighborhood N
ON L.NeighborhoodFK = N.NeighborhoodPK
WHERE N.Name = 'Little Portugal' AND IsAvailable = 'FALSE'
GROUP BY Month
ORDER BY count DESC;


/*
 In which neighborhoods os the phrase 'X' most often written in reviews?
*/
SELECT N.Name AS Neighborhood, COUNT(ReviewPK) AS count
FROM Reviews R
JOIN AirBnB A
ON R.AirBnB_AirPK = A.AirPK
JOIN Location L
ON A.LocationFK = L.LocationPK
JOIN Neighborhood N
ON L.NeighborhoodFK = N.NeighborhoodPK
WHERE Comments LIKE '% nice view %'
GROUP BY Neighborhood
ORDER BY count DESC;

