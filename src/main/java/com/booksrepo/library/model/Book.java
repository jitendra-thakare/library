package com.booksrepo.library.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn ;

    @Column(nullable = false)
    private String title;    
    
    @Column(nullable = false)
    private String author;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Genre genre;
    
    private String publication;
    
     private LocalDateTime publishedDate;
     
     private String language;
     
     private Integer pages;
     
     private String description;
     
     @Column(nullable = false)
     @Min(value = 0, message = "Total copies cannot be negative.")
     private Integer totalCopies;

     @Column(nullable = false)
     private Integer availableCopies;
     
     private BigDecimal price;
     
     private String coverImageUrl;
     
     @Column(nullable = false)
     @Builder.Default()
     private Boolean active = true;
     
     @CreationTimestamp
     private LocalDateTime createdAt;

     @UpdateTimestamp
     private LocalDateTime updatedAt;
     
     @AssertTrue(message = "Available copies cannot exceed total copies.")
     private boolean isAvailableCopiesValid() {
		return availableCopies != null || totalCopies != null && availableCopies <= totalCopies;
     }
     
}