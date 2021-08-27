package ru.bvt.jdbcnotesenginelite.service;

import ru.bvt.jdbcnotesenginelite.rest.dto.NoteBriefDto;
import ru.bvt.jdbcnotesenginelite.rest.dto.NoteDto;
import ru.bvt.jdbcnotesenginelite.rest.dto.NoteFullDto;

import java.util.List;

public interface NoteService {

    List<NoteFullDto> getAllNotes();

    long addNote(NoteDto newNoteDto);

    long addNote(NoteBriefDto newNoteBriefDto);

    NoteFullDto getNote(long id);

    long setNote(NoteFullDto noteFullDto);

    long deleteNote(long id);

}
