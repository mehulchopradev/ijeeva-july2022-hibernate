package com.ijeeva;

import com.ijeeva.entities.Book;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

// Hibernate Query Language (HQL)
public class QueryBooksHql {

    static void queryAllBooks() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            String query = "from Book b";

            Query<Book> q = session.createQuery(query);
            List<Book> books = q.getResultList();

            System.out.println(books);
        }
    }

    static void queryAllBookTitles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            String query = "select b.title from Book b";
            Query<String> q = session.createQuery(query);
            List<String> bookTitles = q.getResultList();
            System.out.println(bookTitles);
        }
    }

    static void queryBookTitlePriceByPages(int pages) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            String q = "select b.title, b.price from Book b where b.pages > :pages";
            Query<Object[]> query = session
                    .createQuery(q)
                    .setParameter("pages", pages);
            List<Object[]> books = query.getResultList();
            books.forEach(book -> {
                System.out.println(book[0]);
                System.out.println(book[1]);
            });
        }
    }

    static void queryBookTitlesByPriceAndPages(float price, int pages) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            String q = "select b.title from Book b where b.pages > :pages " +
                    "and b.price > :price order by b.pages desc";
            Query<String> query = session.createQuery(q)
                    .setParameter("pages", pages)
                    .setParameter("price", price);
            List<String> titles = query.getResultList();
            System.out.println(titles);
        }
    }

    static void countBooksByTitleLike() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            String q = "select count(b) from Book b where lower(b.title) like '%prog%'";
            Query<Long> query = session.createQuery(q);
            System.out.println(query.getSingleResult());
        }
    }

    public static void main(String[] args) {
        // queryAllBooks();
        // queryAllBookTitles();
        // queryBookTitlePriceByPages(400);
        // queryBookTitlesByPriceAndPages(850, 400);
        countBooksByTitleLike();
    }
}
