package model;

import java.util.TreeMap;

/**
 * Singleton class which contains all
 * value variables
 */

public class ValueContainer {

    public final static int stealValue = 100;
    public final static int incomeValue1 = 300;
    public final static int incomeValue2 = 500;
    public final static int incomeValue3 = 1000;

    public final static double timeCut1 = 0.99;
    public final static double timeCut2 = 0.95;
    public final static double timeCut3 = 0.90;

    public static boolean autoStealUnlocked;
    public static boolean autoTimeUnlocked;


    private static ValueContainer instance;

    private static TreeMap<String, Double> values = new TreeMap<>();

    private ValueContainer(){}

    public static synchronized ValueContainer getInstance(){
        if(instance == null){
            mapValues();
            instance = new ValueContainer();
        }
        return instance;
    }

    private static void mapValues(){
        double steal = 100;
        double income1 = 300;
        double income2 = 500;
        double income3 = 1000;

        double time1 = 370;
        double time2 = 280;
        double time3 = 420;

        double autoIncome = 150;
        double autoTime = 500;

        double goldMultiplier = 10;

        values.put("steal", steal);
        values.put("autoIncome", autoIncome);
        values.put("multiplier", goldMultiplier);
        values.put("income1", income1);
        values.put("income2", income2);
        values.put("income3", income3);
        values.put("time1", time1);
        values.put("time2", time2);
        values.put("time3", time3);
        values.put("autoTime", autoTime);


    }

    public double getValue(String key){
        return values.get(key);
    }



    public TreeMap getValueMap(){
        return values;
    }

    public synchronized void setValue(String key, double newValue){
        if(values.containsKey(key)){
            values.put(key, newValue);
        }
    }

}


