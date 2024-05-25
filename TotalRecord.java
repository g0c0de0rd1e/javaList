package com.example.lr9;

public class TotalRecord {
        String str;
        int num1;
        float num2;
        public TotalRecord(String str, int num1, float num2){
            this.num1 = num1;
            this.num2 = num2;
            this.str = str;
        }
        public String toString() {
            return String.format("| %30s | %5d | %16.2f |", str, num1, num2);
        }
}