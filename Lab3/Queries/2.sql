SET @region = 'Kyiv';
SET @precipitation = 'Snow';
SET @temperature = -10;
SELECT 
    weathers.date AS 'Date',
    weathers.precipitation AS 'Precipitation',
    weathers.temperature AS 'Temperature'
FROM
    weathers
        INNER JOIN
    regions ON regions.regionId = weathers.regionId
WHERE
    regions.name = @region
        AND weathers.precipitation = @precipitation
        AND weathers.temperature < @temperature