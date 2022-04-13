package ru.otus.spring.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Genre {

    private Long id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) return false;
        Genre genre = (Genre) o;
        if (this.name != null && genre.name != null)
            return (this.id.equals(genre.id) &&
                    this.name.equals(genre.name));
        else return Objects.equals(id, genre.id) &&
                Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
