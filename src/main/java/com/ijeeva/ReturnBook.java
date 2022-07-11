package com.ijeeva;

import com.ijeeva.entities.BookStudent;
import com.ijeeva.entities.Student;
import com.ijeeva.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Set;

public class ReturnBook {

    public static void main(String[] args) {
        long bookId = 1l;
        long studentId = 2l;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Student student = session.get(Student.class, studentId);
            Set<BookStudent> issuedBooks = student.getBooksIssued();
            boolean isBookIssued = false;

            for (BookStudent bookStudent : issuedBooks) {
                if (bookStudent.getBook().getId() == bookId) {
                    isBookIssued = true;

                    if (bookStudent.getReturnDate() == null) {
                        Transaction transaction = session.beginTransaction();
                        bookStudent.setReturnDate(Calendar.getInstance().getTime());
                        transaction.commit();
                        System.out.println("Book returned successfully!");
                    } else {
                        System.out.println("Book already returned");
                    }

                    break;
                }
            }
            if (!isBookIssued) {
                System.out.println("Book not yet issued");
                return;
            }
        }
    }
}
