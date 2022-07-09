package com.ijeeva;

import com.ijeeva.entities.Book;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class BookId {

    public static void main(String[] args) {
        long bookId;
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the book id: ");
            bookId = scanner.nextLong();
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, bookId);
            if(book != null) {
                System.out.println(book);
            } else {
                System.out.println("Book not found");
            }
        }
    }
}
