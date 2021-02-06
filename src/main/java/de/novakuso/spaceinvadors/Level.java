package de.novakuso.spaceinvadors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level {
    static HashMap<String, Double> level0 = new HashMap<String, Double>();
    static HashMap<String, Double> level1 = new HashMap<String, Double>();
    static HashMap<String, Double> level2 = new HashMap<String, Double>();
    static HashMap<String, Double> level3 = new HashMap<String, Double>();
    static List<HashMap> Levels = new ArrayList<>();
    private double maxObjects;
    private double maxShots;
    private double objectSpeed;
    private double shotSpeed;
    private double objectReloadSpeed;
    private double shotReloadSpeed;

    public static void setUpLevelArray() {
        Levels.add(0, level0);
        Levels.add(1, level1);
        Levels.add(2, level2);
        Levels.add(3, level3);
    }

    @Override
    public String toString() {
        return "Level{" +
                "maxObjects=" + maxObjects +
                ", maxShots=" + maxShots +
                ", objectSpeed=" + objectSpeed +
                ", shotSpeed=" + shotSpeed +
                ", objectReloadSpeed=" + objectReloadSpeed +
                ", shotReloadSpeed=" + shotReloadSpeed +
                '}';
    }

    //getter
    public double getMaxObjects() {
        return maxObjects;
    }

    public double getMaxShots() {
        return maxShots;
    }

    public double getObjectSpeed() {
        return objectSpeed;
    }

    public double getShotSpeed() {
        return shotSpeed;
    }

    public double getObjectReloadSpeed() {
        return objectReloadSpeed;
    }

    public double getShotReloadSpeed() {
        return shotReloadSpeed;
    }
}

