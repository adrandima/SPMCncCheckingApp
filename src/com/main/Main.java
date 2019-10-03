package com.main;

import com.common.ReadFile;
import com.nestingCheck.LoopRepo.LoopPatternChecker;
import com.nestingCheck.Model.Line;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        ReadFile readFile = new ReadFile();
        BufferedReader br = readFile.sendStatement();
        LoopPatternChecker loopPatternChecker = new LoopPatternChecker();
        String st;
        while ((st = br.readLine()) != null) {
           // System.out.println(st);
            ArrayList<Line> a= loopPatternChecker.bracketChecker(st);
        }
    }
}
