package com.sandbox_maven.test01.package2.proxyfactorybean_annotation.models;

public class Guitar {
    private String brand =" Martin";

    public String play(){
        return "G C G C Am D7";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
