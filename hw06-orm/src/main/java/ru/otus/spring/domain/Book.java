package ru.otus.spring.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "book-with-author-graph", attributeNodes = {
                @NamedAttributeNode("author")
        }),
        @NamedEntityGraph(name = "book-with-genre-graph", attributeNodes = {
                @NamedAttributeNode("genre")
        }),
        @NamedEntityGraph(name = "book-with-author-and-genre-graph", attributeNodes = {
                @NamedAttributeNode("author"),
                @NamedAttributeNode("genre")
        }),
        @NamedEntityGraph(name = "book-with-comments-graph", attributeNodes = {
                @NamedAttributeNode(value = "commentaries")
        })
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id")
    private Author author;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 10)
    @OneToMany(targetEntity = Commentary.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private List<Commentary> commentaries;

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre);
    }
}
