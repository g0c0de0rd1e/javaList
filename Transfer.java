package com.example.lr9;
import java.util.*;
public class Transfer {
    public static String unificationString(String line){
        if (line == null) return null;
        line = line.trim();
        if (line.equals("")) return "";
        String [ ] words = line.split("\\s+");
        StringBuilder result = new StringBuilder();
        result.append (words[0].substring(0,1).toUpperCase());
        result.append(words[0].substring(1).toLowerCase());
        for (int i = 1; i < words.length; i++){
            result.append(" ");
            result.append(words[i].toLowerCase());
        }
        return result.toString();
    }
    public static SectionTrainerSurname lineToTrainer(String line, int num) {
        String [ ] words = line.split(" ");
        if (words.length != num) return null;
        SectionTrainerSurname s = new SectionTrainerSurname();
        s.setTrainer_surname(unificationString(words[1]));
        s.setSectionName(unificationString(words[4]).toLowerCase());
        try{
            s.setIdSection(Integer.parseInt (words[0].trim()));
            s.setPriceForExercise(Double.parseDouble (words[3].trim()));
            s.setOnceAWeek(Integer.parseInt (words[2].trim()));
        } catch (NumberFormatException e){
            return null;}
        if (s.getOnceAWeek() < 0 || s.getOnceAWeek() > 7) return null;
        return s;
    }

    public static List <SectionTrainerSurname> StringsToTrainer(List <String> lines){
        if (lines == null || lines.isEmpty ()) return null;
        List <SectionTrainerSurname> sections = new ArrayList <SectionTrainerSurname> ();
        for (int i = 0; i < lines.size(); i++){
            SectionTrainerSurname t = lineToTrainer(lines.get(i), 5);
            if (t == null) return null;
            sections.add(t);
        }
        return sections;
    }

    public static List <String> TeacherToString (List <SectionTrainerSurname> sections) {
        if (sections == null || sections.isEmpty ()) return null;
        List <String> lines = new ArrayList <String> ();
        for (SectionTrainerSurname sectionTrainerSurname: sections)
            lines.add(String.format("%d, %s, %.2f, %d, %s,", sectionTrainerSurname.getIdSection(),
                    sectionTrainerSurname.getTrainerSurname(), sectionTrainerSurname.getPriceForExercise(),
                    sectionTrainerSurname.getOnceAWeek(), sectionTrainerSurname.getSectionName()));
        return lines;
    }
}
