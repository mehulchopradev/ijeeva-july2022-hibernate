package com.ijeeva;

import com.ijeeva.entities.Book;
import com.ijeeva.entities.BookStudent;
import com.ijeeva.entities.Student;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Calendar;

public class IssueBooks {

    public static void main(String[] args) {
        long bookId = 1l;
        long studentId = 1l;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            Student student = session.get(Student.class, studentId);

            BookStudent bookStudent = new BookStudent(book, student, Calendar.getInstance().getTime());
            session.save(bookStudent);

            transaction.commit();
        }
    }
}
