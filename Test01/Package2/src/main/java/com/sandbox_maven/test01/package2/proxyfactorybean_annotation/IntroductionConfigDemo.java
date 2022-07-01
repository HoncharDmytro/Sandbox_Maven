package com.sandbox_maven.test01.package2.proxyfactorybean_annotation;

import com.sandbox_maven.test01.package2.proxyfactorybean_annotation.config.AppConfig;
import com.sandbox_maven.test01.package2.proxyfactorybean_annotation.models.Contact;
import com.sandbox_maven.test01.package2.proxyfactorybean_annotation.status.IsModified;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class IntroductionConfigDemo {
    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Contact bean = (Contact) ctx.getBean("bean");
        IsModified mod = (IsModified) bean;

        System.out.println("Is Contact?: " + (bean instanceof Contact));
        System.out.println("Is IsModified?: " + (bean instanceof IsModified));

        System.out.println("Has been modified?: " + mod.isModified());
        bean.setName("John Mayer");

        System.out.println("Has been modified?: " + mod.isModified());
        bean.setName("Eric Clapton");

        System.out.println("Has been modified?: " + mod.isModified());
    }
}
