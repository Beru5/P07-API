/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryapp2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookForm extends JFrame {
    private JTable table;
    private BookService bookService;

    public BookForm() throws MalformedURLException, IOException {
        setTitle("Book Manager GUI");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        URL url = new URL("http://localhost:4567/api/books");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String json = in.lines().collect(Collectors.joining());
        List<Book> books = new Gson().fromJson(json, new TypeToken<List<Book>>() {}.getType());

        String[] columnNames = {"ID", "Title", "Author"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Book book : books) {
            Object[] row = {book.getId(), book.getTitle(), book.getAuthor()};
            model.addRow(row);
        }

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookForm gui;
            try {
                gui = new BookForm();
                gui.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
