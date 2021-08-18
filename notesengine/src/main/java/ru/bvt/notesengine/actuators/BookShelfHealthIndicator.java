package ru.bvt.notesengine.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.bvt.notesengine.repository.BookRepositoryExtended;
import ru.bvt.notesengine.repository.BookRepositoryExtendedSimple;

@Component
public class BookShelfHealthIndicator implements HealthIndicator {

    private BookRepositoryExtended bookRepository;

    @Autowired
    public void BookShelfHealthIndicator(BookRepositoryExtendedSimple bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        boolean bookshelfIsEmpty = (bookRepository.count() == 0);
        if (bookshelfIsEmpty) {
            return Health.down().status(Status.DOWN).withDetail("message", "Нет книг для заметок!").build();
        } else {
            return Health.up().withDetail("message", "Имеются доступные книги для заметок!").build();
        }
    }
}
