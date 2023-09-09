package guru.springframework.sdjpaintro.domain.composite;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="author_composite")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class AuthorEmbedded {

    @EmbeddedId
    @NonNull
    private NameId nameId;

    private String country;

}
