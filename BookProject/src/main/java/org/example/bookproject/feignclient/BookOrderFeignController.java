package org.example.bookproject.feignclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookOrderFeignController {
    private final BookOrderFeignClient bookOrderFeignClient;

    @Autowired
    public BookOrderFeignController(BookOrderFeignClient bookOrderFeignClient) {
        this.bookOrderFeignClient = bookOrderFeignClient;
    }

    @GetMapping("/test")
    public void getAllBookOrders() {
        System.out.println(bookOrderFeignClient.getAllBookOrders());
    }

    @PostMapping("/test")
    public BookOrder createBookOrder(@RequestBody BookOrderRequest request) {
        return bookOrderFeignClient.createBookOrder(request);
    }
}