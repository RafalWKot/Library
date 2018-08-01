package com.crud.library;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookLoan;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.User;
import com.crud.library.repository.BookLoanRepository;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@Transactional
//@TransactionConfiguration(defaultRollback = false) //żeby wyłączyć ROLLBACK i utrwaliść dane w bazie
@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookLoanRepository bookLoanRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSaveBook() {
        //Given
        Book book1 = new Book(null,"Pan Tadeusz","Adam Mickiewicz", Date.from(LocalDate.of(2010,12,1).atStartOfDay().toInstant(ZoneOffset.UTC)));
        Book book2 = new Book(null,"Zemsta","Aleksander Fredro", Date.from(LocalDate.of(2011,2,10).atStartOfDay().toInstant(ZoneOffset.UTC)));

        //When
        bookRepository.save(book1);
        bookRepository.save(book2);

        //Then
        Assert.assertEquals(2, bookRepository.count());
        Assert.assertEquals("Adam Mickiewicz", bookRepository.findOne(book1.getId()).getAuthor());
        Assert.assertEquals("Zemsta", bookRepository.findOne(book2.getId()).getTitle());

    }


    @Test
    public void testSaveBookCopy() {
        //Given
        Book book1 = new Book(null, "Pan Tadeusz","Adam Mickiewicz", Date.from(LocalDate.of(2010,12,1).atStartOfDay().toInstant(ZoneOffset.UTC)));

        BookCopy bookCopy1 = new BookCopy(null,  book1, "wolna",null);
        BookCopy bookCopy2 = new BookCopy(null, book1, "wypożyczona",null
        );

        //When
        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);

        //Then
        Assert.assertEquals(1, bookRepository.count());
        Assert.assertEquals(2, bookCopyRepository.count());
    }

    @Test
    public void testSaveUser() {
        //Given
        User user = new User(null, "Rafal", "Kot", Date.from(LocalDate.of(2018,7,31).atStartOfDay().toInstant(ZoneOffset.UTC)),null);

        //When
        userRepository.save(user);

        //Then
        Assert.assertEquals(1, userRepository.count());
    }

    @Test
    public void testSaveBookBorrowed() {
        //Given
        User user = new User("Rafal", "Kot", Date.from(LocalDate.of(2018,7,31).atStartOfDay().toInstant(ZoneOffset.UTC)));
        Book book1 = new Book(null, "Pan Tadeusz","Adam Mickiewicz", Date.from(LocalDate.of(2010,12,1).atStartOfDay().toInstant(ZoneOffset.UTC)));
        BookCopy bookCopy1 = new BookCopy(  book1, "wolna");

        BookLoan bookLoan = new BookLoan(null, bookCopy1, user);
        user.getBookLoans().add(bookLoan);
        bookCopy1.getBookLoans().add(bookLoan);

        //When
        bookLoanRepository.save(bookLoan);

        //Then

    }
}
