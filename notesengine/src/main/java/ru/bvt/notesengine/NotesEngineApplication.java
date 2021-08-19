package ru.bvt.notesengine;

import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bvt.notesengine.domain.Note;
import ru.bvt.notesengine.repository.NoteRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NotesEngineApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NotesEngineApplication.class, args);
//               Console.main(args);

    }
}
