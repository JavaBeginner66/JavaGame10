package model;

import java.util.TreeMap;

/**
 * Singleton class which contains all
 * value variables
 */

public class ValueContainer {

    private static ValueContainer instance;

    private static TreeMap<String, Integer> values = new TreeMap<>();

    private ValueContainer(){}

    public static synchronized ValueContainer getInstance(){
        if(instance == null){
            mapValues();
            instance = new ValueContainer();
        }
        return instance;
    }

    private static void mapValues(){
        int basicAttack = 100;
        int autoAttack = 150;
        int goldMultiplier = 10;

        values.put("basic", basicAttack);
        values.put("auto", autoAttack);
        values.put("multiplier", goldMultiplier);


    }

    int getValue(String key){
        return values.get(key);
    }
}


