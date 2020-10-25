# Intersects of LineStrings

Демо-проект на Spring Boot для проверки пересечения линий на карте.

[Пример geojson](/wiki_resources/lines.geojson).

Два эндпоинта:

curl -X POST -H "Content-Type: application/json" -d /geojson/ http://localhost:8080/intersections

curl http://localhost:8080/intersections?state=/state/

Визуализация geojson:
![Vizualization of geojson](/wiki_resources/example.png)