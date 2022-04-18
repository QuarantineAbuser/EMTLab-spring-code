package mk.ukim.finki.booklibrary.model.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super(String.format("Book with id %d was not found!", id));
    }
}
