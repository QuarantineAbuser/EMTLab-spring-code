package mk.ukim.finki.booklibrary.web;

import mk.ukim.finki.booklibrary.model.Country;
import mk.ukim.finki.booklibrary.model.dto.CountryDto;
import mk.ukim.finki.booklibrary.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://library-react-195091.herokuapp.com")
@RequestMapping("/api/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    private List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Country> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    private ResponseEntity<Country> save(@RequestBody CountryDto countryDto) {
        return countryService.save(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity<Country> edit(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        return countryService.edit(id, countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //ResponseEntity<Book>
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
        if (countryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
