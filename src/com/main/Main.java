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

public class Main {


    public static void main(String[] args) throws IOException {
        CodeRunner codeRunner = new CodeRunner();
        codeRunner.Call();
    }

}
