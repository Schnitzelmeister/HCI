package com.example.uniwien.gesunderleben.mainFunctions;

import java.util.ArrayList;

/**
 * Created by Raf on 07.05.2017.
 */

public class Database {

    public ArrayList<String> getFood(){

        ArrayList<String> food = new ArrayList();

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


    public ArrayList<String> getSports() {

        ArrayList<String> sports = new ArrayList();

        sports.add("Aerobik (locker)");
        sports.add("0.1");

        sports.add("Walking (mittel)");
        sports.add("0.0917");

        sports.add("Joggen (langsam)");
        sports.add("0.1333");

        return sports;
    }

    public ArrayList<String> getSportName(){

        ArrayList<String> sports = getSports();
        ArrayList<String> sportname = new ArrayList();

        for(int i=0;i<sports.size();++i){
            if(i%2==0){
                sportname.add(sports.get(i));
            }
        }

        return sportname;
    }

    public ArrayList<String> getActivityGrade(){

        ArrayList<String> activity_grade = new ArrayList();

        activity_grade.add("Nachtruhe");
        activity_grade.add("0.95");

        activity_grade.add("überwiegend sitzend, keine Freizeitaktivitäten");
        activity_grade.add("1.2");

        activity_grade.add("überwiegend sitzend, Freizeitaktivitäten");
        activity_grade.add("1.3");

        return activity_grade;
    }

    public ArrayList<String> getActivityGradeDescription(){

        ArrayList<String> activityGrades = getActivityGrade();
        ArrayList<String> activityGradeDescription = new ArrayList();

        for(int i=0;i<activityGrades.size();++i){
            if(i%2==0){
                activityGradeDescription.add(activityGrades.get(i));
            }
        }

        return activityGradeDescription;
    }
}
