package ru.otus.spring.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_date")
    private String postDate;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "content")
    private String content;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;
}
