# Intersects of LineStrings

Демо-проект на Spring Boot для проверки пересечения линий на карте.

curl -X POST -H "Content-Type: application/json" -d @resources/lines.geojson http://localhost:8080/intersections
curl http://localhost:8080/intersections?state=<state>

![Map](/resources/example.png)
