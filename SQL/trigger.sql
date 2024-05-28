DROP TABLE VetClinic.Doctor_services;
SELECT * FROM doctor_services;
DELETE FROM Doctors WHERE Doctor_id>20;
/*Создание таблицы для хранения данных*/
CREATE TABLE VetClinic.Doctor_services
(
	Doctor_id INT PRIMARY KEY,
    Second_name VARCHAR(30),
    Services_number_on_appointment INT,
    Services_number_in_hospital INT
);
DROP TRIGGER New_appointment_services;
/*Триггер, прикрепленный к таблице Услуги_на_приеме*/
CREATE TRIGGER New_appointment_services AFTER INSERT
ON Appointment_services FOR EACH ROW
INSERT INTO Doctor_services 
	SELECT Doctor_id, Second_name, Services_number_on_appointment, Services_number_in_hospital
    FROM (
    Doctors LEFT JOIN
		(SELECT Doctor_id, count(*) AS Services_number_on_appointment
        FROM Appointment_services
        GROUP BY Doctor_id) AS From_appointments
	USING (Doctor_id) 
	LEFT JOIN
        (SELECT Doctor_id, count(*) AS Services_number_in_hospital
        FROM Hospital_services
        GROUP BY Doctor_id) AS From_hospital 
        USING (Doctor_id) )
ON DUPLICATE KEY UPDATE Services_number_on_appointment=From_appointments.Services_number_on_appointment;

DROP TRIGGER New_hospital_services;
/*Триггер, прикрепленный к таблице Услуги_в_стационаре*/
CREATE TRIGGER New_hospital_services AFTER INSERT
ON Hospital_services FOR EACH ROW
INSERT INTO Doctor_services 
	SELECT Doctor_id, Second_name, Services_number_on_appointment, Services_number_in_hospital
    FROM (
    Doctors LEFT JOIN
		(SELECT Doctor_id, count(*) AS Services_number_on_appointment
        FROM Appointment_services
        GROUP BY Doctor_id) AS From_appointments
	USING (Doctor_id) 
	LEFT JOIN
        (SELECT Doctor_id, count(*) AS Services_number_in_hospital
        FROM Hospital_services
        GROUP BY Doctor_id) AS From_hospital 
        USING (Doctor_id) )
ON DUPLICATE KEY UPDATE Services_number_in_hospital=From_hospital.Services_number_in_hospital;

DROP TRIGGER New_doctors;
/*Триггер, прикрепленный к таблице Доктора*/
CREATE TRIGGER New_doctors AFTER INSERT
ON Doctors FOR EACH ROW
INSERT INTO Doctor_services 
	SELECT Doctor_id, Second_name, Services_number_on_appointment, Services_number_in_hospital
    FROM (
		(SELECT Doctor_id, count(*) AS Services_number_on_appointment
        FROM Appointment_services
        GROUP BY Doctor_id) AS From_appointments
	JOIN
        (SELECT Doctor_id, count(*) AS Services_number_in_hospital
        FROM Hospital_services
        GROUP BY Doctor_id) AS From_hospital 
        USING (Doctor_id)
	RIGHT JOIN
        Doctors USING (Doctor_id) )
ON DUPLICATE KEY UPDATE Second_name=Doctors.Second_name, 
						Services_number_on_appointment=From_appointments.Services_number_on_appointment,
                        Services_number_in_hospital=From_hospital.Services_number_in_hospital;