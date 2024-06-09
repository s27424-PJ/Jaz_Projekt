package org.example.bookproject.feignclient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class BookOrder {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID bookId;
    private int quantity;
    // getters and setters
}