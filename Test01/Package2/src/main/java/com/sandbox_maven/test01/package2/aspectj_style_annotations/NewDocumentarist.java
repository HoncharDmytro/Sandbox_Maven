package com.sandbox_maven.test01.package2.aspectj_style_annotations;

import com.sandbox_maven.test01.package2.proxyfactorybean_annotation.models.Guitar;
import org.springframework.stereotype.Component;

@Component("documentarist")
public class NewDocumentarist {
    protected GrammyGuitarist guitarist;

//    @Autowired //wasn't work without it
//    @Qualifier("johnMayer") //don't need it
//    public void setGuitarist(GrammyGuitarist guitarist) {
//        this.guitarist = guitarist;
//    }
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