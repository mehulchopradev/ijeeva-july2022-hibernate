package com.ijeeva;

import com.ijeeva.entities.Book;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Scanner;

public class DeleteBook {

    public static void main(String[] args) {
        long bookId;
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the book id you want to delete: ");
            bookId = scanner.nextLong();
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, bookId);
            if(book != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(book);
                transaction.commit();
            } else {
                System.out.println("Book not found to delete");
            }
        }
    }
}
