package com.example.lr9;

import java.io.*;
import java.util.*;
public class Main {
    static SportSchool table;
    static List <SectionTrainerSurname> teachers;

    static List <String> lines;

    static BufferedReader br;

    static String line;

    static int number;

    public static void main (String[] args) throws IOException {

        table = new SportSchool("Список записей о преподавателях");

        teachers = new ArrayList <SectionTrainerSurname>();

        br = new BufferedReader (
                new InputStreamReader ( System.in ) );
        line = "";
        number = 0;
        while (true){
            System.out.print( "\f" );
            System.out.println("Главное меню системы хранения и обработки данных:");
            System.out.println("1-Файл");
            System.out.println("2-Редактирование БД");
            System.out.println("3-Просмотр БД");
            System.out.println("4-Выход");
            System.out.println("-------------------------------");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            }
            catch (NumberFormatException e){
                number = 0;}
            switch (number){
                case 1: goFileMenu();break;
                case 2: goEditorMenu(); break;
                case 3: goViewMenu(); break;
                case 4: System.out.println ("Программа завершена"); return;
                default: System.out.println ("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
        }
    }
    //Пункты главного меню
    public static void goFileMenu()throws IOException{
        while (true){
            System.out.print( "\f" );
            System.out.println("Меню Файл:");
            System.out.println("1-Создать");
            System.out.println("2-Открыть");
            System.out.println("3-Сохранить как");
            System.out.println("4-Выход");
            System.out.println("-------------------------------");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            }
            catch (NumberFormatException e){
                number = 0;}
            switch (number){
                case 1: goCreate();break;
                case 2: goOpen(); break;
                case 3: goSaveAs(); break;
                case 4: return;
                default: System.out.println ("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
            System.out.println("Нажмите <Enter>");
            line = br.readLine();
        }
    }
    public static void goEditorMenu()throws IOException{
        while (true){
            System.out.print( "\f" );
            System.out.println("Меню Редактирование:");
            System.out.println("1-Добавить запись БД");
            System.out.println("2-Изменить запись БД по ключу");
            System.out.println("3-Удалить запись БД по ключу");
            System.out.println(
                    "4-Удалить группу записей БД (балл выше среднего)");
            System.out.println("5-Выход");
            System.out.println("-------------------------------");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            }
            catch (NumberFormatException e){
                number = 0;}
            switch (number){
                case 1: goAdd();break;
                case 2: goUpdate(); break;
                case 3: goDelete1(); break;
                case 4: goDeleteN(); break;
                case 5: return;
                default: System.out.println ("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
            System.out.println("Нажмите <Enter>");
            line = br.readLine();
        }
    }
    public static void goViewMenu()throws IOException{
        while (true){
            System.out.print( "\f" );
            System.out.println("Меню Просмотр");
            System.out.println("1-Вывести все");
            System.out.println("2-Применить фильтр по полю <Фамилия>");
            System.out.println("3-Сортировать по id");
            System.out.println("4-Сортировать возрастанию зарплаты");
            System.out.println("5-Сортировать по убыванию зарплаты");
            System.out.println("6-Сортировать по возрастанию фамилии и убыванию зарплаты");
            System.out.println("7-Средняя зарплата");
            System.out.println("8-Преподаватели с зарплатой выше среднего");
            System.out.println("9-Преподаватели с зарплатой в диапазоне");
            System.out.println("10-Число и средняя зарплата преподавателей с одинаковым статусом");
            System.out.println("11-Выход");
            System.out.println("-------------------------------");
            System.out.println("Введите номер пункта меню");
            line = br.readLine().trim();
            try {
                number = Integer.parseInt(line);
            }
            catch (NumberFormatException e){
                number = 0;}
            switch (number){
                case 1: goPutAll();break;
                case 2: goFilter(); break;
                case 3: goSortId(); break;
                case 4: goSortPayrollAsc(); break;
                case 5: goSortPayrollDesc(); break;
                case 6: goSortNameAscPayrollDesc(); break;
                case 7: goAvg(); break;
                case 8: goAboveAvgPayroll(); break;
                case 9: goBetweenPayroll(); break;
                case 10: gototalCountSumStatus(); break;
                case 11: return;
                default: System.out.println ("Неправильный номер");
                    System.out.println("Нажмите <Enter>");
                    line = br.readLine();
                    continue;
            }
            System.out.println("Нажмите <Enter>");
            line = br.readLine();
        }
    }
    public static void goCreate(){
        teachers.clear();
        table.getSections().clear();
        System.out.println ("База данных создана, можно добавлять записи");
    }
    public static void goOpen() throws IOException {
        System.out.println("Введите имя файла для ввода записей");
        String fileName = (br.readLine()).trim();
        try{
            lines = IO.inpLines(fileName);
            if (lines != null) {
                System.out.printf("Успешный ввод данных из файла %s\n", fileName);
                List <SectionTrainerSurname> sections = Transfer.StringsToTrainer(lines);
                if (sections != null){
                    table.getSections().clear();
                    for (SectionTrainerSurname s: sections) table.addSection(s);
                }
                else System.out.println ("Неправильно задано числовое значение");
            }
            else System.out.printf("Ошибка ввода данных из файла %s\n", fileName);
        }
        catch (IOException e) {
            System.out.println("Ошибка данных");
        }
    }
    public static void goSaveAs()throws IOException {
        System.out.println("Введите имя файла для вывода записей");
        String fileName = (br.readLine()).trim();
        lines = Transfer.TeacherToString(table.getSections());
        try{
            boolean f = IO.outLines(fileName, lines);
            if (f) {System.out.printf("Данные успешно сохранены в файле %s\n", fileName); }
            else System.out.printf("Ошибка сохранения данных в файле %s\n", fileName);
        }
        catch (IOException e) { System.out.printf("Ошибка сохранения данных в файле %s\n", fileName);}
    }

    public static void goAdd()throws IOException {
        System.out.println("Введите значения полей новой записи БД через запятую:");
        System.out.println("id, фамилия, ставка, оклад:");
        String line = (br.readLine()).trim();
        SectionTrainerSurname sts = Transfer.lineToTrainer(line,4);
        if (sts == null)
            System.out.println ("Неправильно введены значения числовых полей");
        else
        if (table.addSection(sts))
            System.out.println ("Запись успешно добавлена к БД");
        else System.out.println("Невозможно добавить запись. Проверьте уникальность ключа");
    }

    public static void goUpdate()throws IOException {
        table.sort().putSchoolSection();
        System.out.println("Введите значение существующего в БД ключа");
        System.out.println("и новые/старые значения неключевых полей через запятую:");
        System.out.println("id, фамилия, ставка, оклад, статус:");
        String line = (br.readLine()).trim();
        SectionTrainerSurname sections = Transfer.lineToTrainer(line,4);
        if (sections == null) System.out.println ("Неправильно введены значения полей");
        else
        if (table.updateTeacherByKey(sections))
            System.out.println ("Запись успешно изменена в БД");
        else System.out.println("Попытка изменения неудачна. Проверьте наличие указанного ключа");
    }

    public static void goDelete1()throws IOException {
        System.out.println("Введите значение существующего в БД ключа - id:");
        String line = (br.readLine()).trim();
        String str = String.format("%s, %s, %s, %s", line, "NoName", "1.0", "0.0");
        SectionTrainerSurname section = Transfer.lineToTrainer(str, 4);
        if (section == null) System.out.println ("Неправильно введены значения числовых полей");
        else
        if (table.removeSection(section))
            System.out.println ("Запись успешно удалена из БД");
        else System.out.println("Попытка удаления неудачна. Проверьте наличие записи.");
    }

    public static void goDeleteN(){
        float avg = table.avgPayroll();
        if (table.deleteAvgPayroll()) System.out.printf ("Записи с зарплатой выше среднего %.2f успешно удалены из БД\n",avg);
        else System.out.println("Попытка удаления неудачна.\n");
    }

    public static void goPutAll(){
        table.putSchoolSection();
    }
    public static void goFilter()throws IOException {
        System.out.println(
                "Введите фильтр для поля \"Фамилия\" (регистр не важен):");
        String line = (br.readLine()).trim();
        table.filterOfName(line).putSchoolSection();
    }
    public static void goSortId(){
        table.sort().putSchoolSection();
    }
    public static void goSortPayrollAsc(){
        table.sort(new CompPayrollAsc(), "Список отсортирован по возрастанию зарплаты").putSchoolSection();
    }

    public static void goSortPayrollDesc(){
        table.sort(new CompPayrollDesc(), "Список отсортирован по убыванию зарплаты").putSchoolSection();
    }
    public static void goSortNameAscPayrollDesc(){
        table.sort(new CompNameAscPayrollDesc(), "Список отсортирован по возрастанию фамилии и убыванию зарплаты").putSchoolSection();
    }
    public static void goAvg(){
        System.out.printf("Средняя зарплата преподавателей кафедры: %.2f\n", table.avgPayroll());
    }
    public static void goAboveAvgPayroll(){
        table.aboveAvgPayroll().putSchoolSection();
    }
    public static void goBetweenPayroll()throws IOException {
        System.out.println("Введите границы диапазона (левая меньше правой) через запятую :");
        System.out.println("вещественное число, вещественное число:");
        String line = (br.readLine()).trim();
        String [ ] words = line.split("[\\s,]+");
        if (words.length != 2) {
            System.out.println("Границы введены неверно.");
            return;
        }
        float a, b;
        try{
            a = Float.parseFloat(words[0]);
            b = Float.parseFloat(words[1]);
        }
        catch (NumberFormatException e){
            System.out.println("Задайте правильно границы диапазона");
            return;}
        table.betweenPayroll(a, b).putSchoolSection();
    }
    public static void gototalCountSumStatus(){
        System.out.println("Итоги по статусу (число преподавателей, средняя зарплата):");
        List <TotalRecord> list = table.totalCountSumHobby();
        System.out.printf("| %30s | %5s | %16s |\n", "Статус", "Число", "Зарплата");
        if (list.size() > 0)
            for (TotalRecord tr: list)
                System.out.printf ("%s\n", tr);
    }
}
