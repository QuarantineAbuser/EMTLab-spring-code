package mk.ukim.finki.booklibrary.web;

import mk.ukim.finki.booklibrary.model.Author;
import mk.ukim.finki.booklibrary.model.dto.AuthorDto;
import mk.ukim.finki.booklibrary.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Author> findById(@PathVariable Long id) {
        return authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    private ResponseEntity<Author> save(@RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity<Author> edit(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return authorService.edit(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //ResponseEntity<Author>
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        if (authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
