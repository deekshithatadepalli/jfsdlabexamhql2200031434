
package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert Records
        insertRecords(session);

        // Print All Records
        printAllRecords(session);

        session.close();
        sessionFactory.close();
    }

    public static void insertRecords(Session session) {
        Transaction transaction = session.beginTransaction();

        Client client1 = new Client();
        client1.setName("Alice");
        client1.setGender("Female");
        client1.setAge(25);
        client1.setLocation("New York");
        client1.setEmail("alice@example.com");
        client1.setMobileNumber("1234567890");

        Client client2 = new Client();
        client2.setName("Bob");
        client2.setGender("Male");
        client2.setAge(30);
        client2.setLocation("San Francisco");
        client2.setEmail("bob@example.com");
        client2.setMobileNumber("9876543210");

        session.save(client1);
        session.save(client2);

        transaction.commit();
        System.out.println("Records inserted successfully.");
    }

    public static void printAllRecords(Session session) {
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        for (Client client : clients) {
            System.out.println("ID: " + client.getId());
            System.out.println("Name: " + client.getName());
            System.out.println("Gender: " + client.getGender());
            System.out.println("Age: " + client.getAge());
            System.out.println("Location: " + client.getLocation());
            System.out.println("Email: " + client.getEmail());
            System.out.println("Mobile Number: " + client.getMobileNumber());
            System.out.println("----------------------------");
        }
    }
}
