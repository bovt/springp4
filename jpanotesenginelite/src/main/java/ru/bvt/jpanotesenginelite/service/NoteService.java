package ru.bvt.jpanotesenginelite.service;

import ru.bvt.jpanotesenginelite.rest.dto.NoteBriefDto;
import ru.bvt.jpanotesenginelite.rest.dto.NoteDto;
import ru.bvt.jpanotesenginelite.rest.dto.NoteFullDto;

import java.util.List;

public interface NoteService {

    List<NoteFullDto> getAllNotes();

    long addNote(NoteDto newNoteDto);

    long addNote(NoteBriefDto newNoteBriefDto);

    NoteFullDto getNote(long id);

    long setNote(NoteFullDto noteFullDto);

    long deleteNote(long id);

}
