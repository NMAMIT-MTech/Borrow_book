package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        int userOption = 0,membershipOption, userid, bookId;
        String bookName;
        Scanner sc;
        User loggedUser;
        LibraryOptions bookOptions;
        Book book;

        System.out.println("\n***Book Library***\n");
        bookOptions = new Library();

        sc = new Scanner(System.in);

        for (int bookNo = 1; bookNo < 10; bookNo++) {

            book = new Book();
            book.setBookName("Book1" + bookNo);
            book.setBookId(10 + bookNo);
            book.setBorrowed(false);
            book.setUserID(0); //book not assigned to loggedUser
            bookOptions.add(book, null);

            User tempUser = new User();
            tempUser.setTypeofUser(Constants.BASIC_USER);
            tempUser.setUserId(30 + bookNo);
            tempUser.setNoOfBooks(1);

            book = new Book();
            book.setBookName("Book2" + bookNo);
            book.setBookId(20 + bookNo);
            book.setBorrowed(true);
            book.setUserID(30 + bookNo);
            bookOptions.add(book, tempUser);

        }
        System.out.println("\n***Enter your User id***\n");
        userid = sc.nextInt();

        loggedUser = new User();
        loggedUser.setUserId(userid);

        System.out.println("\n***Membership types***\n1.Basic\n2.Classic\n3.Elite\n***Enter your membership type***\n");
        membershipOption=sc.nextInt();
        switch (membershipOption) {
            case Constants.BASIC_USER:
                loggedUser.setTypeofUser(Constants.BASIC_USER);
                break;
            case Constants.CLASSIC_USER:
                loggedUser.setTypeofUser(Constants.CLASSIC_USER);
                break;
            case Constants.ELITE_USER:
                loggedUser.setTypeofUser(Constants.ELITE_USER);
                break;
            default :
                loggedUser.setTypeofUser(Constants.BASIC_USER);
                break;
        }
        bookOptions.add(null, loggedUser); //add loggedUser

        while (userOption != 9) {
            System.out.println("\n***Book book options***\n1.Add book\n2.remove book\n3.Update book\n4.Search book\n5.Display books\n" +
                    "6.BorrowBook\n7.Return book\n8.Reserve book\n9.Exit");
            System.out.println("\n***Enter your Option***\n");
            userOption = sc.nextInt();

            switch (userOption) {
                case 1:
                    System.out.println("***Enter book name to add***\n");
                    bookName = sc.next();

                    System.out.println("***Enter book id to add***\n");
                    bookId = sc.nextInt();

                    book = new Book();
                    book.setBookName(bookName);
                    book.setBookId(bookId);
                    book.setBorrowed(false);
                    bookOptions.add(book, null);
                    break;
                case 2:
                    System.out.println("***Enter book name to remove***\n");
                    bookName = sc.next();

                    book = new Book();
                    book.setBookName(bookName);
                    book.setBorrowed(false);
                    bookOptions.remove(book, null);
                    break;
                case 3:
                    System.out.println("***Enter the old book name to update***\n");
                    String oldName = sc.next();
                    Book oldBook = new Book();
                    oldBook.setBookName(oldName);

                    System.out.println("***Enter new book name to update***\n");
                    bookName = sc.next();

                    System.out.println("***Enter new book id to update***\n");
                    bookId = sc.nextInt();

                    book = new Book();
                    book.setBookName(bookName);
                    book.setBookId(bookId);
                    book.setBorrowed(false);

                    bookOptions.update(oldBook, book);
                    break;
                case 4:
                    System.out.println("***Enter book name to search***\n");
                    bookName = sc.next();
                    book = new Book();
                    book.setBookName(bookName);
                    bookOptions.search(book);
                    break;
                case 5:
                    bookOptions.display(loggedUser);
                    break;
                case 6:
                    System.out.println("***Enter book name to borrow***\n");
                    bookName = sc.next();

                    book = new Book();
                    book.setBookName(bookName);
                    book.setBorrowed(true);
                    book.setUserID(loggedUser.getUserId());
                    bookOptions.borrowBook(book, loggedUser);//Add the loggedUser here.Logged in loggedUser
                    break;
                case 7:
                    System.out.println("***Enter book name to return***\n");
                    bookName = sc.next();

                    book = new Book();
                    book.setBookName(bookName);
                    book.setBorrowed(true);
                    book.setUserID(loggedUser.getUserId());

                    bookOptions.returnBook(book, loggedUser);
                    break;
                case 8:
                    System.out.println("***Enter book name to reserve***\n");
                    bookName = sc.next();

                    book = new Book();
                    book.setBookName(bookName);
                    book.setBorrowed(false);
                    book.setUserID(loggedUser.getUserId());

                    bookOptions.reserveBook(book, loggedUser);
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        }
    }
}
