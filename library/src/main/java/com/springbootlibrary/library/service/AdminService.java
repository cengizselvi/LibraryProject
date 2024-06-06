package com.springbootlibrary.library.service;


import com.springbootlibrary.library.dao.BookRepository;
import com.springbootlibrary.library.dao.CheckoutRepository;
import com.springbootlibrary.library.dao.ReviewRepository;
import com.springbootlibrary.library.entity.Book;
import com.springbootlibrary.library.requestmodels.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;
    private CheckoutRepository checkoutRepository;

    @Autowired
    public AdminService(BookRepository bookRepository, ReviewRepository reviewRepository, CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.checkoutRepository = checkoutRepository;
    }



     public void postBook(AddBookRequest addBookRequest) {
            Book book = new Book();
            book.setTitle(addBookRequest.getTitle());
            book.setAuthor(addBookRequest.getAuthor());
            book.setDescription(addBookRequest.getDescription());
            book.setCopies(addBookRequest.getCopies());
            book.setCopiesAvailable(addBookRequest.getCopies());
            book.setImg(addBookRequest.getImg());
         bookRepository.save(book);
     }

     public void deleteBook(Long bookId) throws Exception{
         Optional<Book> book = bookRepository.findById(bookId);

         if (!book.isPresent()) {
             throw new Exception("Kitap bulunamadı");
         }
         bookRepository.delete(book.get());

     }

}
