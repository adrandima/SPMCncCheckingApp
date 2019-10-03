package com.nestingCheck;

import com.nestingCheck.stacks.RecursionBracketStack;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecurstionFunctionChecker {

    final String METHOD_IDENTIFIER = "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";
    //final String RECURSIVE_CALL_IDENTIFIER = "(?!\\bif\\b|\\bfor\\b|\\bwhile\\b|\\bswitch\\b|\\btry\\b|\\bcatch\\b)(\\b[\\w]+\\b)[\\s\\n\\r]*(?=\\(.*\\))";

    Pattern methodIdentifierPattern;
    //Pattern recursiveCallIdentifierPattern;

    Matcher methodIdentifierMatcher;
    //Matcher recursiveCallIdentifierMatcher;

    RecursionBracketStack recursionBracketStack;
    String methodName;



    public RecurstionFunctionChecker() {
        recursionBracketStack = new RecursionBracketStack();
        methodIdentifierPattern = Pattern.compile(METHOD_IDENTIFIER);
        //recursiveCallIdentifierPattern = Pattern.compile(RECURSIVE_CALL_IDENTIFIER);
    }

    public String checkRecursiveFunction(String statement) {
        methodIdentifierMatcher = methodIdentifierPattern.matcher(statement);
        //recursiveCallIdentifierMatcher = recursiveCallIdentifierPattern.matcher(statement);

        if(methodIdentifierMatcher.matches()){
            if(!(methodIdentifierMatcher.group(2).trim().replaceAll("[ ]{2,}", " ").contains("else if"))){
                methodName = methodIdentifierMatcher.group(2);
/*                if(!recursionBracketStack.isEmpty()){
                    System.out.println("Compilation Error!");
                    return false;
                }*/



                recursionBracketStack.cleanArrayList();
                if(statement.contains("{"))
                    recursionBracketStack.push("{");
                return "loop";
            }
        }else{

            if(statement.contains("{")||statement.contains("}")){
                if(statement.contains("{")){

                    int matchCount = StringUtils.countMatches(statement, "{");
                    for(int i=1;i<=matchCount;i++){
                        recursionBracketStack.push("{");
                    }

                }else if(statement.contains("}")){

                    int matchCount = StringUtils.countMatches(statement, "}");
                    for(int i=1;i<=matchCount;i++){
                        recursionBracketStack.pop();
                    }
                }

            }

        }

        return "true";
    }
}
