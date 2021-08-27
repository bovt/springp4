package ru.bvt.jdbcnotesenginelite.domain;

//import org.hibernate.annotations.BatchSize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private long id;

    @Getter
    private String text;
    private Author author;
    private Book book;
    private List<Category> categories;

    public Note(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + text + '\'' +
                '}';
    }

}
