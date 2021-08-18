package ru.bvt.tgnotesagent.service;

import ru.bvt.tgnotesagent.rest.dto.NoteFullDto;

import java.util.List;

public interface NoteServiceWithCB {
    public List<NoteFullDto> getAllNotes();
}
