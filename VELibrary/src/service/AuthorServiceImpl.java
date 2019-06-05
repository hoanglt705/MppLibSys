package service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import domain.Address;
import domain.Author;

public class AuthorServiceImpl implements IAuthorService{
    @Override
    public Author createNewAuthor(Author author, Address address) {
        DataAccess dataAccess = new DataAccessFacade();
        author.setAddress(address);
        return dataAccess.saveNewAuthor(author);
    }
}
