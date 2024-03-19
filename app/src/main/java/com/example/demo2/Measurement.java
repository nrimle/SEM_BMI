package com.example.demo2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Measurement implements Serializable {

    private double height;
    private double weight;

    private final List<String> categories = new ArrayList<>(
            Arrays.asList("Underweight", "Normal Weight", "Overweight")
    );

    public Measurement() {
    }

    public Measurement(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getCategories() {
        return categories;
    }

    public double getBMI() {
        if (height > 5) {
            height = height / 100;
        }
        return weight / (height * height);
    }

    public String getCategory() {
        double bmi = getBMI();
        if (bmi < 18.5) {
            return categories.get(0);
        } else if (bmi >= 18.5 && bmi < 25) {
            return categories.get(1);
        } else {
            return categories.get(2);
        }
    }
}
