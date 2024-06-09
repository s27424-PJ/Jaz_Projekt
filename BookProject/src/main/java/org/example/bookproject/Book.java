package org.example.bookproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private UUID id;
    private String nazwa;
    private String gatunek;
    private double cena;
    private int iloscStron;
    private int licznikOdwiedzin;
    private int sztuki;
    // Dodane pole dla autora
    private UUID authorId;
}
