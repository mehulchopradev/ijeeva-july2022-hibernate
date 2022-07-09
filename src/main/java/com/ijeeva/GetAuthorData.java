package com.ijeeva;

import com.ijeeva.entities.Author;
import com.ijeeva.entities.Book;
import com.ijeeva.entities.Location;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class GetAuthorData {

    public static void main(String[] args) {
        long authorId;
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the author id: ");
            authorId = scanner.nextLong();
        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Author author = session.get(Author.class, authorId);
            if(author != null) {
                System.out.println(author.getName());
                System.out.println(author.getGender());
                Location location = author.getLocation();
                if (location != null) {
                    System.out.println(location.getCountry());
                    System.out.println(location.getState());
                }
            } else {
                System.out.println("Author not found");
            }
        }
    }
}
