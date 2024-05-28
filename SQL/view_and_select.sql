DROP VIEW IF EXISTS Appointments_number;

CREATE VIEW Appointments_number AS
SELECT Doctors.Doctor_id AS Doctor_id, Second_name, Number_of_appointments, Number_of_hospital_appointments
FROM
Doctors LEFT JOIN 
  (SELECT Doctor_id, count(*) AS Number_of_appointments
  FROM
    (SELECT Doctor_id, Appointment_id
     FROM appointment_services
     GROUP BY Doctor_id,Appointment_id) AS Doctors_and_Appointments
  GROUP BY Doctor_id
  ORDER BY Doctor_id) AS From_appointments
  ON (From_appointments.Doctor_id=Doctors.Doctor_id)
LEFT JOIN
  (SELECT Doctor_id, count(*) AS Number_of_hospital_appointments
  FROM
    (SELECT Doctor_id, Hospital_id
     FROM hospital_services
     GROUP BY Doctor_id,Hospital_id) AS Doctors_and_Hospitals
  GROUP BY Doctor_id) AS From_hospital
ON (Doctors.Doctor_id=From_hospital.Doctor_id);

SELECT * FROM appointments_number;

explain SELECT DISTINCT Second_name, Number_of_appointments, Moniker
FROM appointment_services JOIN appointments USING (Appointment_id)
JOIN Patients USING (Patient_id)
JOIN Appointments_number USING (Doctor_id)
WHERE Doctor_id=1;