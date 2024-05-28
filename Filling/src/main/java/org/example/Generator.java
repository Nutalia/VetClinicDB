package org.example;

import java.util.Random;

public class Generator {
    Random random = new Random();
    final static String[] maleNames = {"Александр", "Алексей", "Анатолий", "Андрей", "Артем", "Борис", "Валерий",
            "Василий", "Виктор", "Виталий", "Владимир", "Георгий", "Глеб", "Григорий",
            "Дмитрий", "Евгений", "Егор", "Иван", "Илья", "Кирилл", "Лев", "Максим",
            "Михаил", "Николай", "Никита", "Олег", "Петр", "Семен", "Сергей", "Юрий"};
    final static String[] femaleNames = {"Александра", "Алиса", "Анастасия", "Анна", "Варвара", "Валерия", "Вера",
            "Вероника", "Дарья", "Евгения", "Екатерина", "Елена", "Елизавета", "Ирина",
            "Кристина", "Ксения", "Любовь", "Людмила", "Маргарита", "Марина", "Мария",
            "Надежда", "Нина", "Оксана", "Ольга", "Полина", "Светлана", "София", "Татьяна"};
    final static String[] surnames = {"Иванов", "Смирнов", "Кузнецов", "Васильев", "Петров", "Соколов", "Михайлов",
            "Новиков", "Федоров", "Морозов", "Волков", "Алексеев", "Лебедев", "Семенов", "Хаборов", "Хворостков",
            "Егоров", "Павлов", "Степанов", "Николаев", "Орлов", "Макаров", "Никитин", "Работин", "Решетов", "Цаплин",
            "Зайцев", "Захаров", "Соловьев", "Борисов", "Яковлев", "Романов", "Воробьев", "Петелин", "Петухов",
            "Фролов", "Королев", "Гусев", "Киселев", "Поляков", "Сорокин", "Виноградов", "Демидов", "Шариков",
            "Бородин", "Ошурков", "Червяков", "Сидоров", "Табаков", "Тараканов", "Назимов", "Ларин",
            "Лапин", "Гончаров", "Шишокин", "Шишкин", "Закатов", "Залежнев", "Вареников", "Валов"};
    final static String[] maleMonikers = {"Айс", "Альф", "Арто", "Арчи", "Барни", "Барон", "Вилли", "Билли", "Дилли",
            "Бруно", "Бинго", "Локи", "Феликс", "Джек", "Фред", "Микки", "Чейз", "Кейк", "Лука", "Буян", "Граф",
            "Ник", "Честер", "Хэнк", "Тедди", "Тинг", "Рик", "Дюк", "Люк", "Пип", "Рекс", "Туз", "Лео", "Уголек"};
    final static String[] femaleMonikers = {"Рокси", "Бони", "Ава", "Берта", "Ленни", "Милка", "Маркиза", "Дора",
            "Ромашка", "Полли", "Пенелопа", "Салли", "Оливия", "Трикси", "Стелла", "Лея", "Руби", "Фокси", "Дымка",
            "Фиона", "Симона", "Тесса", "Лили", "Лулу", "Кэт", "Дина", "Кора", "Фея", "Снежка", "Ночка", "Клякса"};
    final static String[] animalType = {"кошка", "собака", "попугай", "хомяк", "кролик", "шиншила"};
    final static String[] patronymic = {"Александров", "Алексеев", "Анатольев", "Андреев", "Артемов", "Борисов",
            "Валерьев", "Васильев", "Викторов", "Витальев", "Владиров", "Георгиев", "Глебов",
            "Григорев", "Дмитриев", "Евгеньев", "Егоров", "Иванов", "Кириллов", "Львов",
            "Михайлов", "Николаев", "Олегов", "Петров", "Семенов", "Сергеев", "Юрьев"};
    final static String[] goods = {"Отоведин капли в уши", "КотЭрвин раствор для приема внутрь", "Ветмедин S таблетки",
            "Марфлоксин таблетки", "Ошейник от блох, клещей для собак", "Милпразон антигельминтик",
            "Мильбемакс антигельминтик", "Вакдерм вакцина суспензия для инъекций", "Микродерм вакцина для животных",
            "АктиВет таблетки", "Минеральный комплекс", "Сера для животных", "Комплекс для кожи и шерсти",
            "Код Омега Плюс", "Лактобифадол пробиотик", "Травка для кошек лоток", "Травка для кошек пакет", "Катобевит",
            "Эвинтон раствор", "Лосьон для глаз", "Максидин капли глазные и интерназальные",
            "Средство для удаления слезных пятен", "Ирис капли глазные", "Лосьон очищающий для глаз",
            "Анандин капли ушные", "Лосьон очищающий для ушей", "Лосьон для ушей с фитокомплексом",
            "Отибиовин капли ушные", "Отодепин капли в уши с маслом сосны", "Чистые ушки лосьон для очистки ушей",
            "Рикарфа таблетки", "Хондартон раствор для инъекций", "Ветом 3 порошок", "Диаркан брикеты-сахарные кубики",
            "Молочная кислота 40%", "Веракол раствор для инъекций", "Ветсорбин таблетки", "Гепатолюкс для печени",
            "Средство для дезинфекции", "Онсиор таблетки", "Мелоксидил суспензия", "Ципровет таблетки",
            "Септогель", "Фунгивет для лечения грибковых заболеваний", "Фунгин Форте раствор для наружного применения",
            "Корм", "Сульф 120 таблетки", "Амоксоил ретард суспензия для инъекций", "Синулокс шприц",
            "Арбимектин таблетки"};
    final static String[] services = {"Удаление папилломы", "Грыжесечение", "Лечение абсцесса",
            "Удаление репродуктивных органов", "Резекция опухоли на коже", "Ампутация конечности",
            "Резекция поджелудочной железы", "Резекция печени", "Иссечение части желудка", "Офтальмоскопия",
            "Лечение органов зрения", "Операция век", "Санирование ротовой полости", "Проведение ЭКГ", "Проведение КТ",
            "Проведение ЭХОкг", "Проведение рентгена", "Анализ мочи", "Анализ кала", "Клинический анализ крови ",
            "Установка капельницы", "Вакцинация", "Чипирование", "Кормление", "Первичный осмотр", "Проведение МРТ",
            "Диагностика органов слуха", "Лечение органов слуха", "Профилактический осмотр", "Гигиеническая стрижка",
            "Консультация по вопросам рациона", "Консультация по изменению поведения животного", "Перевязка",
            "Проведение УЗИ", "Удаление новообразований в ротовой полости", "Лечение переломов челюстей",
            "Тест Ширмера", "Тест с флюорисцином", "Эндоскопическая диагностика", "Биохимия крови", "Коагулограмма",
            "Гормональные исследования", "Анализ на паразитов", "Лечение от паразитов", "Лечение переломов конечностей",
            "Бактериологические исследования", "Гистологическое исследование", "Цитологическое исследование",
            "Наложение гипса", "Лечение мочевыделительной системы"};

    final static String[] featuresDigit = {"Температура", "Артериальное давление", "Вес", "Частота пульса", "Частота дыхания"};
    final static String[] featuresNonDigit = {"Состояние ротовой полости", "Состояние шерсти/перьев", "Состояние кожи",
            "Состояние глаз", "Состояние верхних конечностей", "Состояние нижних конечностей", "Состояние ушей",
            "Состояние хвоста", "Аппетит", "Состояние когтей", "Состояние брюшной полости",
            "Состояние подкожных лимфоузлов", "Состояние слизистых оболочек", "Состояние грудной клетки", "Общее состояние"};
    final static String[] featureValue = {"Критическое", "Удовлетворительное", "Ниже нормы", "Норма"};

    private String addDays(String date, int days) {
        var ymd = date.split("-");
        int day = Integer.parseInt(ymd[2]) + days;
        int month = Integer.parseInt(ymd[1]);
        int year = Integer.parseInt(ymd[0]);
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day >= 30) {
                day -= 30;
                month++;
            }
        } else if (month == 2) {
            if (year % 4 == 0) {
                if (day >= 29) {
                    day -= 29;
                    month++;
                }
            } else {
                if (day >= 28) {
                    day -= 28;
                    month++;
                }
            }
        } else {
            if (day >= 31) {
                day -= 31;
                month++;
                if (month == 13) {
                    month = 1;
                    year++;
                }
            }
        }
        if(day == 0)
            day = 1;
        return year + "-" + month + "-" + day;
    }

    public String randomPhone() {
        StringBuilder phone = new StringBuilder();
        phone.append("+7(");
        for (int i = 0; i < 3; i++)
            phone.append(random.nextInt(9));
        phone.append(")");
        for (int i = 0; i < 7; i++) {
            phone.append(random.nextInt(9));
            if (i == 2 || i == 4)
                phone.append("-");
        }
        return phone.toString();
    }

    public String randomDayMonth(int year) {
        StringBuilder date = new StringBuilder();
        int month = random.nextInt(1, 12);
        date.append(month);
        date.append("-");
        int day;
        if (month == 2) {
            if (year % 4 == 0)
                day = random.nextInt(1, 29);
            else day = random.nextInt(1, 28);
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = random.nextInt(1, 30);
        } else {
            day = random.nextInt(1, 31);
        }
        date.append(day);
        return date.toString();
    }

    public String randomBirthday() {
        StringBuilder birthday = new StringBuilder();
        int year = random.nextInt(2000, 2021);
        birthday.append(year);
        birthday.append("-");
        birthday.append(randomDayMonth(year));
        return birthday.toString();
    }

    public String[] randomHuman() {
        String[] result = new String[4];
        if (random.nextBoolean()) {
            result[0] = surnames[random.nextInt(surnames.length - 1)];
            result[1] = maleNames[random.nextInt(maleNames.length - 1)];
            result[2] = patronymic[random.nextInt(patronymic.length - 1)] + "ич";
        } else {
            result[0] = surnames[random.nextInt(surnames.length - 1)] + "а";
            result[1] = femaleNames[random.nextInt(femaleNames.length - 1)];
            result[2] = patronymic[random.nextInt(patronymic.length - 1)] + "на";
        }
        result[3] = randomPhone();
        return result;
    }

    public String[] randomAnimal() {
        String[] result = new String[4];
        if (random.nextBoolean()) {
            result[0] = maleMonikers[random.nextInt(maleMonikers.length - 1)];
            result[1] = "М";
        } else {
            result[0] = femaleMonikers[random.nextInt(femaleMonikers.length - 1)];
            result[1] = "Ж";
        }
        result[2] = randomBirthday();
        result[3] = animalType[random.nextInt(animalType.length - 1)];
        return result;
    }

    public String[] randomPrice(int i) {
        String[] result = new String[2];
        result[0] = goods[i];
        result[1] = String.format("%d.%d", random.nextInt(200, 9999), random.nextInt(99));
        return result;
    }

    public String[] randomAppointment() {
        String[] result = new String[4];
        int year = random.nextInt(2019, 2021);
        result[0] = year + "-" + randomDayMonth(year);
        result[1] = random.nextInt(8, 20) + ":00";
        return result;
    }

    public String[] randomSale() {
        String[] result = new String[2];
        int year = random.nextInt(2019, 2021);
        result[0] = year + "-" + randomDayMonth(year);
        result[1] = String.valueOf(random.nextInt(1, goods.length + 1));
        return result;
    }

    public String[] randomHospital() {
        String[] result = new String[3];
        int year = random.nextInt(2019, 2021);
        result[0] = year + "-" + randomDayMonth(year);
        int days = random.nextInt(5, 30);
        result[1] = addDays(result[0], days);
        result[2] = String.valueOf(days);
        return result;
    }

    public String[] randomCondition(int doctorNumber, int days, String start, boolean type) {//type - true легкое животное
        String[] result = new String[5];
        int idx = random.nextInt(19);
        result[0] = String.valueOf(idx + 1);
        if (idx < featuresDigit.length) {
            if (idx == 0) {
                result[1] = String.format("%d.%d", random.nextInt(34, 45), random.nextInt(9));
            } else if (idx == 1) {
                result[1] = String.format("%d-%d", random.nextInt(100, 200), random.nextInt(50, 150));
            } else if (idx == 2) {
                if (type) {
                    result[1] = random.nextInt(200, 2000) + " г";
                } else {
                    result[1] = random.nextInt(1, 20) + " кг";
                }
            } else {
                result[1] = random.nextInt(20, 150) + " в мин";
            }
        } else {
            result[1] = featureValue[random.nextInt(featureValue.length - 1)];
        }
        result[2] = addDays(start, random.nextInt(days));
        result[3] = String.format("%d:%d", random.nextInt(23), random.nextInt(59));
        result[4] = String.valueOf(random.nextInt(1, doctorNumber + 1));
        return result;
    }

    public String[] randomService(int i) {
        String[] result = new String[2];
        result[0] = services[i];
        result[1] = String.format("%d.%d", random.nextInt(500, 10000), random.nextInt(100));
        return result;
    }

    public String[] randomAppServices(int doctors) {
        String[] result = new String[2];
        result[0] = String.valueOf(random.nextInt(1, doctors + 1));
        result[1] = String.valueOf(random.nextInt(1, services.length + 1));
        return result;
    }

    public String[] randomHosServices(int doctors, int days, String start) {
        String[] result = new String[3];
        result[0] = String.valueOf(random.nextInt(1, doctors + 1));
        result[1] = String.valueOf(random.nextInt(1, services.length + 1));
        result[2] = addDays(start, random.nextInt(days));
        return result;
    }
}
