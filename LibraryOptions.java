package com.company;





public interface LibraryOptions {
    void add(Book b, User user);
    void remove(Book b,User user);
    void search(Book b);
    void update(Book b, Book updateName);
    void display(User user);
    void borrowBook(Book b,User user);
    void returnBook(Book b,User user);
    void reserveBook(Book b,User user);

}
