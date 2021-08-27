package ru.bvt.wf.wfmnotesengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.bvt.wf.wfmnotesengine.domain.Category;
import ru.bvt.wf.wfmnotesengine.domain.Note;
import ru.bvt.wf.wfmnotesengine.repository.CategoryRepository;
import ru.bvt.wf.wfmnotesengine.repository.NoteRepository;
import ru.bvt.wf.wfmnotesengine.repository.ReactiveNoteRepository;
import ru.bvt.wf.wfmnotesengine.service.NoteService;
import ru.bvt.wf.wfmnotesengine.service.NoteServiceSimple;

import java.util.HashMap;
import java.util.Map;

@EnableMongoRepositories
@SpringBootApplication
public class WfmnotesengineApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WfmnotesengineApplication.class, args);

        NoteRepository noteRepository = context.getBean(NoteRepository.class);
        ReactiveNoteRepository noteReactiveRepository = context.getBean(ReactiveNoteRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        NoteService noteService = context.getBean(NoteService.class);
/*
        Note note1 = new Note("Заметка 1");
        Note note2 = new Note("Заметка 2");
        Note note3 = new Note("Заметка 3");

        Category category1 = new Category("Категория 1");
        Category category2 = new Category("Категория 2");
        Category category3 = new Category("Категория 3");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        Map<String, String> categoryMap1 = new HashMap<>();
        categoryMap1.put(category1.getId(), category1.getName());
        note1.setCategories(categoryMap1);
        noteRepository.save(note1);
        category1.addNewLastCreatedNotes(note1);

        Map<String, String> categoryMap2 = new HashMap<>();
        categoryMap2.put(category1.getId(), category1.getName());
        categoryMap2.put(category2.getId(), category2.getName());
        note2.setCategories(categoryMap2);
        noteRepository.save(note2);
        category1.addNewLastCreatedNotes(note2);
        category2.addNewLastCreatedNotes(note2);

        Map<String, String> categoryMap3 = new HashMap<>();
        categoryMap3.put(category1.getId(), category2.getName());
        categoryMap3.put(category2.getId(), category2.getName());
        categoryMap3.put(category3.getId(), category3.getName());
        note3.setCategories(categoryMap3);
        noteRepository.save(note3);
        category1.addNewLastCreatedNotes(note3);
        category2.addNewLastCreatedNotes(note3);
        category3.addNewLastCreatedNotes(note3);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        Thread.sleep(3000);
*/
        Note note4 = new Note("Заметка 1 #sdf #sdddde #f");
        Note note5 = new Note("Заметка 2 #sdf #sdddd343e #3f");
        Note note6 = new Note("Заметка 3");

        noteService.addNote(note4);
        noteService.addNote(note5);
        noteService.addNote(note6);

        noteReactiveRepository.findAll().subscribe(p -> System.out.println(p.getText()));
//        noteRepository.findAll().forEach(n -> System.out.println(n.getText()));
        categoryRepository.findAll().forEach(n -> System.out.println(n.getName()));

    }

}
