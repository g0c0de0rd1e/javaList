package com.example.lr9;

import java.util.*;

public class CompNameAscPayrollDesc implements Comparator<Section> {
    public int compare(Section s1, Section s2) {
        int titleComparison = s1.getSectionName().compareTo(s2.getSectionName());
        if (titleComparison != 0) {
            return titleComparison;
        } else {
            return s2.getSectionName().compareTo(s1.getSectionName());
        }
    }
}