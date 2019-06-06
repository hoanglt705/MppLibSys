package service;

import domain.Address;
import domain.Author;

import java.util.List;

/**
 * Created by haupham on 6/5/19.
 */
public interface IAuthorService {
    public Author createNewAuthor(Author author, Address address);
    public List<Author> getAllAuthors();
}