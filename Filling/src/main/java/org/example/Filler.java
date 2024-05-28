package org.example;

import java.sql.*;
import java.util.*;

public class Filler {
    final static int clientNumber = 500; //количество клиентов
    final static int doctorNumber = 20; //количество врачей
    final static int minAnimals = 1; //минимальное количество животных у одного клиента
    final static int maxAnimals = 5; //максимальное количество животных у одного клиента
    final static int minGoods = 1; //минимальное количество покупок у одного клиента
    final static int maxGoods = 10; //максимальное количество покупок у одного клиента
    final static int patientNumber = clientNumber*(minAnimals+maxAnimals)/2;//количество пациентов
    final static int minAppointments = 5; //минимальное количество приемов одного пациента
    final static int maxAppointments = 10; //максимальное количество приемов одного пациента
    final static int appointNumber = patientNumber*(minAppointments+maxAppointments)/2; //количество приемов
    final static int minHospital = 1; //минимальное количество попаданий в стационар одного пациента
    final static int maxHospital = 8; //максимальное количество попаданий в стационар одного пациента
    final static int minCondition = 5; //минимальное количество состояний в стационаре
    final static int maxCondition = 10; //максимальное количество состояний в стационаре
    final static int minServicesAppointment = 1; //минимальное количество услуг на приеме
    final static int maxServicesAppointment = 10; //максимальное количество услуг на приеме
    final static int minServicesHospital = 5; //минимальное количество услуг в стационаре
    final static int maxServicesHospital = 15; //максимальное количество услуг в стационаре
    static List<Integer> daysInHospital = new ArrayList<>();
    static List<String> patientType = new ArrayList<>();
    static List<String> startInHospital = new ArrayList<>();
    static List<Integer> patientInHospital = new ArrayList<>();
    public static void main(String[] args) {
        Generator generator = new Generator();
        String url = "jdbc:mysql://127.0.0.1/vetclinic";
        String userName = "root";
        String pass = "Windows_10Microsoft";
        try (Connection connection = DriverManager.getConnection(url, userName, pass)) {
            Statement statement = connection.createStatement();

            //клиенты
            StringBuilder request = new StringBuilder(
                    "INSERT INTO clients (Second_name,First_name,Patronymic,Phone_number) VALUES ");
            for(int i = 0; i < clientNumber; i++) {
                var client = generator.randomHuman();
                request.append(String.format("('%s','%s','%s','%s'),",
                        client[0], client[1], client[2], client[3]));
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица клиентов заполнена");

            //товары
            request = new StringBuilder("INSERT INTO products (Product_name,Price) VALUES ");
            for(int i = 0; i < Generator.goods.length; i++) {
                var product = generator.randomPrice(i);
                request.append(String.format("('%s',%s),",
                        product[0], product[1]));
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица товаров заполнена");

            //врачи
            request = new StringBuilder(
                    "INSERT INTO doctors (Second_name,First_name,Patronymic,Phone_number) VALUES ");
            for(int i = 0; i < doctorNumber; i++) {
                var doctor = generator.randomHuman();
                request.append(String.format(
                        "('%s','%s','%s','%s'),",
                        doctor[0], doctor[1], doctor[2], doctor[3]));
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица врачей заполнена");

            //признаки
            request = new StringBuilder("INSERT INTO features (Feature_name) VALUES ");
            for(int i = 0; i < Generator.featuresDigit.length;i++) {
                request.append(String.format("('%s'),",
                        Generator.featuresDigit[i]));
            }
            for(int i = 0; i < Generator.featuresNonDigit.length;i++) {
                request.append(String.format("('%s'),",
                        Generator.featuresNonDigit[i]));
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица признаков заполнена");

            //услуги
            request = new StringBuilder("INSERT INTO services (Service_name,Price) VALUES ");
            for(int i = 0; i < Generator.services.length; i++) {
                var service = generator.randomService(i);
                request.append(String.format("('%s',%s),",service[0],service[1]));
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица услуг заполнена");

            //пациенты
            request = new StringBuilder("INSERT INTO patients (Moniker,Gender,Birthday,Animal_kind,Owner_id) VALUES ");
            int animalNumber = minAnimals;
            int range = clientNumber/(maxAnimals - minAnimals + 1);
            for(int i = 1; i <= clientNumber; i++) {
                for (int j = 0; j < animalNumber; j++) {
                    var patient = generator.randomAnimal();
                    request.append(String.format("('%s','%s','%s','%s',%s),",
                            patient[0], patient[1], patient[2], patient[3],i));
                    patientType.add(patient[3]);
                }
                if(i % range == 0)
                    animalNumber++;
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица пациентов заполнена");

            //продажи
            request = new StringBuilder("INSERT INTO sales (Sale_date,Product_id,Client_id) VALUES");
            int goodNumber = minGoods;
            range = clientNumber/(maxGoods - minGoods + 1);
            for(int i = 1; i <= clientNumber; i++) {
                for(int j = 0; j < goodNumber; j++) {
                    var sale = generator.randomSale();
                    request.append(String.format("('%s',%s,%s),", sale[0], sale[1], i));
                }
                if(i % range == 0) {
                    goodNumber++;
                }
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица продаж заполнена");

            //приемы
            request = new StringBuilder(
                    "INSERT INTO appointments (Appointment_date,Appointment_time,Patient_id) VALUES ");
            int appointmentNumber = minAppointments;
            range = patientNumber/(maxAppointments - minAppointments + 1);
            for(int i = 1; i <= patientNumber; i++) {
                for (int j = 0; j < appointmentNumber; j++) {
                    var appointment = generator.randomAppointment();
                    request.append(String.format("('%s','%s',%s),",
                            appointment[0], appointment[1],i));
                }
                if(i % range == 0)
                    appointmentNumber++;
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица приемов заполнена");

            //стационар
            request = new StringBuilder(
                    "INSERT INTO hospital (Admission_date,Discharge_date,Patient_id) VALUES ");
            int hospitalNumber = minHospital;
            range = patientNumber/(maxHospital-minHospital+1);
            int current = 0; //количество уже добавленных записей
            for(int i = 1; i < patientNumber; i++) {
                for (int j = 0; j < hospitalNumber; j++) {
                    var hospital = generator.randomHospital();
                    request.append(String.format("('%s','%s',%s),",
                            hospital[0], hospital[1],i));
                    daysInHospital.add(Integer.parseInt(hospital[2]));
                    startInHospital.add(hospital[0]);
                    patientInHospital.add(i-1);
                }
                if((hospitalNumber % 2) == 1 || hospitalNumber == 2) {
                    if(i - current == range) {
                        hospitalNumber++;
                        current += range;
                    }
                } else {
                    if(i - current == range + 1) {
                        hospitalNumber++;
                        current += range + 1;
                    }
                }
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица стационара заполнена");

            //состояния
            request = new StringBuilder(
                    "INSERT INTO conditions (Feature_id,Feature_value,Fixation_date,Fixation_time,Doctor_id,Hospital_id) VALUES ");
            int conditionNumber = minCondition;
            range = daysInHospital.size()/(maxCondition-minCondition+1);
            for(int i = 0; i < daysInHospital.size(); i++) {
                String patient = patientType.get(patientInHospital.get(i));
                boolean type = !(patient.equals("собака") || patient.equals("кошка"));
                for(int j = 0; j < conditionNumber; j++) {
                    var condition = generator.randomCondition(doctorNumber,daysInHospital.get(i),
                            startInHospital.get(i),type);
                    request.append(String.format("(%s,'%s','%s','%s',%s,%s),",
                            condition[0],condition[1],condition[2],condition[3],condition[4],i+1));
                }
                if((i+1) % range == 0)
                    conditionNumber++;
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица состояний заполнена");

            //услуги на приеме
            request = new StringBuilder(
                    "INSERT INTO Appointment_services (Doctor_id,Service_id,Appointment_id) VALUES ");
            int serviceNumber = minServicesAppointment;
            range = appointNumber/(maxServicesAppointment - minServicesAppointment + 1);
            for(int i = 1; i <= appointNumber; i++) {
                for(int j = 0; j < serviceNumber; j++) {
                    var service = generator.randomAppServices(doctorNumber);
                    request.append(String.format("(%s,%s,%s),", service[0],service[1],i));
                }
                if(i % range == 0) {
                    serviceNumber++;
                }
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица услуг на приеме заполнена");

            //услуги в стационаре
            request = new StringBuilder(
                    "INSERT INTO Hospital_services (Doctor_id,Service_id,Service_date,Hospital_id) VALUES ");
            serviceNumber = minServicesHospital;
            range = daysInHospital.size()/(maxServicesHospital - minServicesHospital + 1);
            current = 0;
            for(int i = 1; i <= daysInHospital.size(); i++) {
                for(int j = 0; j < serviceNumber; j++) {
                    var service = generator.randomHosServices(doctorNumber, daysInHospital.get(i - 1),
                            startInHospital.get(i - 1));
                    request.append(String.format("(%s,%s,'%s',%s),", service[0],service[1],service[2], i));
                }
                if(serviceNumber % 2 == 1 || serviceNumber == 10) {
                    if(i - current == range + 1) {
                        serviceNumber++;
                        current += range + 1;
                    }
                } else {
                    if(i - current == range) {
                        serviceNumber++;
                        current+=range;
                    }
                }
            }
            request.deleteCharAt(request.length()-1);
            statement.executeUpdate(request.toString());
            System.out.println("Таблица услуг в стационаре заполнена");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
