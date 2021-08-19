package ru.bvt.notesengine.service;

import ru.bvt.notesengine.domain.Note;
import ru.bvt.notesengine.rest.dto.NoteBriefDto;
import ru.bvt.notesengine.rest.dto.NoteDto;
import ru.bvt.notesengine.rest.dto.NoteFullDto;

import java.util.List;

public interface NoteService {

    List<NoteFullDto> getAllNotes();

    long addNote(NoteDto newNoteDto);

    long addNote(NoteBriefDto newNoteBriefDto);

    NoteFullDto getNote(long id);

    long setNote(NoteFullDto noteFullDto);

    long deleteNote(long id);

}
