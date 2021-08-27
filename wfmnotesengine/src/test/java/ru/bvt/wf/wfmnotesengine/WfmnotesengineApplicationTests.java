package ru.bvt.wf.wfmnotesengine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.bvt.wf.wfmnotesengine.domain.Note;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WfmnotesengineApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testNoteStream() {
        List<Note> comments = webClient
                .get().uri("/flux/note/feed")
                .accept(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Note.class)
                .getResponseBody()
                .take(3) // take 3 comment objects
                .collectList()
                .block();

        comments.forEach(x -> System.out.println(x));

        assertEquals(3, comments.size());
    }
}


