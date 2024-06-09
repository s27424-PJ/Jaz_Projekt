package org.example.bookorder;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookOrderResponse {
    private UUID id;
    private UUID bookId;
    private int quantity;
}