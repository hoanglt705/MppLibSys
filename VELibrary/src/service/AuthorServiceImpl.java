package service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Address;
import domain.Author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements IAuthorService{
    @Override
    public Author createNewAuthor(Author author, Address address) {
        DataAccess dataAccess = new DataAccessFacade();
        author.setAddress(address);
        return dataAccess.saveNewAuthor(author);
    }


    @Override
    public List<Author> getAllAuthors() {
        DataAccess dataAccess = new DataAccessFacade();
        HashMap<String, Author> maps = dataAccess.readAuthorsMap();
        return maps.values().stream().collect(Collectors.toList());
    }
}
