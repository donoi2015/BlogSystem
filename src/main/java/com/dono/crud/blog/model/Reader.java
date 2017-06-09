package com.dono.crud.blog.model;


import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString(exclude = "posts")
@EqualsAndHashCode(exclude = {"id"})
@RequiredArgsConstructor
@NoArgsConstructor
public class Reader implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    @NonNull
    private String username;
    @NonNull
    private String password;
    @Enumerated(EnumType.STRING)
    @NonNull
    private UserType userType;
    @NonNull
    private LocalDate userCreatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reader")
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }

    public int numOfPosts(){
        return posts.size();
    }


}
