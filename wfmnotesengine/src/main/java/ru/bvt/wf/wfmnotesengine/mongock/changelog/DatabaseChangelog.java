package ru.bvt.wf.wfmnotesengine.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.bvt.wf.wfmnotesengine.domain.Note;
import ru.bvt.wf.wfmnotesengine.repository.NoteRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "bvt", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertLermontov", author = "bvt")
    public void insertLermontov(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("notes");
        var doc = new Document().append("text", "Lermontov");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertPushkin", author = "bvt")
    public void insertPushkin(NoteRepository repository) {
        repository.save(new Note("Pushkin"));
    }
}
