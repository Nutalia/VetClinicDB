CREATE DATABASE VetClinic;
CREATE TABLE VetClinic.Clients
(
Client_id INT PRIMARY KEY AUTO_INCREMENT,
Second_name VARCHAR(30),
First_name VARCHAR(30),
Patronymic VARCHAR(35),
Phone_number VARCHAR(20)
);
CREATE TABLE VetClinic.Products
(
Product_id INT PRIMARY KEY AUTO_INCREMENT,
Product_name VARCHAR(50),
Price DECIMAL(6,2)
);
CREATE TABLE VetClinic.Sales
(
Sale_id INT PRIMARY KEY AUTO_INCREMENT,
Client_id INT,
Product_id INT,
Sale_date DATE,
FOREIGN KEY(Client_id) REFERENCES VetClinic.Clients(Client_id)
ON DELETE restrict
ON UPDATE restrict,
FOREIGN KEY(Product_id) REFERENCES VetClinic.Products(Product_id)
ON DELETE restrict
ON UPDATE restrict
);
CREATE TABLE VetClinic.Patients
(
Patient_id INT PRIMARY KEY AUTO_INCREMENT,
Owner_id INT,
Moniker VARCHAR(30),
Birthday DATE,
Animal_kind VARCHAR(20),
Gender CHAR(1),
FOREIGN KEY(Owner_id) REFERENCES VetClinic.Clients(Client_id)
ON DELETE restrict
ON UPDATE restrict
);
CREATE TABLE VetClinic.Doctors
(
Doctor_id INT PRIMARY KEY AUTO_INCREMENT,
Second_name VARCHAR(30),
First_name VARCHAR(30),
Patronymic VARCHAR(35),
Phone_number VARCHAR(20)
);
CREATE TABLE VetClinic.Appointments
(
Appointment_id INT PRIMARY KEY AUTO_INCREMENT,
Patient_id INT,
Appointment_date DATE,
Appointment_time TIME,
FOREIGN KEY(Patient_id) REFERENCES VetClinic.Patients(Patient_id)
ON DELETE restrict
ON UPDATE restrict
);
CREATE TABLE VetClinic.Services
(
Service_id INT PRIMARY KEY AUTO_INCREMENT,
Service_name VARCHAR(50),
Price DECIMAL(6,2)
);
CREATE TABLE VetClinic.Appointment_services
(
id INT PRIMARY KEY AUTO_INCREMENT,
Doctor_id INT,
Appointment_id INT,
Service_id INT,
FOREIGN KEY(Doctor_id) REFERENCES VetClinic.Doctors(Doctor_id)
ON DELETE restrict
ON UPDATE restrict,
FOREIGN KEY(Appointment_id) REFERENCES VetClinic.Appointments(Appointment_id)
ON DELETE restrict
ON UPDATE restrict,
FOREIGN KEY(Service_id) REFERENCES VetClinic.Services(Service_id)
ON DELETE restrict
ON UPDATE restrict
);
CREATE TABLE VetClinic.Hospital
(
Hospital_id INT PRIMARY KEY AUTO_INCREMENT,
Patient_id INT,
Admission_date DATE,
Discharge_date DATE,
FOREIGN KEY(Patient_id) REFERENCES VetClinic.Patients(Patient_id)
ON DELETE restrict
ON UPDATE restrict
);
CREATE TABLE VetClinic.Hospital_services
(
id INT PRIMARY KEY AUTO_INCREMENT,
Doctor_id INT,
Hospital_id INT,
Service_id INT,
Service_date DATE,
FOREIGN KEY(Doctor_id) REFERENCES VetClinic.Doctors(Doctor_id)
ON DELETE restrict
ON UPDATE restrict,
FOREIGN KEY(Hospital_id) REFERENCES VetClinic.Hospital(Hospital_id)
ON DELETE restrict
ON UPDATE restrict,
FOREIGN KEY(Service_id) REFERENCES VetClinic.Services(Service_id)
ON DELETE restrict
ON UPDATE restrict
);
CREATE TABLE VetClinic.Features
(
Feature_id INT PRIMARY KEY AUTO_INCREMENT,
Feature_name VARCHAR(50)
);
CREATE TABLE VetClinic.Conditions
(
Condition_id INT PRIMARY KEY AUTO_INCREMENT,
Doctor_id INT,
Hospital_id INT,
Feature_id INT,
Feature_value VARCHAR(50),
Fixation_date DATE,
Fixation_time TIME,
FOREIGN KEY(Doctor_id) REFERENCES VetClinic.Doctors(Doctor_id)
ON DELETE restrict
ON UPDATE restrict,
FOREIGN KEY(Hospital_id) REFERENCES VetClinic.Hospital(Hospital_id)
ON DELETE restrict
ON UPDATE restrict,
FOREIGN KEY(Feature_id) REFERENCES VetClinic.Features(Feature_id)
ON DELETE restrict
ON UPDATE restrict
);