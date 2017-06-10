package com.dono.crud.blog;

import com.dono.crud.blog.model.Reader;
import com.dono.crud.blog.model.UserType;
import com.dono.crud.blog.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;

@SpringBootApplication
public class SpringBootApp extends WebMvcConfigurerAdapter implements CommandLineRunner {

//    @Autowired
//    private ReaderService readerService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        Reader reader = new Reader("bill", "pass", UserType.READER, LocalDate.now());
//        Post post = new Post("Post title", "Post body", LocalDate.now());
//        Post post2 = new Post("Post title 2 ", "Post body 2 ", LocalDate.now());
//        post.setReader(reader);
//        reader.addPost(post);
//        post2.setReader(reader);
//        reader.addPost(post2);
//
//        readerService.save(reader);
//
//        List<Post> posts = postRepository.findAllByReaderUsername(reader.getUsername());
//
//        posts.forEach(System.out::println);
    }
}