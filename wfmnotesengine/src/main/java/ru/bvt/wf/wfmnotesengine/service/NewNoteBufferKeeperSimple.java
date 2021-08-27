package ru.bvt.wf.wfmnotesengine.service;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.bvt.wf.wfmnotesengine.domain.Note;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Scope("singleton")
@Data
@Service
public class NewNoteBufferKeeperSimple implements NewNoteBufferKeeper {
    private boolean readyForGet = false;
    private boolean readyForPut = true;
    private LinkedList<Note> noteBuffer;

    public NewNoteBufferKeeperSimple(){noteBuffer = new LinkedList<Note>();};

    synchronized public void put(Note note)
    {
        try {
            while (!readyForPut) {wait();}
        } catch (InterruptedException ex) {
            return;
        }
        readyForPut = false;
        readyForGet = false;
        noteBuffer.addFirst(note);
        readyForPut = true;
        readyForGet = true;
    }

    synchronized public List<Note> get(long interval)
    {
        Note note;

        try {
            while (!readyForGet) {wait();}
        } catch (InterruptedException ex) {
            return null;
        }

        readyForPut = false;
        readyForGet = false;
        note = noteBuffer.pollLast();
        if (!noteBuffer.isEmpty()) {readyForGet = true;}
        readyForPut = true;
        return Arrays.asList(note);
    }
}
