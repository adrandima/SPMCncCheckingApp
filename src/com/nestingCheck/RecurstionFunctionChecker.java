package com.nestingCheck;

import com.nestingCheck.Model.Line;
import com.nestingCheck.RecurtionStacks.RecursionBracketStack;
import com.sun.security.jgss.GSSUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecurstionFunctionChecker {

    private final String METHOD_IDENTIFIER = "^\\s|(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
    private final String BRACKET_OPEN = "\\s*\\{";
    private final String BRACKET_CLOSE = "\\s*\\}";
    private String METHOD_CALL_PATTERN = "\\s*\\([^)]*\\)\\s*;";

    Pattern methodIdentifierPattern;
    Pattern openBracketIdentifierPattern;
    Pattern closeBracketIdentifierPattern;
    Pattern methodCallIdentifierPattern;


    Matcher methodIdentifierMatcher;
    Matcher openBracketIdentifierMatcher;
    Matcher closeBracketIdentifierMatcher;
    Matcher methodCallIdentifierMatcher;

    private boolean recurstionStatus;

    RecursionBracketStack recursionBracketStack;
    ArrayList<Line> lineArrayList = new ArrayList<>();
    String methodName;
    public int cummaValue = 100;
    private int lineValue = 0;


    public RecurstionFunctionChecker() {
        recursionBracketStack = new RecursionBracketStack();

        methodIdentifierPattern = Pattern.compile(METHOD_IDENTIFIER);
        openBracketIdentifierPattern = Pattern.compile(BRACKET_OPEN);
        closeBracketIdentifierPattern = Pattern.compile(BRACKET_CLOSE);

        recurstionStatus = false;
        //recursiveCallIdentifierPattern = Pattern.compile(RECURSIVE_CALL_IDENTIFIER);
    }

    public int checkRecursiveFunction(String statement) {
        int bracketStatus = 0;
        methodIdentifierMatcher = methodIdentifierPattern.matcher(statement.trim());
        openBracketIdentifierMatcher = openBracketIdentifierPattern.matcher(statement);
        closeBracketIdentifierMatcher = closeBracketIdentifierPattern.matcher(statement);


        if (methodIdentifierMatcher.matches()) {
            int openBracketValue = statement.indexOf("(");;
            int closeBracketValue = statement.indexOf(")");
            String bracketValue = statement.substring(openBracketValue,closeBracketValue);

            int i = 0;
            Pattern p = Pattern.compile(",");
            Matcher m = p.matcher(bracketValue);
            while (m.find()) {
                i++;
            }

            cummaValue = i;

            methodName = methodIdentifierMatcher.group(2);
            recursionBracketStack.cleanArrayList();
            lineArrayList = new ArrayList<>();
            while (openBracketIdentifierMatcher.find()){
                recursionBracketStack.push("{");
            }
        }else{

            while (openBracketIdentifierMatcher.find()){
                recursionBracketStack.push("{");
            }
            while (closeBracketIdentifierMatcher.find()){
                try {
                    recursionBracketStack.pop();
                }catch (IllegalStateException e){
                    return 0;
                }
            }
            if(!recurstionStatus) {
                if (methodName != null){
                    if (statement.contains(methodName)) {
                      //  methodCallIdentifierPattern = Pattern.compile(String.valueOf(methodName.trim()+"("));
                       // methodCallIdentifierMatcher = methodCallIdentifierPattern.matcher(statement);
                        //System.out.println(methodName+METHOD_CALL_PATTERN);
                       // if(methodCallIdentifierMatcher.matches()){
                           // System.out.println("Found");
                                int openBracketValue = statement.indexOf("(");
                                ;
                                int closeBracketValue = statement.indexOf(")");
                                String callMethodName = statement.substring(openBracketValue, closeBracketValue);

                                int i = 0;
                                Pattern p = Pattern.compile(",");
                                Matcher m = p.matcher(callMethodName);
                                while (m.find()) {
                                    i++;
                                }
                                if (cummaValue == i) {
                                    editLineValue();
                                    recurstionStatus = true;
                                    lineValue = 1;
                                }
                        //    }
                    }
                }
            }
        }
       // System.out.println(statement+":::"+lineValue+":::Bracket Value::::"+recursionBracketStack.size());
        lineArrayList.add(new Line(statement,lineValue));


            return recursionBracketStack.size() ;



    }

    public ArrayList<Line> getLineArrayList(){
        lineValue = 0;
        cummaValue = 100;
        recurstionStatus = false;
        return lineArrayList;
    }

    public void editLineValue(){
        for (int a = 0;a<=lineArrayList.size()-1;a++){
            lineArrayList.get(a).setValue(1);
          //  System.out.println("asdasdasd"+lineArrayList.get(a).getValue());

        }

    }

    public void displayValuesWithLine(){
        for (int a = 0;a<=lineArrayList.size()-1;a++){
           System.out.println(lineArrayList.get(a).getLine()+"::"+lineArrayList.get(a).getValue());
        }

    }

}
