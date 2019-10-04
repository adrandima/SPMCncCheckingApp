package com.nestingCheck.LoopRepo;

import com.nestingCheck.Model.Line;
import com.nestingCheck.RecurtionStacks.ConditionStack;
import com.nestingCheck.RecurtionStacks.LoopBracketStack;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoopPatternChecker {
    private final String whileChecker = "while\\s*\\([^)]*\\)";
    private final String forChecker = "for\\s*\\([^)]*\\)";
    private final String whileCheckerWithSemicolen = "while\\s*\\([^)]*\\)\\s*;";
    private final String forCheckerWithSemicolen = "for\\s*\\([^)]*\\)\\s*;";
    private final String whileCheckerWithCurlyBracket = "while\\s*\\([^)]*\\)\\s*\\{\\s*";
    private final String forCheckerWithCurlyBracket = "for\\s*\\([^)]*\\)\\s*\\{\\s*";
    private final String doCheckerWithCurlyBracket = "do\\s*\\{\\s*";
    private final String closeCurlyBracket = "\\s*\\}\\s*";

    private final String STATEMENT_IDENTIFIER = "(for|while|if|try|switch)\\s*\\([^)]*\\)";

    Pattern whileIdentifierPattern;
    Pattern forIdentifierPattern;
    Pattern whileWithSemicolenIdentifierPattern;
    Pattern forWithSemicolenIdentifierPattern;
    Pattern whileWithCurlyBracketIdentifierPattern;
    Pattern forWithCurlyBracketIdentifierPattern;
    Pattern doWithCurlyBracketIdentifierPattern;
    Pattern closeCurlyBracketIdentifierPattern;
    Pattern conditionIdentifierPattern;


    Matcher whileIdentifierMatcher;
    Matcher forIdentifierMatcher;
    Matcher whileWithSemicolenIdentifierMatcher;
    Matcher forWithSemicolenIdentifierMatcher;
    Matcher whileWithCurlyBracketIdentifierMatcher;
    Matcher forWithCurlyBracketIdentifierMatcher;
    Matcher doWithCurlyBracketIdentifierMatcher;
    Matcher closeCurlyBracketIdentifierMatcher;
    Matcher conditionIdentifierMatcher;

    public static int semicolenLoopIdentifire;
    public static boolean singleLoopCall;

    public static ArrayList<Line> linesWithValues;

    LoopBracketStack loopBracketStack;
    ConditionStack conditionStack;



    public LoopPatternChecker() {
         whileIdentifierPattern = Pattern.compile(whileChecker);
         forIdentifierPattern = Pattern.compile(forChecker);
         whileWithSemicolenIdentifierPattern = Pattern.compile(whileCheckerWithSemicolen);
         forWithSemicolenIdentifierPattern = Pattern.compile(forCheckerWithSemicolen);
         whileWithCurlyBracketIdentifierPattern = Pattern.compile(whileCheckerWithCurlyBracket);
         forWithCurlyBracketIdentifierPattern = Pattern.compile(forCheckerWithCurlyBracket);
         doWithCurlyBracketIdentifierPattern = Pattern.compile(doCheckerWithCurlyBracket);
         closeCurlyBracketIdentifierPattern = Pattern.compile(closeCurlyBracket);
         conditionIdentifierPattern = Pattern.compile(STATEMENT_IDENTIFIER);

         loopBracketStack = new LoopBracketStack();
         conditionStack = new ConditionStack();
         linesWithValues = new ArrayList<Line>();
         singleLoopCall = false;
        //recursiveCallIdentifierPattern = Pattern.compile(RECURSIVE_CALL_IDENTIFIER);
    }

    public ArrayList<Line> bracketChecker(ArrayList<Line> st){
        for(Line statement:st) {


            conditionIdentifierMatcher = conditionIdentifierPattern.matcher(statement.getLine());
            while (conditionIdentifierMatcher.find()) {
                conditionStack.push(conditionIdentifierMatcher.group(1));
                //  System.out.println(conditionIdentifierMatcher.group(1));
            }


            int pushStatusValue = 0;

            if (singleLoopCall) {
                if ((statement.getLine().replaceAll("\\s", "").charAt(0)) == '{') {
                    loopBracketStack.push("{");
                    pushStatusValue = 0;
                    singleLoopCall = false;
                } else {
                    //loopBracketStack.pop();
                    pushStatusValue++;
                    singleLoopCall = false;
                }
            }


            callPattern(statement.getLine());


            if (whileIdentifierMatcher.find()) {
                callPattern(statement.getLine());
                if (whileWithSemicolenIdentifierMatcher.find()) {
                    callPattern(statement.getLine());
                    while (whileWithSemicolenIdentifierMatcher.find()) {
                        semicolenLoopIdentifire++;
                    }
                }

                if (whileWithCurlyBracketIdentifierMatcher.find()) {
                    callPattern(statement.getLine());
                    while (whileWithCurlyBracketIdentifierMatcher.find()) {
                        loopBracketStack.push("{");
                    }
                } else if (whileIdentifierMatcher.find()) {
                    pushStatusValue = 1;
                    singleLoopCall = true;
                }

            }

            if (forIdentifierMatcher.find()) {
                callPattern(statement.getLine());
                if (forWithSemicolenIdentifierMatcher.find()) {
                    callPattern(statement.getLine());
                    while (forWithSemicolenIdentifierMatcher.find()) {
                        semicolenLoopIdentifire++;
                    }
                }


                if (forWithCurlyBracketIdentifierMatcher.find()) {
                    callPattern(statement.getLine());
                    while (forWithCurlyBracketIdentifierMatcher.find()) {
                        loopBracketStack.push("{");
                    }
                } else if (forIdentifierMatcher.find()) {
                    pushStatusValue = 1;
                    singleLoopCall = true;
                }

            }

            if (closeCurlyBracketIdentifierMatcher.find()) {
                callPattern(statement.getLine());
                if (loopBracketStack.size() != 0)
                    while (closeCurlyBracketIdentifierMatcher.find()) {
                        String value = conditionStack.pop();
                        if (value.equalsIgnoreCase("while") || value.equalsIgnoreCase("for")) {
                            loopBracketStack.pop();
                        }
                    }
            }


        //    System.out.println();
            linesWithValues.add(new Line(statement.getLine(), loopBracketStack.size() + semicolenLoopIdentifire + pushStatusValue+statement.getValue()));
           // System.out.println(statement.getLine() + "::" + (loopBracketStack.size() + semicolenLoopIdentifire + pushStatusValue+statement.getValue()));

            if (!(semicolenLoopIdentifire >= 0)) {
                semicolenLoopIdentifire = 0;
            }
        }
        return linesWithValues;
    }


    public void callPattern(String statement){
        whileIdentifierMatcher = whileIdentifierPattern.matcher(statement);
        forIdentifierMatcher = forIdentifierPattern.matcher(statement);
        whileWithSemicolenIdentifierMatcher = whileWithSemicolenIdentifierPattern.matcher(statement);
        whileWithCurlyBracketIdentifierMatcher = whileWithCurlyBracketIdentifierPattern.matcher(statement);
        forWithSemicolenIdentifierMatcher = forWithSemicolenIdentifierPattern.matcher(statement);
        forWithCurlyBracketIdentifierMatcher = forWithCurlyBracketIdentifierPattern.matcher(statement);
        closeCurlyBracketIdentifierMatcher = closeCurlyBracketIdentifierPattern.matcher(statement);
    }

}
