package guru.springframework.sdjpaintro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class BookNatural {

    @Id
    private String title;

    @NonNull
    private String isbn;

    @NonNull
    private String publisher;

}
