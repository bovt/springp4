package ru.bvt.wf.wfmnotesengine.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import ru.bvt.wf.wfmnotesengine.repository.ReactiveNoteRepository;

@AllArgsConstructor
@Controller
public class NotePagesController {
    private final ReactiveNoteRepository noteRepository;

    @GetMapping("/nfeed")
    public String nFeedPage(final Model model) {
        model.addAttribute("keywords", "notes feed");
        return "nfeed";
    }


    @GetMapping("/aindex")
    public String alistPage(Model model) {
        model.addAttribute("keywords", "list authors");
        return "aindex";
    }

    @GetMapping(value = {"/nindex", "/index"})
    public String nlistPage(Model model) {
        model.addAttribute("keywords", "list notes");
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(noteRepository.findAll(), 1);
        model.addAttribute("notes", reactiveDataDrivenMode);
        return "nindex";
    }
/*
    @GetMapping(value = {"/nedit/{noteId}"})
    public String neditPage(@PathVariable("noteId") long noteId, Model model) {

       model.addAttribute("keywords", "edit note");
        model.addAttribute("add", false);
        NoteFullDto noteFullDto = new NoteFullDto();
        noteFullDto.setId(noteId);
        noteFullDto.setText("Update!");
        noteFullDto.setBookId(1);
        model.addAttribute("note", noteFullDto);
        return "nedit";
    }

    @GetMapping(value = {"/nadd"})
    public String naddPage(Model model) {

         model.addAttribute("keywords", "add note");
        model.addAttribute("add", true);
        NoteFullDto noteFullDto = new NoteFullDto();
        noteFullDto.setId(1);
        noteFullDto.setText("create!");
        noteFullDto.setBookId(1);
        model.addAttribute("note", noteFullDto);
        return "nedit";
    }


    @GetMapping("/bindex")
    public String blistPage(Model model) {
        model.addAttribute("keywords", "list books");
        return "bindex";
    }

    @GetMapping("/cindex")
    public String clistPage(Model model) {
        model.addAttribute("keywords", "list categories");
        return "cindex";
    }
*/
}

