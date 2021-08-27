package ru.bvt.notesengine.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    public Author(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
