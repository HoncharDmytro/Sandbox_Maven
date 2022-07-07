package com.sandbox_maven.package3;

import com.sandbox_maven.package3.entities.Book;
import com.sandbox_maven.package3.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {
    private final BookRepository bookRepository;
    //@Autowired
    public SpringAopApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @Override
    public void run(String[] args) {
        Book book1 = new Book("Thinking in Java", "Bruce Eckel");
        Book book2 = new Book("Effective Java", "Joshua Bloch");

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
