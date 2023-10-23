package bg.softuni.booksserver.service;

import bg.softuni.booksserver.model.dto.BookDTO;
import java.util.List;
import java.util.Optional;

public interface BookService {

  List<BookDTO> getAllBooks();

  Optional<BookDTO> findBookById(Long id);

  void deleteBookByID(Long id);

  Long createBook(BookDTO bookDTO);
}
