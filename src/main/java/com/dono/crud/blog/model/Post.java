package com.dono.crud.blog.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id"})
@RequiredArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    @NonNull private String title;
    @Lob
    @NonNull private String body;
    @Column(columnDefinition = "date")
    @NonNull private LocalDate createdDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Reader reader;

}
