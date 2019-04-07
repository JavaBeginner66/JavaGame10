package model;

import java.util.TreeMap;

/**
 * Singleton class which contains all
 * value variables
 */

public class ValueContainer {

    public final static int stealValue = 1000000;
    public final static int incomeValue1 = 400;
    public final static int incomeValue2 = 1000;
    public final static int incomeValue3 = 1600;

    public final static int energyValue1 = 3;
    public final static int energyValue2 = 5;
    public final static int energyValue3 = 10;

    public final static int strengthValue1 = 2;
    public final static int strengthValue2 = 4;
    public final static int strengthValue3 = 6;

    public final static double timeCut1 = 0.99;
    public final static double timeCut2 = 0.98;
    public final static double timeCut3 = 0.95;

    public static boolean autoTaskUnlocked;
    public static boolean autoTimeUnlocked;
    public static boolean autoEnergyUnlocked;
    public static boolean autoStrengthUnlocked;


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

        /* The parameter value that gets sent into a task. 100 = 1 sec */

        double steal = 500;
        double income1 = 300;
        double income2 = 900;
        double income3 = 18000;

        double time1 = 300000;
        double time2 = 900000;
        double time3 = 1600000;

        double energy1 = 6000;
        double energy2 = 18000;
        double energy3 = 72000;

        double strength1 = 18000;
        double strength2 = 72000;
        double strength3 = 144000;

        double autoIncome = 1000000;
        double autoTime = 10000000;
        double autoEnergy = 5000000;
        double autoStrength = 8000000;

        double goldMultiplier = 1;
        double energyMultiplier = 1;
        double strengthMultiplier = 1;

        values.put("task", steal);
        values.put("autoIncome", autoIncome);
        values.put("autoEnergy", autoEnergy);
        values.put("goldMultiplier", goldMultiplier);
        values.put("energyMultiplier", energyMultiplier);
        values.put("strengthMultiplier", strengthMultiplier);
        values.put("income1", income1);
        values.put("income2", income2);
        values.put("income3", income3);
        values.put("time1", time1);
        values.put("time2", time2);
        values.put("time3", time3);
        values.put("energy1", energy1);
        values.put("energy2", energy2);
        values.put("energy3", energy3);
        values.put("strength1", strength1);
        values.put("strength2", strength2);
        values.put("strength3", strength3);
        values.put("autoTime", autoTime);
        values.put("autoStrength", autoStrength);


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


