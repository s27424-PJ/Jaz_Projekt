package org.example.bookproject;

import io.swagger.client.model.BookRequest;
import io.swagger.client.model.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> foundBooks = bookRepository.findAll().stream()
                .map(bookMapper::mapEntityToResponse)
                .peek(bookResponse -> {
                    Author author = authorRepository.findById(bookResponse.getAuthorId())
                            .orElseThrow(() -> new ResourceNotFoundException("Author not found for book with id: " + bookResponse.getId()));
                    bookResponse.setAuthorName(author.getName());
                })
                .collect(Collectors.toList());

        if (foundBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(foundBooks, HttpStatus.OK);
    }


    public ResponseEntity<BookResponse> getBookById(UUID id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        book.setLicznikOdwiedzin(book.getLicznikOdwiedzin() + 1); // dodaj ten wiersz
        bookRepository.save(book); // i ten wiersz

        BookResponse bookResponse = bookMapper.mapEntityToResponse(book);
        Author author = authorRepository.findById(book.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Author not found for book with id: " + id));
        bookResponse.setAuthorName(author.getName());

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
    public ResponseEntity<BookResponse> saveBook(BookRequest createRequest) {
        Book book = bookMapper.mapToBook(createRequest);
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(bookMapper.mapEntityToResponse(savedBook), HttpStatus.OK);
    }

    public ResponseEntity<BookResponse> updateBook(UUID id, BookRequest updateRequest) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        Book updatedBook = bookMapper.update(updateRequest, book);
        return new ResponseEntity<>(bookMapper.mapEntityToResponse(bookRepository.save(updatedBook)), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBook(UUID id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
