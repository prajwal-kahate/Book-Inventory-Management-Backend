package com.example.util;

import org.springframework.stereotype.Component;

import com.example.dto.AuthorDto;
import com.example.entity.Author;

@Component
public class AuthorDTOMapper {

	public Author mapToEntity(AuthorDto authorDto) {
		Author newAuthor = new Author();
		newAuthor.setAuthorID(authorDto.getAuthorID());
		newAuthor.setLastName(authorDto.getLastName());
		newAuthor.setFirstName(authorDto.getFirstName());
		newAuthor.setPhoto(authorDto.getPhoto());

		return newAuthor;
	}

	public AuthorDto mapToDTO(Author author) {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setAuthorID(author.getAuthorID());
		authorDto.setLastName(author.getLastName());
		authorDto.setFirstName(author.getFirstName());
		authorDto.setPhoto(author.getPhoto());

		return authorDto;
	}
}
