CREATE TABLE IF NOT EXISTS geometry (
    geometry_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , feature_id INT NOT NULL
    , type VARCHAR NOT NULL
    , CONSTRAINT fk_geometry FOREIGN KEY (feature_id) REFERENCES feature (feature_id)
);