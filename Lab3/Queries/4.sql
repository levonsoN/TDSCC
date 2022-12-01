SET @area = 33000;
SELECT 
    AVG(weathers.temperature) AS 'AVG Temperature'
FROM
    weathers
        INNER JOIN
    regions ON regions.regionId = weathers.regionId
WHERE
    YEARWEEK(weathers.date, 5) = YEARWEEK(NOW() - INTERVAL 1 WEEK, 5) 
    AND regions.area > @area;
    