package ru.bvt.tgnotesagent.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bvt.tgnotesagent.feign.NoteFeignClientBotEdition;
import ru.bvt.tgnotesagent.rest.dto.BookDto;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceWithCBSimple implements BookServiceWithCB {

    private NoteFeignClientBotEdition noteFeignClientBotEdition;

    @HystrixCommand(fallbackMethod = "defaultGetAllBooks")
    public List<BookDto> getAllBooks() {
        return noteFeignClientBotEdition.showBookList();
    }

    private List<BookDto> defaultGetAllBooks() {
        List<BookDto> bookList = Arrays.asList(new BookDto(0,"N/A"));
        return bookList;
    }

}
