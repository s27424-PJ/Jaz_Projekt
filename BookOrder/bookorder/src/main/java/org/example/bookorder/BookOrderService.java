package org.example.bookorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookOrderService {
    private final BookOrderRepository bookOrderRepository;
    private final BookOrderMapper bookOrderMapper;

    @Autowired
    public BookOrderService(BookOrderRepository bookOrderRepository, BookOrderMapper bookOrderMapper) {
        this.bookOrderRepository = bookOrderRepository;
        this.bookOrderMapper = bookOrderMapper;
    }

    public ResponseEntity<List<BookOrderResponse>> getAllBookOrders() {
        List<BookOrder> foundBookOrders = bookOrderRepository.findAll();
        List<BookOrderResponse> responses = foundBookOrders.stream()
                .map(bookOrderMapper::mapEntityToResponse)
                .collect(Collectors.toList());
        if (responses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    public ResponseEntity<BookOrderResponse> getBookOrderById(UUID id) {
        BookOrder bookOrder = bookOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book order not found with id: " + id));
        BookOrderResponse response = bookOrderMapper.mapEntityToResponse(bookOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<BookOrderResponse> createBookOrder(BookOrderRequest createRequest) {
        BookOrder bookOrder = bookOrderMapper.mapToOrderBook(createRequest);
        BookOrder savedBookOrder = bookOrderRepository.save(bookOrder);
        BookOrderResponse response = bookOrderMapper.mapEntityToResponse(savedBookOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<BookOrderResponse> updateBookOrder(UUID id, BookOrderRequest updateRequest) {
        BookOrder bookOrder = bookOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book order not found with id: " + id));
        bookOrder = bookOrderMapper.update(updateRequest, bookOrder);
        BookOrderResponse response = bookOrderMapper.mapEntityToResponse(bookOrderRepository.save(bookOrder));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBookOrder(UUID id) {
        bookOrderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}