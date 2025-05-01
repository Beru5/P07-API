/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryapp3;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class BookService {
    private final Map<Long, Book> bookMap = new HashMap<>();
    private long nextId = 1;
    
    public List <Book> getAll(){
        return new ArrayList<>(bookMap.values());
    }
    
    public Book get(long id){
        return bookMap.get(id);
    }
    
    public Book add(Book book){
        book.setId(nextId++);
        bookMap.put(book.getId(), book);
        return book;
    }
    
    public Book update(long id, Book book) {
        book.setId(id);
        bookMap.put(id, book);
        return book;
    }

    public boolean delete(long id) {
        return bookMap.remove(id) != null;
    }
}
