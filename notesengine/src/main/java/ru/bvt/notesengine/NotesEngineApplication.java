package ru.bvt.notesengine;

import org.h2.tools.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ru.bvt.notesengine.domain.Note;
import ru.bvt.notesengine.repository.NoteRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableWebSecurity
public class NotesEngineApplication {
    static final Logger log =
            LoggerFactory.getLogger(NotesEngineApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NotesEngineApplication.class, args);
//               Console.main(args);
        log.debug("Starting my application in debug with {} args", args.length);
        log.info("Starting my application with {} args.", args.length);
    }
}
