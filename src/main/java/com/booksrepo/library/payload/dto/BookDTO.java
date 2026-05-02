package com.booksrepo.library.payload.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.booksrepo.library.model.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor	
@AllArgsConstructor
@Builder
public class BookDTO {

	    private Long id;
	    
	    @NotBlank(message = "ISBN is mandatory.")
	    private String isbn ;

	    @NotBlank(message = "Title is mandatory.")
	    @Size(min=1, max = 255, message = "Title length should be less than 255.")
	    private String title;    
	    
	    @NotBlank(message = "Author is mandatory.")
	    @Size(min=1, max = 255, message = "Author length should be less than 255.")
	    private String author;

	    @NotNull(message = "Genre is mandatory.")
	    private Genre genre;
	    
	    @Size(min=1, max = 255, message = "Publisher length should be less than 255.")
	    private String publisher;
	    
	     private LocalDateTime publicationDate;
	     
	     @Size(min=1, max = 20, message = "Language length should be less than 20.")
	     private String language;
	     
	     @Size(min=1, max = 50000, message = "Description length should be less than 50000.")
	     private Integer pages;
	     
	     @Size( max = 2000, message = "Description length should be less than 2000.")
	     private String description;
	     
	     @Min(value = 0, message = "Total copies cannot be negative.")
	     private Integer totalCopies;

	     @Min(value = 0, message = "Available copies cannot be negative.")
	     private Integer availableCopies;
	     
	     @Min(value = 0, message = "Price cannot be negative.")
	     @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount with up to 10 digits and 2 decimal places.")
	     private BigDecimal price;
	     
	     @Size(max = 500, message = "Cover image URL length should be less than 500.")
	     private String coverImageUrl;
	     
	     private Boolean alreadyHaveLoan;
	     
	     private Boolean alreadyHaveReservation;
	     
	     private Boolean active = true;
	     
	     private LocalDateTime createdAt;

	     private LocalDateTime updatedAt;
}
