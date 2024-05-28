/*Запрос 1a*/
SELECT Moniker, Animal_kind, Birthday
FROM ((Sales JOIN Patients
ON (Product_id=19 AND Client_id=Owner_id)) JOIN
Appointments USING (Patient_id)) JOIN Appointment_services
ON (Doctor_id = 18 AND 
Appointments.Appointment_id=Appointment_services.Appointment_id)
GROUP BY Patient_id;

/*Запрос 1b*/
SELECT DISTINCT Moniker, Animal_kind, Birthday, Sales_number
FROM (SELECT Client_id, count(*) AS Sales_number
FROM Sales WHERE Product_id = 19
GROUP BY Client_id HAVING Sales_number >= 3) AS Number_of_sales JOIN
Patients ON Client_id=Owner_id JOIN
Appointments USING (Patient_id) JOIN
Appointment_services USING (Appointment_id)
WHERE Doctor_id = 18;

/*Запрос 2*/
SELECT Second_name, First_name, Patronymic, count(*) AS Patient_number
FROM ((SELECT Doctor_id, Patient_id
FROM Appointment_services JOIN Appointments
ON (Doctor_id = 10 AND 
Appointment_services.Appointment_id = Appointments.Appointment_id)
GROUP BY Patient_id) AS From_appointments
JOIN (SELECT Doctor_id, Patient_id
FROM Hospital_services JOIN Hospital
ON (Doctor_id = 10 AND Hospital_services.Hospital_id=Hospital.Hospital_id)
GROUP BY Patient_id) AS From_hospital
USING (Doctor_id, Patient_id)) JOIN Doctors
USING (Doctor_id);

/*Запрос 3*/
SELECT Second_name, 
Appointment_services_number,
Hospital_services_number
FROM ((SELECT count(*) AS Appointment_services_number, Doctor_id
FROM Appointment_services
GROUP BY Doctor_id) AS Services_on_appointments JOIN
(SELECT count(*) AS Hospital_services_number, Doctor_id
FROM Hospital_services
GROUP BY Doctor_id) AS Services_in_hospital
USING (Doctor_id)) JOIN Doctors 
USING (Doctor_id);

/*Запрос 4*/
SELECT Services_number, count(*) AS Patients_number
FROM (SELECT DISTINCT Patient_id, count(*) AS Services_number
FROM Appointment_services JOIN Appointments
USING (Appointment_id)
GROUP BY Appointment_id) AS Number_of_services
GROUP BY Services_number;

/*Запрос 5*/
SELECT Second_name, First_name, Patronymic, count(*) AS Sales_number
FROM Sales JOIN Clients USING (Client_id)
GROUP BY Client_id
HAVING Sales_number =
(SELECT count(*) AS Number_of_sales
FROM Sales
GROUP BY Client_id
ORDER BY Number_of_sales DESC
LIMIT 1);

/*Запрос 6*/
SELECT Second_name, Service_name, count(*) AS Service_number
FROM Appointment_services JOIN Doctors
USING (Doctor_id) JOIN Services
USING (Service_id)
GROUP BY Doctor_id, Service_id;

/*Запрос 7*/
SELECT Product_name, count(*) AS Number_of_Sales, 
(SELECT count(*) FROM Sales WHERE Product_id=3) AS Sales_of_product_A
FROM Sales JOIN Products
USING (Product_id)
GROUP BY Product_id
HAVING Number_of_sales > Sales_of_product_A;

/*Запрос 8*/
SELECT Second_name, First_name, Patronymic
FROM Doctors
WHERE Doctor_id NOT IN
(SELECT Doctor_id
FROM (Hospital_services JOIN Hospital
USING (Hospital_id))
WHERE Patient_id = 500);