package ru.bvt.tgnotesagent.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.bvt.tgnotesagent.feign.NoteFeignClientBotEdition;
import ru.bvt.tgnotesagent.rest.dto.AuthorDto;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthorDetailsServices implements UserDetailsService {
    private NoteFeignClientBotEdition noteFeignClientBotEdition;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<UserDetails> userDetailsList = new ArrayList<>();

        AuthorDto authorDto = noteFeignClientBotEdition.showAuthor(username);

        if (authorDto != null) {
            userDetailsList.add(User.withUsername(authorDto.getName()).password(authorDto.getPassword()).roles(authorDto.getRole()).build());
            return userDetailsList.get(0);
        }
        return null;
    }
}
