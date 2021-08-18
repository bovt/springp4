package ru.bvt.notesengine.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotePagesController {

    @GetMapping("/aindex")
    public String alistPage(Model model) {
        model.addAttribute("keywords", "list authors");
        return "aindex";
    }

    @GetMapping(value = {"/nindex", "/index"})
    public String nlistPage(Model model) {
        model.addAttribute("keywords", "list notes");
        return "nindex";
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
}
