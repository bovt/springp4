package ru.bvt.wf.wfmnotesengine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private String text;

    private String author;

    private Map<String, String> categories;

    public Note(String text) {
        this.text = text;
        categories = new HashMap<String, String>();
    }
/*
    public Note(String text, String author, Map<Long, String> categories) {
        this.text = text;
        this.author = author;
        this.categories = categories;
    }
*/

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

}
