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

// Criteria API of hibernate
public class QueryingBooks {

    static void queryAllBooks() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);

            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root);

            Query<Book> query = session.createQuery(criteriaQuery);
            List<Book> books =  query.getResultList();
            System.out.println(books);
        }
    }

    static void queryAllBookTitles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = cb.createQuery(String.class);

            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root.get("title"));

            Query<String> query = session.createQuery(criteriaQuery);
            List<String> bookTitles =  query.getResultList();
            System.out.println(bookTitles);
        }
    }

    static void queryAllBookTitlePrice() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);

            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.multiselect(root.get("title"), root.get("price"));

            Query<Object[]> query = session.createQuery(criteriaQuery);
            List<Object[]> bookTitles =  query.getResultList();
            bookTitles.forEach(element -> {
                System.out.println(element[0]);
                System.out.println(element[1]);
            });
        }
    }

    static void queryBookTitlePriceByPages() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);

            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery
                    .multiselect(root.get("title"), root.get("price"))
                    .where(cb.greaterThan(root.get("pages"), 400));

            Query<Object[]> query = session.createQuery(criteriaQuery);
            List<Object[]> bookTitles =  query.getResultList();
            bookTitles.forEach(element -> {
                System.out.println(element[0]);
                System.out.println(element[1]);
            });
        }
    }

    static void countBooksByPrice() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);

            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery
                    .select(cb.count(root.get("title")))
                    .where(cb.greaterThanOrEqualTo(root.get("price"), 900));

            Query<Long> query = session.createQuery(criteriaQuery);
            Long count =  query.getSingleResult();
            System.out.println(count);
        }
    }

    static void countBooksByTitleLike() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);

            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery
                    .select(cb.count(root.get("title")))
                    .where(
                            cb.like(
                                cb.lower(root.get("title")), "%prog%"
                            )
                    );

            Query<Long> query = session.createQuery(criteriaQuery);
            Long count =  query.getSingleResult();
            System.out.println(count);
        }
    }

    static void queryBookTitlesByPriceAndPages() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = cb.createQuery(String.class);

            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery
                    .select(root.get("title"))
                    .where(
                            cb.and(
                                    cb.greaterThan(root.get("pages"), 400),
                                    cb.greaterThan(root.get("price"), 850)
                            )
                    )
                    .orderBy(
                            cb.desc(root.get("pages"))
                    );

            Query<String> query = session.createQuery(criteriaQuery);
            List<String> bookTitles =  query.getResultList();
            System.out.println(bookTitles);
        }
    }

    public static void main(String[] args) {
        // queryAllBooks();
        // queryAllBookTitles();
        // queryAllBookTitlePrice();
        // queryBookTitlePriceByPages();
        // countBooksByPrice();
        // countBooksByTitleLike();
        queryBookTitlesByPriceAndPages();
    }
}
