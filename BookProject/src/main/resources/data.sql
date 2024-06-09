CREATE TABLE IF NOT EXISTS AUTHOR (
                                      id UUID PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL
    );
INSERT INTO AUTHOR (id, name)
VALUES
    ('c9f9e8d1-12a5-4a26-aa51-75b5b01884f7', 'John Smith'),
    ('0b2c5a9e-342d-4b71-853f-6cb5e70eef2b', 'Alice Johnson');

CREATE TABLE IF NOT EXISTS BOOK (
                                    id UUID PRIMARY KEY,
                                    nazwa VARCHAR(255) NOT NULL,
    gatunek VARCHAR(255) NOT NULL,
    cena DOUBLE NOT NULL,
    iloscStron INT NOT NULL,
    licznikOdwiedzin INT NOT NULL,
    sztuki INT NOT NULL,
    author_id UUID,
    FOREIGN KEY (author_id) REFERENCES AUTHOR(id)
    );



INSERT INTO BOOK (ID, NAZWA, GATUNEK, CENA, ILOSC_STRON, LICZNIK_ODWIEDZIN, SZTUKI, AUTHOR_ID)
VALUES
    ('1e0a6db3-2f7b-4e93-8e98-5d7f8f8a1b7d', 'The Catcher in the Rye', 'Novel', 29.99, 277, 15, 120, 'c9f9e8d1-12a5-4a26-aa51-75b5b01884f7'),
    ('2f5a6c93-1d7a-4b1a-97e9-4d3f7e8a2a4d', 'To Kill a Mockingbird', 'Fiction', 24.99, 336, 10, 50, '0b2c5a9e-342d-4b71-853f-6cb5e70eef2b'),
    ('3d4a7e33-3b8c-4d2a-95f3-5d8f6a8a3b2d', '1984', 'Dystopian', 19.99, 328, 20, 75, 'c9f9e8d1-12a5-4a26-aa51-75b5b01884f7'),
    ('4a5b6f73-4e9d-4f3a-98f7-6e9f7e9a4c3d', 'Pride and Prejudice', 'Romance', 22.99, 279, 25, 60, '0b2c5a9e-342d-4b71-853f-6cb5e70eef2b');
