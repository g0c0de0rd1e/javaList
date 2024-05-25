package com.example.lr9;

import java.util.*;

public class SportSchool {
    private static final String SPORT_SCHOOL_FORMAT_STRING = "Спортшкола: %s, число секций: %3d";

    private String name;
    private List <SectionTrainerSurname> sections;
    public SportSchool() {
        name="";
        sections = new ArrayList <SectionTrainerSurname>();
    }
    public SportSchool(String name){
        this.name = name;
        sections = new ArrayList <SectionTrainerSurname>();
    }
    public SportSchool(String name, List list){
        this.name = name;
        sections = new ArrayList <SectionTrainerSurname>(list);
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public List <SectionTrainerSurname> getSections(){return sections; }
    public String toString(){
        return String.format (SPORT_SCHOOL_FORMAT_STRING,name, getNum());
    }
    public boolean addSection(Section section) {
        this.sections.add((SectionTrainerSurname) section);
        return false;
    }
    public boolean removeSection(SectionTrainerSurname section) {
        this.sections.remove(section);
        return false;
    }

    public Section getSections (int id){
        for (SectionTrainerSurname section: sections)
            if (section.getIdSection() == id) return section;
        return null;
    }
    public int getNum(){
        return sections.size();
    }
    public float avgPayroll(){
        int num = sections.size();
        if (num == 0) return 0;
        float avg = 0;
        for (SectionTrainerSurname section: sections)
            avg=avg + (float)section.Payroll();
        return avg / num;
    }

    public SportSchool aboveAvgPayroll(){
        float avg = avgPayroll();
        SportSchool school = new SportSchool(String.format (
                "%s\nПреподаватели, у которых зарплата выше среднего %.2f: ", name, avg));
        for (SectionTrainerSurname section: sections)
            if (section.Payroll()>avg)school.addSection(section);
        return school;
    }

    public SportSchool betweenPayroll (float b1, float b2){
        SportSchool school = new SportSchool (String.format (
                "%s\nПреподаватели, у которых зарплата в диапазоне от %.2f до %.2f: ",
                name, b1, b2));
        Iterator <SectionTrainerSurname> iter = sections.iterator();
        while (iter.hasNext()){
            Section section = iter.next();
            if ((section.Payroll() >= b1)&&(section.Payroll() <= b2))school.addSection(section);
        }
        return school;
    }
    public SportSchool sort () {
        SportSchool school = new SportSchool (String.format (
                "%s\nСписок, отсортирован по возрастанию в естественном порядке: ",
                name), sections);
        Collections.sort (school.sections);
        return school;
    }
    public SportSchool sort(Comparator comp, String msg){
        SportSchool school = new SportSchool (String.format (
                "%s\n%s: ", name, msg), sections);
        Collections.sort(school.sections, comp);
        return school;
    }
    public void putSchoolSection(){
        System.out.println(name);
        int i = 1;
        for (Section section: sections){
            System.out.printf(" %-7d %s\n", i, section);
            i = i + 1;
        }
    }
    public List <TotalRecord> totalCountSumHobby(){
        int n = sections.size();
        if (n == 0) return null;
        List <Section> teacherTemp = new ArrayList < Section>();
        teacherTemp.addAll(sections);
        SortedSet <String> statusS = new TreeSet <String>();
        for (Section t: sections)
            statusS.add(t.getSectionName());
        List <String> statusL= new ArrayList (statusS);
        int m = statusL.size();
        String status;
        int count;
        float sum;
        List <TotalRecord> totRecList = new ArrayList <TotalRecord>();
        for (int i = 0; i < m; i++){
            status = statusL.get(i);
            sum = 0;
            count = 0;
            Iterator <Section> iter = teacherTemp.iterator();
            while (iter.hasNext()) {
                Section section = iter.next();
                if (status.equals(section.getSectionName())) {
                    count++;
                    sum += section.Payroll();
                    iter.remove();
                }
            }
            totRecList.add (new TotalRecord(status, count, sum/count));
        }
        return totRecList;
    }
    public SportSchool filterOfName(String filter){
        SportSchool school = new SportSchool (String.format ("%s\n%s%s:", name,
                "Список преподавателей с фамилией, начинающейся на ", filter));
        if (filter != null && filter != "") {
            filter = filter.toLowerCase();
            for (SectionTrainerSurname section: sections)
                if (section.getTrainerSurname().toLowerCase().startsWith(filter))
                    school.addSection(section);
        }
        return school;
    }
    public boolean updateTeacherByKey(SectionTrainerSurname s){
        Section section = getSections(s.getIdSection());
        if (section != null) {
            section.setSectionName(s.getSectionName());
            section.setOnceAWeek(s.getOnceAWeek());
            s.setTrainer_surname(s.getTrainerSurname());
            return true;
        }
        return false;
    }
    public boolean deleteAvgPayroll(){
        return sections.removeAll(aboveAvgPayroll().sections);
    }
}
