package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.BookNatural;
import guru.springframework.sdjpaintro.domain.composite.AuthorComposite;
import guru.springframework.sdjpaintro.domain.composite.AuthorEmbedded;
import guru.springframework.sdjpaintro.domain.composite.NameId;
import guru.springframework.sdjpaintro.repositories.AuthorCompositeRepository;
import guru.springframework.sdjpaintro.repositories.AuthorEmbeddedRepository;
import guru.springframework.sdjpaintro.repositories.BookNaturalRepository;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySqlIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void testMySql() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

    @Test
    public void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("Before Spring Boot");
        bookNatural.setIsbn("9876543210");
        bookNatural.setPublisher("The BrainyCode Crew");
        BookNatural savedBookNatural = bookNaturalRepository.save(bookNatural);

        BookNatural fetchedBookNatural = bookNaturalRepository.getReferenceById(savedBookNatural.getTitle());
        assertThat(fetchedBookNatural).isNotNull();
    }

    @Test
    public void authorCompositeTest() {
        NameId nameId = new NameId("Lorraine", "Figueroa");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setCountry("US");
        AuthorComposite savedAuthorComposite = authorCompositeRepository.save(authorComposite);

        AuthorComposite fetchedAuthorComposite = authorCompositeRepository.getReferenceById(nameId);
        assertThat(fetchedAuthorComposite).isEqualTo(savedAuthorComposite);
    }

    @Test
    public void authorEmbeddedTest() {
        NameId nameId = new NameId("Samantha", "Neill");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);

        AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();

        AuthorEmbedded fetched = authorEmbeddedRepository.getReferenceById(nameId);
        assertThat(fetched).isEqualTo(saved);

    }
}

