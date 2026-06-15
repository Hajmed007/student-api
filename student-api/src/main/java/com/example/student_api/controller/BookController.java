package com.example.student_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_api.entity.Book;

@RestController
@RequestMapping("/")
public class BookController {
    ArrayList<Book> list = new ArrayList<>();
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book)
    {
        list.add(book);
        return "Book added";
    }
    @GetMapping("/getbooks")
    public List<Book> getbook()
    {
        return list;
    }
    @GetMapping("/getbyId/{id}")
    public ResponseEntity<Book> getById(@PathVariable int  id)
    {
         return list.stream()
        .filter(b->b.getId()==id)
        .findFirst()
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
        
    }
    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<String> delBook(@PathVariable int id)
    {
        boolean res = list.removeIf(b->b.getId()==id);
        if(res)
        {
            return ResponseEntity.ok("Book " + id + " deleted");
        }
        else
        {
            return ResponseEntity.status(404).body("Book Not Found");
        }
    }
}
