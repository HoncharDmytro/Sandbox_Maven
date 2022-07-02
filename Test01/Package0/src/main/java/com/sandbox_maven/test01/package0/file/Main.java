package com.sandbox_maven.test01.package0.file;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String path = "Test01\\Package0\\src\\main\\java\\com\\sandbox_maven\\test01\\package0\\file\\Text.txt";
        File file = new File(path);
        FileManipulator fm = new FileManipulator(file);
        String str = fm.getContent();
        fm.saveContent(str);
        System.out.println();
    }
}
