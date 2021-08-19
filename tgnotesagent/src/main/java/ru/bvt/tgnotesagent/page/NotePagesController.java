package ru.bvt.tgnotesagent.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotePagesController {

    @GetMapping("/aindex-cb")
    public String aCBlistPage(Model model) {
        model.addAttribute("keywords", "list authors");
        return "aindex-cb";
    }

    @GetMapping(value = {"/nindex-cb", "/index"})
    public String nCBlistPage(Model model) {
        model.addAttribute("keywords", "list notes");
        return "nindex-cb";
    }

    @GetMapping("/bindex-cb")
    public String bCBlistPage(Model model) {
        model.addAttribute("keywords", "list books");
        return "bindex-cb";
    }

    @GetMapping("/cindex-cb")
    public String cCBlistPage(Model model) {

        model.addAttribute("keywords", "list categories");
        return "cindex-cb";
    }
}
