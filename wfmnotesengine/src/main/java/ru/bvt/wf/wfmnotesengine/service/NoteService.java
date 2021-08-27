package ru.bvt.wf.wfmnotesengine.service;

import ru.bvt.wf.wfmnotesengine.domain.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();

    String addNote(Note noteDto);

    Note getNote(long id);

    long deleteNote(long id);

}
