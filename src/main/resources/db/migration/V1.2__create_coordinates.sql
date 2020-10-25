CREATE TABLE IF NOT EXISTS point (
    point_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , geometry_id INT NOT NULL
    , x DOUBLE NOT NULL
    , y DOUBLE NOT NULL
    , CONSTRAINT fk_point FOREIGN KEY (geometry_id) REFERENCES geometry (geometry_id)
);