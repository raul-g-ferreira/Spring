package com.kaizen.library.service;

import com.kaizen.library.dto.GoogleBookDTO;
import com.kaizen.library.exception.BookNotFoundOrUnavailableException;
import com.kaizen.library.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GoogleBookService {

    @Value("${google.books.api-key}")
    private String apiKey;
    private final WebClient webClient;

    public GoogleBookService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://www.googleapis.com/books/v1").build();
    }

    public Book createBookByIsbn(String isbn) {
        GoogleBookDTO response = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/volumes")
                        .queryParam("q", "isbn:" + isbn)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GoogleBookDTO.class)
                .block(); // Aguarda a resposta para prosseguir

        if (response == null || response.items() == null || response.items().isEmpty()) {
            throw new BookNotFoundOrUnavailableException("Livro não encontrado no Google Books");
        }

        // 2. Extrai as informações do livro
        GoogleBookDTO.Item item = response.items().getFirst();
        String title = item.volumeInfo().title();
        String author = item.volumeInfo().authors() != null && !item.volumeInfo().authors().isEmpty() ? item.volumeInfo().authors().getFirst() : "Autor Desconecido";
        String category = item.volumeInfo().mainCategory() != null ? item.volumeInfo().mainCategory() : "Categoria Desconecida";

        return new Book(title, author, category);
    }

}

