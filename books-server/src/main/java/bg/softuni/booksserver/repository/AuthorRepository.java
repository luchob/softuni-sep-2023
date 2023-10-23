package bg.softuni.booksserver.repository;


import bg.softuni.booksserver.model.entity.AuthorEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
  Optional<AuthorEntity> findByName(String name);
}