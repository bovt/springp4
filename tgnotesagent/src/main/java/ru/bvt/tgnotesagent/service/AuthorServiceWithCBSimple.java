package ru.bvt.tgnotesagent.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bvt.tgnotesagent.feign.NoteFeignClientBotEdition;
import ru.bvt.tgnotesagent.rest.dto.AuthorDto;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthorServiceWithCBSimple implements AuthorServiceWithCB {

    private NoteFeignClientBotEdition noteFeignClientBotEdition;

    @HystrixCommand(fallbackMethod = "defaultGetAllAuthors")
    public List<AuthorDto> getAllAuthors() {
        return noteFeignClientBotEdition.showAuthorList();
    }

    private List<AuthorDto> defaultGetAllAuthors() {
        List<AuthorDto> authorList = Arrays.asList(new AuthorDto(0, "N/A", "N/A"));
        return authorList;
    }

}
