package ru.bvt.tgnotesagent.service;

import ru.bvt.tgnotesagent.rest.dto.NoteFullDto;

import java.util.List;

public interface NoteServiceWithCB {
    List<NoteFullDto> getAllNotes();
}
