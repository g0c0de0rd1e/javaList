package com.example.lr9;

import java.util.*;
import java.util.*;
public class CompPayrollAsc implements Comparator<Section> {
    public int compare(Section s1, Section s2) {
        return s1.getSectionName().compareTo(s2.getSectionName());
    }
}
