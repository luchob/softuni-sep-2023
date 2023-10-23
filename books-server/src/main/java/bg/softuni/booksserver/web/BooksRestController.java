package bg.softuni.booksserver.web;

import bg.softuni.booksserver.model.dto.BookDTO;
import bg.softuni.booksserver.service.BookService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BooksRestController {

  private final BookService bookService;

  public BooksRestController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> getAllBooks() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> findBookById(@PathVariable("id") Long id) {
    Optional<BookDTO> bookDTOOptional = bookService.findBookById(id);

    return bookDTOOptional
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BookDTO> deleteBookByID(@PathVariable("id") Long id) {

    bookService.deleteBookByID(id);

    return ResponseEntity
        .noContent()
        .build();
  }

  @PostMapping
  public ResponseEntity<BookDTO> createBook(
      @RequestBody BookDTO bookDTO,
      UriComponentsBuilder uriComponentsBuilder) {

    long newBookID = bookService.createBook(bookDTO);

    return ResponseEntity.created(
        uriComponentsBuilder.path("/api/books/{id}").build(newBookID)
    ).build();
  }

}
