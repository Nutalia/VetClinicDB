/*Пользователь, который может только просматривать view*/
DROP USER 'cavy';

CREATE USER 'cavy'
DEFAULT ROLE cavy;

GRANT SELECT
ON TABLE vetclinic.appointments_number
TO 'cavy';

/*Пользователь, который может просматривать и изменять view*/
DROP USER 'viewer';

CREATE USER 'viewer'
DEFAULT ROLE viewer;

GRANT SELECT
ON TABLE vetclinic.appointments_number
TO 'viewer';

GRANT SELECT, UPDATE, DELETE, INSERT
ON TABLE vetclinic.appointment_services
TO 'viewer';

GRANT SELECT, UPDATE, DELETE, INSERT
ON TABLE vetclinic.hospital_services
TO 'viewer';

GRANT SELECT, UPDATE, DELETE, INSERT
ON TABLE vetclinic.doctors
TO 'viewer';

/* Что делать: зайти под пользователем cavy, зайти под пользователем viewer
От обоих пользователей сделать:
	SHOW TABLES;
От пользователя viewer:
	INSERT INTO doctors (Doctor_id, Second_name, First_name, Patronymic, Phone_number)
    VALUE (21, 'Бортникова', 'Валерия', 'Витальевна', '+7(921)426-71-37');
От пользователя cavy проверить изменения:
	SELECT * FROM appointments_number;
От пользователя viewer:
	INSERT INTO appointment_services (Doctor_id, Appointment_id, Service_id)
    VALUE (21, 20, 10);
Проверить от cavy;
От пользователя viewer:
	INSERT INTO hospital_services (Doctor_id, Hospital_id, Service_id)
    VALUE (21, 50, 20);
Проверить от cavy
*/