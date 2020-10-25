CREATE TABLE IF NOT EXISTS property (
    property_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY
    , feature_id INT NOT NULL
    , key VARCHAR NOT NULL
    , value VARCHAR NOT NULL
    , CONSTRAINT fk_property FOREIGN KEY (feature_id) REFERENCES feature (feature_id)
);