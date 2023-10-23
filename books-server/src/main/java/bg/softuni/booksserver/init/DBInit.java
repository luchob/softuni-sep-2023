package bg.softuni.booksserver.init;

import bg.softuni.booksserver.model.entity.AuthorEntity;
import bg.softuni.booksserver.model.entity.BookEntity;
import bg.softuni.booksserver.repository.AuthorRepository;
import bg.softuni.booksserver.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;


  DBInit(
      AuthorRepository authorRepository,
      BookRepository bookRepository) {

    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    if (bookRepository.count() == 0 && authorRepository.count() == 0) {
      initJovkov();
      initNikolaiHaitov();
      initDimitarTalev();
      initElinPelin();
      initVazov();
    }
  }

  private void initNikolaiHaitov() {
    initAuthor("Николай Хайтов",
        "Диви Разкази"
    );
  }

  private void initDimitarTalev() {
    initAuthor("Димитър Димов",
        "Тютюн"
    );
  }

  private void initElinPelin() {
    initAuthor("Елин Пелин",
        "Пижо и Пендо",
        "Ян Бибиян на луната",
        "Под манастирската лоза"
    );
  }

  private void initVazov() {
    initAuthor("Иван Вазов",
        "Пряпорец и Гусла",
        "Под Игото",
        "Тъгите на България"
    );
  }

  private void initJovkov() {

    initAuthor("Йордан Йовков",
        "Старопланински легенди",
        "Чифликът край границата");
  }

  private void initAuthor(String authorName, String... books) {
    AuthorEntity author = new AuthorEntity();
    author.setName(authorName);
    author = authorRepository.save(author);

    List<BookEntity> allBooks = new ArrayList<>();

    for (String book: books) {
      BookEntity aBook = new BookEntity();
      aBook.setAuthor(author);
      aBook.setTitle(book);
      aBook.setIsbn(UUID.randomUUID().toString());//random string, not real, dummy
      allBooks.add(aBook);
    }

    author.setBooks(allBooks);
    authorRepository.save(author);

    bookRepository.saveAll(allBooks);
  }
}