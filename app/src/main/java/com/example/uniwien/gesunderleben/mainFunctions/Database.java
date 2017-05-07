package com.example.uniwien.gesunderleben.mainFunctions;

import java.util.ArrayList;

/**
 * Created by Raf on 07.05.2017.
 */

public class Database {

    public ArrayList<String> getFood(){

        ArrayList<String> food = new ArrayList<String>();

        food.add("Banane");
        food.add("0.2");
        food.add("1");
        food.add("20");
        food.add("93");

        food.add("Orange");
        food.add("0.2");
        food.add("1");
        food.add("8.3");
        food.add("47");

        food.add("Kiwi");
        food.add("0.6");
        food.add("1");
        food.add("9.1");
        food.add("62");

        return food;

    }

    public ArrayList<String> getFoodName(){
        ArrayList<String> food = getFood();
        ArrayList<String> foodName = new ArrayList();
        for(int i=0;i<food.size();++i){
            if(i%5==0){
                foodName.add(food.get(i));
            }
        }
        return foodName;
    }

}
