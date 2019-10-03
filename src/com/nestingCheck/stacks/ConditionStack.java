package com.nestingCheck.stacks;

import java.util.ArrayList;

public class ConditionStack {


        private ArrayList<String> ConditionsArray;

        public ConditionStack(){
            ConditionsArray = new ArrayList<String>();
        }

        public boolean cleanArrayList(){
            ConditionsArray.clear();
            return true;
        }

        public Boolean isEmpty() {
            if(ConditionsArray.size() == 0)
                return true;
            else
                return false;
        }



        public int size() {
            return ConditionsArray.size();
        }

        public void push(String x){
            ConditionsArray.add(x);
        }

        public String pop(){
            if (!isEmpty()) {
                int value = (ConditionsArray.size() - 1);
                String name = ConditionsArray.get(value);
                ConditionsArray.remove(ConditionsArray.size() - 1);
                return name;
            }else
                throw new IllegalStateException("Stack is empty");

        }


}
