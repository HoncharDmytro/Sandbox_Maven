package com.sandbox_maven.test01.package2.aspectj_style_annotations;

import com.sandbox_maven.test01.package2.proxyfactorybean_annotation.models.Guitar;
import com.sandbox_maven.test01.package2.proxyfactorybean_xml.Singer;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("sing: Gravity is working against me\n" +
                "And gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    public void rest(){
        System.out.println("zzz");
    }

    public void talk(){
        System.out.println("talk");
    }
}