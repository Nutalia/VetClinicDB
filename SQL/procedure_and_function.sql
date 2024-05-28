/* Пример вызова
call services_between_dates(10, '2019-06-12', '2020-05-13'); */

delimiter //

CREATE PROCEDURE services_between_dates (IN cl_id INT, IN start_date DATE, IN finish_date DATE)
BEGIN
	SELECT Service_name AS Service_on_appointment, Appointment_date AS Service_date
    FROM (clients JOIN patients ON (clients.Client_id = patients.Owner_id)
     JOIN appointments ON (patients.Patient_id = appointments.Patient_id)
     JOIN appointment_services ON (appointments.Appointment_id = appointment_services.Appointment_id)
     JOIN services ON (appointment_services.Service_id = services.Service_id) )
	WHERE (clients.Client_id=cl_id AND Appointment_date >= start_date AND Appointment_date <= finish_date);
    
    SELECT Service_name AS Service_in_hospital, Service_date
    FROM (clients JOIN patients ON (clients.Client_id = patients.Owner_id)
     JOIN hospital ON (patients.Patient_id = hospital.Patient_id)
     JOIN hospital_services ON (hospital.Hospital_id = hospital_services.Hospital_id)
     JOIN services ON (hospital_services.Service_id = services.Service_id) )
	WHERE (clients.Client_id=cl_id AND Service_date >= start_date AND Service_date <= finish_date);
END//

DROP procedure services_between_dates;


DROP FUNCTION reduce;

CREATE FUNCTION reduce(Second_name VARCHAR(30), First_name VARCHAR(30), Patronymic VARCHAR(35))
RETURNS VARCHAR(36) DETERMINISTIC
RETURN CONCAT(Second_name, ' ', SUBSTRING(First_name, 1, 1), '. ', SUBSTRING(Patronymic, 1, 1), '.');

SELECT reduce(Second_name, First_name, Patronymic) AS Initials, Second_name, First_name, Patronymic
FROM Doctors;