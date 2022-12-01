SET @region = 'Kyiv';
SELECT 
    regions.name AS 'Region Name',
    weathers.date AS 'Date',
    weathers.temperature AS 'Temperature',
    weathers.precipitation AS 'Precipitation'
FROM
    weathers
        INNER JOIN
    regions ON regions.regionId = weathers.regionId
WHERE regions.name = 'Kyiv';
