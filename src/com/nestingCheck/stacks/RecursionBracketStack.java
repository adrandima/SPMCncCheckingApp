package com.nestingCheck.stacks;

import java.util.ArrayList;

public class RecursionBracketStack {

        private ArrayList<String> bracketArray;

        public RecursionBracketStack(){
            bracketArray = new ArrayList<String>();
        }

        public boolean cleanArrayList(){
            bracketArray.clear();
            return true;
        }

        public Boolean isEmpty() {
            if(bracketArray.size() == 0)
                return true;
            else
                return false;
        }



        public int size() {
            return bracketArray.size();
        }

        public void push(String x){
            bracketArray.add(x);
        }

        public void pop(){
            if (!isEmpty())
                bracketArray.remove(bracketArray.size() - 1);
            else
                throw new IllegalStateException("Stack is empty");

        }

}
