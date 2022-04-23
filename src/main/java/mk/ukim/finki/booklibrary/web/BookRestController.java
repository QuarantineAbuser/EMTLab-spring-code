package mk.ukim.finki.booklibrary.web;

import mk.ukim.finki.booklibrary.model.Book;
import mk.ukim.finki.booklibrary.model.dto.BookDto;
import mk.ukim.finki.booklibrary.model.enumerations.Category;
import mk.ukim.finki.booklibrary.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private List<Book> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/categories")
    private List<Category> findAllCategories(){
        return bookService.findAllCategories();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Book> findById(@PathVariable Long id){
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    private ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto){
        return bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //ResponseEntity<Book>
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteById(@PathVariable Long id){
        bookService.deleteById(id);
        if(bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
