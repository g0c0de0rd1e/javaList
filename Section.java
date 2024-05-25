package com.example.lr9;

import java.util.Objects;

public class Section implements Comparable<Section> {
    private int id_section;
    private String section_name;
    private int once_a_week;
    private double price_for_exercise;

    public Section() {
        this.id_section = 0;
        this.section_name = "";
        this.once_a_week = 0;
        this.price_for_exercise = 0.0;
    }

    public Section(int id_section, String section_name, int once_a_week, double price_for_exercise) {
        this.id_section = id_section;
        this.section_name = section_name;
        this.once_a_week = once_a_week;
        this.price_for_exercise = price_for_exercise;
    }

    public Section(Section other) {
        this.id_section = other.id_section;
        this.section_name = other.section_name;
        this.once_a_week = other.once_a_week;
        this.price_for_exercise = other.price_for_exercise;
    }

    public int getIdSection(){
        return id_section;
    }

    public String getSectionName(){
        return  section_name;
    }

    public int getOnceAWeek(){
        return once_a_week;
    }

    public double getPriceForExercise(){
        return price_for_exercise;
    }

    // сеттеры
    public void setIdSection(int id_section){
        this.id_section = id_section;
    }

    public void setSectionName(String section_name){
        this.section_name = section_name;
    }

    public void setOnceAWeek(int once_a_week){
        this.once_a_week = once_a_week;
    }

    public void setPriceForExercise(double price_for_exercise){
        this.price_for_exercise = price_for_exercise;
    }

    @Override
    public String toString() {
        return "Секция{" +
                "номер секции - " + id_section +
                ", название секции - " + section_name + '\'' +
                ", раз в неделю - " + once_a_week +
                ", цена за занятие - " + price_for_exercise +
                "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        if (getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return id_section == section.id_section &&
                once_a_week == section.once_a_week &&
                Double.compare(section.price_for_exercise, price_for_exercise) == 0 &&
                Objects.equals(section_name, section.section_name);
    }

    @Override
    public int compareTo(Section section) {
        if (id_section < section.id_section) return -1;
        if (id_section > section.id_section) return 1;
        int nameComparison = section_name.compareTo(section.section_name);
        if (nameComparison != 0) return nameComparison;
        if (once_a_week < section.once_a_week) return -1;
        if (once_a_week > section.once_a_week) return 1;
        return Double.compare(price_for_exercise, section.price_for_exercise);
    }

    @Override
    public int hashCode() {
        return 7 * Integer.hashCode(id_section) +
                11 * section_name.hashCode() +
                13 * Integer.hashCode(once_a_week) +
                17 * Double.hashCode(price_for_exercise);
    }

    public double Payroll(){
        return once_a_week * price_for_exercise;
    }
}



