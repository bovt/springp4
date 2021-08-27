package ru.bvt.notesengine.actuators;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.bvt.notesengine.repository.BookRepository;

@AllArgsConstructor
@Component
public class BookShelfHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

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
