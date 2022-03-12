package repositories;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {
    private SessionFactorySingleton() {}

    private static class Holder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure("hibernateTest.cfg.xml")
                    .build();


            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Professor.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Student_Course.class)
                    .addAnnotatedClass(Term.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return Holder.INSTANCE;
    }
}
