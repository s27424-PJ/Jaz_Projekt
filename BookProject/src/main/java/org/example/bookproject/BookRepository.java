package org.example.bookproject;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
