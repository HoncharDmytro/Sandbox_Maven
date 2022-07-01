package com.sandbox_maven.test01.package2.proxyfactorybean_xml;

public class Documentarist {
    private Singer singer;

    public void setGuitarist(Singer singer) {
        this.singer = singer;
    }

    public void execute() {
        singer.sing();
        singer.talk();
    }
}