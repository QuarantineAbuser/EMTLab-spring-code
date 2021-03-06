package mk.ukim.finki.booklibrary.service;

import mk.ukim.finki.booklibrary.model.Book;
import mk.ukim.finki.booklibrary.model.dto.BookDto;
import mk.ukim.finki.booklibrary.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Long authorId, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    List<Category> findAllCategories();
}
