SET @lang = 'Romanian';
SELECT 
    regions.name AS 'Region Name',
    weathers.date AS 'Date',
    weathers.temperature AS 'Temperature',
    weathers.precipitation AS 'Precipitation',
    populations.language AS 'Population`s Language'
FROM
    weathers
        INNER JOIN
    regions ON regions.regionId = weathers.regionId
        INNER JOIN
    regionspopulations ON regions.regionId = regionspopulations.regionId
        INNER JOIN
    populations ON populations.populationId = regionspopulations.populationId
WHERE
    YEARWEEK(weathers.date, 5) = YEARWEEK(NOW() - INTERVAL 1 WEEK, 5)
        AND populations.language LIKE CONCAT(CONCAT('%', @lang), '%');
    