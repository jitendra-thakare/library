package com.booksrepo.library.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchRequest {
	private String searchTerm;
	private Long genreId;
	private Boolean availableOnly;
	private int page = 0;
	private int size = 20;
	private String sortBy = "createdAt";
	private String sortDir = "desc";
}
