package payload.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class GenreDTO {
    private Long id;

    @NotBlank(message = "Genre code is mandatory.")
    private String code;

    @NotBlank(message = "Genre name is mandatory.")
    private String name;

    @Size(max = 500, message = "Description length should be less than 500.")
    private String description;

    @Min(value = 0, message = "Display order cannot be negative.")
    @Builder.Default()
    private Integer displayOrder = 0;

    @Column(nullable = false)
    @Builder.Default()
    private Boolean active = true;
    
    private long parentGenreId;
    
    private String parentGenreName;
    
    private List<GenreDTO> subGenre;
    
    private Long bookCountLong;
    
    private LocalDateTime  createdAt;
    private LocalDateTime  updatedAt;
    
    
}
