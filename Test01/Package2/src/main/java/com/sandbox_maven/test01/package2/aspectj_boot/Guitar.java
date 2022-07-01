package com.sandbox_maven.test01.package2.aspectj_boot;

public class Guitar {
    private String brand =" Martin";
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String play(){
        return "G C G C Am D7";
    }
}