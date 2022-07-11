package com.ijeeva;

import com.ijeeva.entities.Author;
import com.ijeeva.entities.Book;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Set;

public class GetAuthorBooks {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Author author = session.get(Author.class, 1l);
            Set<Book> books = author.getBooks();
            books.forEach(book -> System.out.println(book));
        }
    }
}
