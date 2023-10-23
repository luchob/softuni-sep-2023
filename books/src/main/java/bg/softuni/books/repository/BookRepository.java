package bg.softuni.books.repository;

import bg.softuni.books.model.AuthorEntity;
import bg.softuni.books.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
