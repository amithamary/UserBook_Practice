package com.UST.UserBook.controller;

import com.UST.UserBook.entity.Book;
import com.UST.UserBook.entity.User;
import com.UST.UserBook.exception.IdNotFound;
import com.UST.UserBook.repository.BookRepo;
import com.UST.UserBook.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;

    @PostMapping("/adduser")
    public ResponseEntity<User>createUser(@RequestBody User user){
        return ResponseEntity.ok().body(userRepo.save(user));
    }

    @PostMapping("/addbook")
    public ResponseEntity<Book>createBook(@RequestBody Book book){
        return ResponseEntity.ok().body(bookRepo.save(book));
    }
    @GetMapping("/getuser")
    public ResponseEntity <List<User>>getAllUser(){
        return ResponseEntity.ok().body(userRepo.findAll());
    }
    @GetMapping("/getuser/{id}")
    public ResponseEntity <User> getByUserId(@PathVariable Integer id) throws IdNotFound {
        Optional<User> users=userRepo.findById(id);
        if(users.isPresent()){
            return ResponseEntity.ok().body(userRepo.findById(id).orElse(null));
        }
        else{
            throw new IdNotFound("id not found");
        }
    }
    @GetMapping("/getbook")
    public ResponseEntity <List<Book>>getAllBook(){
        return ResponseEntity.ok().body(bookRepo.findAll());
    }
    @GetMapping("/getbook/{id}")
    public ResponseEntity <Book> getByBookId(@PathVariable Integer id){
        return ResponseEntity.ok().body(bookRepo.findById(id).orElse(null));
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity <String> deleteById(@PathVariable Integer id){
        userRepo.deleteById(id);
        return ResponseEntity.ok().body("deleted");
    }
    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity <String> deleteBookId(@PathVariable Integer id){
        bookRepo.deleteById(id);
        return ResponseEntity.ok().body("deleted");
    }
    @PutMapping("/putuser/{id}")
    public ResponseEntity<User> updateById(@RequestBody User user, @PathVariable Integer id){
        User old = null;
        Optional<User> users = userRepo.findById(user.getId());
        old = users.get();
        old.setId(id);     // (if condition is needed if there is exception)
        old.setUserName(user.getUserName());
        userRepo.save(old);
        return ResponseEntity.ok().body(old);
    }
    @PutMapping("/putbook/{id}")
    public ResponseEntity<Book> updateById(@RequestBody Book book, @PathVariable Integer id){
        Book old = null;
        Optional<Book> books = bookRepo.findById(book.getId());
        old = books.get();
        old.setId(id);
        old.setBookName(book.getBookName());
        bookRepo.save(old);
        return ResponseEntity.ok().body(old);

    }
}
