CREATE TABLE blood_pressure_reading
(
  id serial PRIMARY KEY,
  comments TEXT DEFAULT NULL,
  systolic_reading INTEGER NOT NULL,
  diastolic_reading INTEGER NOT NULL,
  user_id INT NOT NULL,

  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP DEFAULT NOW(),

  CONSTRAINT fk_user_id
	  FOREIGN KEY (user_id) 
	  	REFERENCES users(id)
	  		ON DELETE CASCADE
);