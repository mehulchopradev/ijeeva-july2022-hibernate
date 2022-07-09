package com.ijeeva;

import com.ijeeva.entities.Book;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Scanner;

public class UpdateBookPrice {

    public static void main(String[] args) {
        long bookId;
        float price;
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the book id whose price you wanna update: ");
            bookId = scanner.nextLong();
            System.out.println("Enter the new price: ");
            price = scanner.nextFloat();
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, bookId);
            if(book != null) {
                Transaction transaction = session.beginTransaction();
                book.setPrice(price);
                transaction.commit();
            } else {
                System.out.println("Book not found to update");
            }
        }
    }
}
