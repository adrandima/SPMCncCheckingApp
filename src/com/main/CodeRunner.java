package com.main;

import com.common.ReadFile;
import com.nestingCheck.LoopRepo.LoopPatternChecker;
import com.nestingCheck.Model.Line;
import com.nestingCheck.RecurstionFunctionChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeRunner {
    private final static String METHOD_IDENTIFIER = "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
    public ArrayList<Line> cncLinesWithValue = new ArrayList<>();

    public void Call() throws IOException {
            Pattern methodIdentifierPattern = Pattern.compile(METHOD_IDENTIFIER);
            Matcher methodIdentifierMatcher;
            ReadFile readFile = new ReadFile();
            BufferedReader br = readFile.sendStatement();
            RecurstionFunctionChecker recurtionChecker = new RecurstionFunctionChecker();
            LoopPatternChecker loopPatternChecker = new LoopPatternChecker();
            String st;
            boolean status = false;

                while ((st = br.readLine()) != null) {

                methodIdentifierMatcher = methodIdentifierPattern.matcher(st);
                boolean result = methodIdentifierMatcher.find();
                if(result || status){

                    int a= recurtionChecker.checkRecursiveFunction(st);
                    // System.out.println(a);
                    if (a != 0){
                        status = true;
                    }else{
                        status = false;

                        ArrayList<Line> linesWithValues = loopPatternChecker.bracketChecker(recurtionChecker.getLineArrayList());
                        for (int n = 0;n<=linesWithValues.size()-1;n++){
                            //System.out.println(linesWithValues.get(n).getLine()+"::"+linesWithValues.get(n).getValue());
                            cncLinesWithValue.add(new Line(linesWithValues.get(n).getLine(),linesWithValues.get(n).getValue()));
                        }
                        //System.out.println("***************************************");
                        recurtionChecker = new RecurstionFunctionChecker();
                        loopPatternChecker = new LoopPatternChecker();

                    }
                }else{
                    cncLinesWithValue.add(new Line(st,0));
                }
            }

        int value = 0;
        for (int n = 0;n<=cncLinesWithValue.size()-1;n++){
            System.out.println(cncLinesWithValue.get(n).getLine()+"::"+cncLinesWithValue.get(n).getValue());
            value= value+cncLinesWithValue.get(n).getValue();

        }
        System.out.println(value);
        }



}

