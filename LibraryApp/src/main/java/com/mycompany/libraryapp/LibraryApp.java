/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.libraryapp;

/**
 *
 * @author ASUS
 */
import static spark.Spark.*;
import com.google.gson.Gson;

public class LibraryApp {
    public static void main(String[] args) {
        port(4567);
        Gson gson = new Gson();
        BookService bookService = new BookService();

        path("/api", () -> {
            get("/books", (req, res) -> {
            res.type("application/json");
            return bookService.getAll();
            }, gson::toJson);

            get("/books/:id", (req, res) -> {
                res.type("application/json");
                long id = Long.parseLong(req.params("id"));
                Book book = bookService.get(id);
                if (book == null) {
                res.status(404);
                return "Book not found";
                }
                return book;
            }, gson::toJson);
            post("/books", (req, res) -> {
                res.type("application/json");
                Book book = gson.fromJson(req.body(), Book.class);
                return bookService.add(book);
            }, gson::toJson);

            put("/books/:id", (req, res) -> {
                res.type("application/json");
                long id = Long.parseLong(req.params("id"));
                Book book = gson.fromJson(req.body(), Book.class);
                return bookService.update(id, book);
            }, gson::toJson);

            delete("/books/:id", (req, res) -> {
                long id = Long.parseLong(req.params("id"));
                if (bookService.delete(id)) {
                    res.status(204);
                    return "";
                } else {
                res.status(404);
                return "Book not found";
                }
            });
        });
       

        }
}