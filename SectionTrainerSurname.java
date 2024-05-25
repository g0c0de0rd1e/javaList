package com.example.lr9;
public class SectionTrainerSurname extends Section{

    private String trainer_surname;

    public SectionTrainerSurname() {
        super();
        this.trainer_surname = "";
    }

    public SectionTrainerSurname(int id_section, String section_name, int once_a_week, double price_for_exercises, String trainer_surname) {
        super(id_section, section_name, once_a_week, price_for_exercises);
        this.trainer_surname = trainer_surname;
    }

    public SectionTrainerSurname(SectionTrainerSurname section) {
        super(section);
        this.trainer_surname = section.trainer_surname;
    }

    public String getTrainerSurname() {
        return trainer_surname;
    }
    public void setTrainer_surname(String trainer_surname) {
        this.trainer_surname = trainer_surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SectionTrainerSurname)) return false;
        if (!super.equals(obj)) return false;
        SectionTrainerSurname section = (SectionTrainerSurname) obj;
        return trainer_surname.equals(section.trainer_surname);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + trainer_surname.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s, фамилия=%s: ", super.toString(), trainer_surname);
    }

    @Override
    public int compareTo(Section section) {
        if (!(section instanceof SectionTrainerSurname)) return super.compareTo(section);
        int superComparison = super.compareTo(section);
        if (superComparison != 0) return superComparison;
        return trainer_surname.compareTo(((SectionTrainerSurname) section).trainer_surname);
    }
}
