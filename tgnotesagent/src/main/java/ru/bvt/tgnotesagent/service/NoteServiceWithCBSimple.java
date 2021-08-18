package ru.bvt.tgnotesagent.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bvt.tgnotesagent.feign.NoteFeignClientBotEdition;
import ru.bvt.tgnotesagent.rest.dto.NoteFullDto;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class NoteServiceWithCBSimple implements NoteServiceWithCB {

    private NoteFeignClientBotEdition noteFeignClientBotEdition;

    @HystrixCommand(fallbackMethod = "defaultGetAllNotes")
    public List<NoteFullDto> getAllNotes() {
        return noteFeignClientBotEdition.showNoteList();
    }

    private List<NoteFullDto> defaultGetAllNotes() {
        List<NoteFullDto> noteList = Arrays.asList(new NoteFullDto(0,"N/A",0,"N/A",0,"N/A",null,null,null));
        return noteList;
    }

}
