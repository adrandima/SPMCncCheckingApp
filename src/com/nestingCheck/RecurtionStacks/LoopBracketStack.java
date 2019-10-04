package com.nestingCheck.RecurtionStacks;

import java.util.ArrayList;

public class LoopBracketStack {


    private ArrayList<String> loopBracketArray;

    public LoopBracketStack(){
        loopBracketArray = new ArrayList<String>();
    }

    public boolean cleanArrayList(){
        loopBracketArray.clear();
        return true;
    }

    public Boolean isEmpty() {
        if(loopBracketArray.size() == 0)
            return true;
        else
            return false;
    }



    public int size() {
        return loopBracketArray.size();
    }

    public void push(String x){
        loopBracketArray.add(x);
    }

    public void pop(){
        if (!isEmpty())
            loopBracketArray.remove(loopBracketArray.size() - 1);
        else
            throw new IllegalStateException("Stack is empty");

    }
}
