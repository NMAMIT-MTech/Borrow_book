package com.company;

import java.util.ArrayList;
import java.util.List;



public class Library implements LibraryOptions {

    private List<Book> availableList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Book> borrowList = new ArrayList<>();
    private List<Book> reserveList = new ArrayList<>();
    private int userBookCount = 0,maxBookCount=0;

    @Override
    public void add(Book b, User user) {
        if (b != null) {
            if (!b.getBorrowed()) {
                for (Book tempBook : availableList) {
                    if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                        return;
                    }
                }
                availableList.add(b);
            } else {
                for (Book tempBook : borrowList) {
                    if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                        return;
                    }
                }
                borrowList.add(b);
            }
            System.out.println("\nBook Name:" + b.getBookName() + "\nBook id:" + b.getBookId() + "\nadded");
        }
        if (user != null) {
            for (User tempUser : userList) {
                if (tempUser.getUserId() == user.getUserId()) {
                    return;
                }
            }
            userList.add(user);
        }

    }

    @Override
    public void remove(Book b, User user) {

        for (Book tempBook : availableList) {
            if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                availableList.remove(tempBook);
                System.out.println("\n" + b.getBookName() + " removed");
                return;
            }
        }
        System.out.println("\n" + b.getBookName() + " not removed");
    }

    @Override
    public void search(Book b) {
        for (Book tempBook : availableList) {
            if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                //index of returns object position,add one beacause position starts from zero
                System.out.println("Book found at " + (availableList.indexOf(tempBook) + 1));
                return;
            }
        }
        for (Book tempBook : borrowList) {
            if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                System.out.println("Book is already borrowed");
                return;
            }
        }
        System.out.println("\nBook not found");
    }

    @Override
    public void update(Book b, Book updateName) {
       /* for (int i = 0; i < availableList.size(); i++) {
            if (b.getBookName().equalsIgnoreCase(availableList.get(i).getBookName())) {
                System.out.println("\nBook updated in position" + i);
                availableList.set(i, updateName);
                return;
            }
        }*/
        for (Book tempBook : availableList) {
            if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                availableList.set(availableList.indexOf(tempBook), updateName);
                System.out.println("\nBook updated in position" + (availableList.indexOf(tempBook) + 1));
                return;
            }
        }

        System.out.println("\nBook not updated");
    }


    @Override
    public void display(User user) {

        System.out.println("\n***Available Book List***");
        for (Book tempBook : availableList) {
            System.out.println("\nBook Name:" + tempBook.getBookName() + "\nBook id:" + tempBook.getBookId() + "\n");

        }
        System.out.println("\n***Borrowed Book List***");
        for (Book tempBook : borrowList) {
            System.out.println("\nBook Name:" + tempBook.getBookName() + "\nBook id:" + tempBook.getBookId() + "\nuser id:" +user.getUserId());

        }
        System.out.println("\n***Reserved Book List***");
        for (Book tempBook : reserveList) {
            System.out.println("\nBook Name:" + tempBook.getBookName() + "\nBook id:" + tempBook.getBookId() + "\n");

        }
        System.out.println("\n***User Detail List***");
        for (User tempUser : userList) {
            System.out.println("\nUser id:" + tempUser.getUserId() + "\nUser Type:" + tempUser.getTypeofUser()
                    + "\nNo of books owned:" + tempUser.getNoOfBooks());
        }
    }


    @Override
    public void borrowBook(Book b, User user) {
        switch (user.getTypeofUser()) {
            case Constants.BASIC_USER:
                maxBookCount=Constants.BOOK_LIMIT_BASIC;
                break;
            case Constants.CLASSIC_USER:
                maxBookCount=Constants.BOOK_LIMIT_CLASSIC;
                break;
            case Constants.ELITE_USER:
                maxBookCount=Constants.BOOK_LIMIT_ELITE;
                break;
        }
        if(user.getNoOfBooks()<maxBookCount){
            for (Book tempBook : availableList) {
                if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                    borrowList.add(tempBook);
                    availableList.remove(tempBook);
                    userBookCount++;
                    System.out.println("\nBook given to user id: "+user.getUserId()+ "with book_id: " + tempBook.getBookId());
                    System.out.println("\nBook added to borrowList");
                    for (User tempUser:userList){
                        if(tempUser.getUserId()==user.getUserId()){
                            tempUser.setNoOfBooks(userBookCount);
                            userList.set(userList.indexOf(tempUser),tempUser);
                        }
                    }
                    return;
                }
            }
            System.out.println("\nBook not found in available list");
        }else {
            System.out.println("\nBook borrow limit reached");
        }

    }

    @Override
    public void returnBook(Book b, User user) {
        int bookNo = 0;

        for (Book tempBook : borrowList) {
            if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                bookNo = borrowList.indexOf(tempBook);
            }
        }
        //Add to reserve list else available list
        for (Book tempBook : reserveList) {
            if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                borrowList.set(bookNo,tempBook); //update if book is already present
                reserveList.remove(tempBook);
                System.out.println("\nBook added to borrowed list from reserved list");
                return;
            }
        }
        availableList.add(borrowList.get(bookNo));
        borrowList.remove(borrowList.get(bookNo));
        System.out.println("\nBook added to available list");
    }

    @Override
    public void reserveBook(Book b, User user) {
        for (Book tempBook : borrowList) {
            if (tempBook.getBookName().equalsIgnoreCase(b.getBookName())) {
                reserveList.add(tempBook);
                System.out.println("\nBook added to reserveList");
                return;
            }
        }
        System.out.println("\nBook not found");
    }
}
