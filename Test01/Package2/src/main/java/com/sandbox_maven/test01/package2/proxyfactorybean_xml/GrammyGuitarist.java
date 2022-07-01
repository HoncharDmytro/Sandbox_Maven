package com.sandbox_maven.test01.package2.proxyfactorybean_xml;

public class GrammyGuitarist implements Singer { //Interface implementation need for using JDK AOP!!!
    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me\n" +
                "And gravity wants to bring me down");
    }

    @Override
    public void talk(){
        System.out.println("talk");
    }
}