package com.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public BufferedReader getFile() throws IOException {

        File file = new File("/root/IdeaProjects/SPMCncCheckingApp/src/com/main/test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br;
    }

    public BufferedReader sendStatement() throws IOException {
        ReadFile readFile = new ReadFile();
        BufferedReader br = readFile.getFile();

        String st;
        return br;
/*        while ((st = br.readLine()) != null) {
            //nestingCheck.checkBracket(st);
            nestingCheck.checkCnc(st);
            // System.out.println(st);
        }*/
    }

}
