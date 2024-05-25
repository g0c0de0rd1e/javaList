package com.example.lr9;

import java.io.*;
import java.util.*;
public class IO {
    public static List <String> inpLines (String fileName) throws IOException {
        List <String> lines = new ArrayList <String>();
        String line;
        BufferedReader inp = null;
        try {
            inp = new BufferedReader (new FileReader(fileName));
            while ((line = inp.readLine()) != null) {
                line = line.trim();
                if (line.equals("")) continue;
                lines.add(line);
            }
        }
        catch (IOException e){
            return null;}
        finally {
            if (inp != null) inp.close();
        }
        if (lines.isEmpty()) return null;
        return lines;
    }
    public static boolean outLines (String fileName, List <String> lines )
            throws IOException {
        PrintWriter out = null;
        if ((lines == null) || lines.isEmpty()) return false;
        try {
            out = new PrintWriter (new FileWriter(fileName));
            int n = lines.size();
            for (int i = 0; i < n; i++){
                out.println(lines.get(i).trim());
            }
        }
        catch (IOException e){
            return false;}
        finally {
            if (out != null) out.close();
        }
        return true;
    }
}