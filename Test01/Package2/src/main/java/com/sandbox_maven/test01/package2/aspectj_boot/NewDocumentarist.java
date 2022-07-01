package com.sandbox_maven.test01.package2.aspectj_boot;

import org.springframework.stereotype.Component;

@Component("documentarist")
public class NewDocumentarist {
    protected GrammyGuitarist guitarist;

    public NewDocumentarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();
    }
}