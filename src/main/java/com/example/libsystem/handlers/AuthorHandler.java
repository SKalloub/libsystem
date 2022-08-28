package com.example.libsystem.handlers;

import com.example.libsystem.exceptions.AuthorNotFoundException;
import com.example.libsystem.exceptions.UnAuthorizedMoveException;
import com.example.libsystem.exceptions.WrongUserNameOrPasswordException;
import com.example.libsystem.model.Author;
import com.example.libsystem.model.Role;
import com.example.libsystem.model.User;
import com.example.libsystem.payload.AuthorDTO;
//import com.example.libsystem.repositories.UserRepository;
import com.example.libsystem.repositories.UserRepository;
import com.example.libsystem.services.AuthorService;
import com.example.libsystem.services.securityServices.JWTService;
import com.example.libsystem.utils.AuthorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorHandler {
    private static final Logger logger = LogManager.getLogger(AuthorHandler.class);

    @Autowired
    private AuthorService authorService;
    //
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;

    public List<AuthorDTO> getAllAuthors() {
        logger.info("Getting All Authors");
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorService.getAllAuthors().stream().forEach(author -> authorDTOS.add(AuthorUtil.toDto(author)));
        return authorDTOS;
    }

    public AuthorDTO getAuthorById(int id) {
        return AuthorUtil.toDto(authorService.findAuthorById(id).orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    public boolean addAuthor(AuthorDTO authorDTO, HttpHeaders headers) {
        if (!authorDTO.getName().isEmpty()) {
            String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
            String username = jwtService.extractUserName(token.substring(7));
            User user = userRepository.findByUsername(username).orElseThrow(() -> new WrongUserNameOrPasswordException());
            if (user.getRole() != Role.ADMIN) {
                throw new UnAuthorizedMoveException();
            } else {
                Author author = AuthorUtil.fromDto(authorDTO);
                authorService.addAuthor(author);
                return true;
            }
        }

        return false;
    }

    public boolean deleteAuthorById(int id) {
        authorService.findAuthorById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        authorService.deleteAuthorById(id);
        return true;
    }

    public boolean updateAuthor(AuthorDTO authorDTO, int id) {
        Author author = authorService.findAuthorById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        if (authorDTO.getName() != null && !authorDTO.getName().isEmpty())
            author.setName(authorDTO.getName());


        authorService.updateAuthor(author);
        return true;
    }
}
