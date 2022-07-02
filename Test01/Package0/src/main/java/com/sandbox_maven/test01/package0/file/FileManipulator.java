package com.sandbox_maven.test01.package0.file;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManipulator { //just use Files.readAllBytes() and Files.write()
    private final File file;

    public FileManipulator(final File file) {
        this.file = file;
    }

    public FileManipulator(final String path) {
        this(new File(path));
    }

    public String getContent() {
        StringBuffer sb = new StringBuffer();
        String str;
        try(
            final FileInputStream  fis = new FileInputStream(file);
            final BufferedReader br =
                        new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
        ) {
            while ((str = br.readLine()) != null){
                sb.append(str).append("\n");
            }
            System.out.println("Input done!");
        }
        catch (IOException io){
            System.out.println("Input error!");
        }
        return sb.toString();
    }

    String path = "Test01\\Package0\\src\\main\\java\\com\\sandbox_maven\\test01\\package0\\file\\Text2.txt";

    public void saveContent(String content) {
        try(
            final FileOutputStream os = new FileOutputStream(new File(path));
            final BufferedWriter r = new BufferedWriter(new OutputStreamWriter(os));
        ) {
            r.write(content);
            System.out.println("Output done!");
        }
        catch (IOException io){
            System.out.println("Output error!");
        }
    }
}
