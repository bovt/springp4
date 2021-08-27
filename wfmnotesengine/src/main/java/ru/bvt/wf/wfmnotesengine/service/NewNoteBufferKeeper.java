package ru.bvt.wf.wfmnotesengine.service;

import ru.bvt.wf.wfmnotesengine.domain.Note;

import java.util.List;

public interface NewNoteBufferKeeper {
    void put(Note note);
    List<Note> get(long interval);
}
