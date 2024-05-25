package com.example.lr9;


import java.util.*;
public class CompPayrollDesc implements Comparator<Section> {
    public int compare(Section s1, Section s2) {
        return s2.getSectionName().compareTo(s1.getSectionName());
    }
}

