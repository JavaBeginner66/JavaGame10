package model;

import java.util.TreeMap;

/**
 * Singleton class which contains all
 * value variables
 */

public class ValueContainer {

    public final static int taskValue = 1000000;
    public final static int incomeValue1 = 400;
    public final static int incomeValue2 = 1000;
    public final static int incomeValue3 = 1600;

    public final static int energyValue1 = 3000;
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

        double task = 100;
        double income1 = 300;
        double income2 = 350;
        double income3 = 180;

        double time1 = 300;
        double time2 = 900;
        double time3 = 160;

        double energy1 = 50;
        double energy2 = 180;
        double energy3 = 720;

        double strength1 = 180;
        double strength2 = 120;
        double strength3 = 144;

        double passive1 = 100;
        double passive3 = 100;
        double passive4 = 500;
        double passive2 = 800;

        double goldMultiplier = 1;
        double energyMultiplier = 1;
        double strengthMultiplier = 1;
        double timeMultiplier = 1;

        values.put("task", task);
        values.put("passive1", passive1);
        values.put("passive4", passive4);
        values.put("goldMultiplier", goldMultiplier);
        values.put("energyMultiplier", energyMultiplier);
        values.put("strengthMultiplier", strengthMultiplier);
        values.put("timeMultiplier", strengthMultiplier);
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
        values.put("passive3", passive3);
        values.put("passive2", passive2);


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


