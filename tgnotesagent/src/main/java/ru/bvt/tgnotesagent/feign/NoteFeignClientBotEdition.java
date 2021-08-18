package ru.bvt.tgnotesagent.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bvt.tgnotesagent.model.NoteVO;
import ru.bvt.tgnotesagent.config.FeignClientConfiguration;
import ru.bvt.tgnotesagent.rest.dto.AuthorDto;
import ru.bvt.tgnotesagent.rest.dto.BookDto;
import ru.bvt.tgnotesagent.rest.dto.CategoryDto;
import ru.bvt.tgnotesagent.rest.dto.NoteFullDto;

import java.util.List;


@FeignClient(name = "note-client", url = "localhost:8080",
        configuration = FeignClientConfiguration.class)
//@FeignClient("note-client") - из советов в интернет, не заработало, отладка не увенчалась успехом
//TODO: Наладить подтягивание из конфига

public interface NoteFeignClientBotEdition {

    @PostMapping("/api/note/brief")
    void createNoteFromBriefDTO(NoteVO note);

    @GetMapping("/api/author")
    List<AuthorDto> showAuthorList();

    @GetMapping("/api/book")
    List<BookDto> showBookList();

    @GetMapping("/api/category")
    List<CategoryDto> showCategoryList();

    @GetMapping("/api/note")
    List<NoteFullDto> showNoteList();

}