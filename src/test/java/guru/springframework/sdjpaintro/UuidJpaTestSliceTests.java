package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.repositories.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repositories.BookUuidRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UuidJpaTestSliceTests {

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Test
    public void testAuthorUuidInitializedOK() {
        List<AuthorUuid> authorUuidList = authorUuidRepository.findAll();
        assertTrue(authorUuidList.size() == 1);
    }


    @Test
    public void testAuthorUuidGetAuthorUuidRecord() {
        List<AuthorUuid> authorUuidList = authorUuidRepository.findAll();
        AuthorUuid exampleAuthorUuid  = authorUuidList.get(0);
        // Now find it by a search
        Optional<AuthorUuid> optSearchForAuthorUuid = authorUuidRepository.findById(exampleAuthorUuid.getId());
        assertTrue(optSearchForAuthorUuid.isPresent());
        assertTrue(optSearchForAuthorUuid.get().getId() == exampleAuthorUuid.getId());
    }

    @Test
    public void testAuthorDataSavedOnNewEntity() {
        AuthorUuid newAuthor = new AuthorUuid();
        newAuthor.setLastName("Figueroa");
        newAuthor.setFirstName("Lorraine");
        AuthorUuid savedAuthor = authorUuidRepository.save(newAuthor);
        assertTrue(savedAuthor.getLastName().equals(newAuthor.getLastName()));
        List<AuthorUuid> authorUuidList = authorUuidRepository.findAll();
        assertTrue(authorUuidList.size() == 2);
    }

    @Test
    public void testBookUuidInitializedOK() {
        List<BookUuid> bookUuidList = bookUuidRepository.findAll();
        assertTrue(bookUuidList.size() == 1);
    }


    @Test
    public void testBookUuidGetBookUuidRecord() {
        List<BookUuid> bookUuidList = bookUuidRepository.findAll();
        BookUuid exampleBookUuid  = bookUuidList.get(0);

        Optional<BookUuid> optSearchForBookUuid = bookUuidRepository.findById(exampleBookUuid.getId());
        assertTrue(optSearchForBookUuid.isPresent());
        assertTrue(optSearchForBookUuid.get().getId() == exampleBookUuid.getId());
    }

    @Test
    public void testBookDataSavedOnNewEntity() {
        BookUuid newBook = new BookUuid();
        newBook.setTitle("The Ultimate Book on Atari 2600 Programming");
        newBook.setIsbn("9876543210");
        newBook.setPublisher("Self Published");
        BookUuid savedBook = bookUuidRepository.save(newBook);
        assertTrue(savedBook.getTitle().equals(newBook.getTitle()));
        List<BookUuid> authorUuidList = bookUuidRepository.findAll();
        assertTrue(authorUuidList.size() == 2);
    }
}
