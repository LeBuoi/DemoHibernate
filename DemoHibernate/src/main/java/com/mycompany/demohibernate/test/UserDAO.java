/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demohibernate.test;

import com.mycompany.demohibernate.entities.User;
import com.mycompany.demohibernate.utils.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class UserDAO {
    public void saveUser(User user) {
        Transaction transaction = null;
        try  {
            
                    Session session = Hibernate.getSessionFactory().openSession();
                    // start a transaction
                    transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public boolean checkLogin(String username, String password) {

        Transaction transaction = null;
        User user = null;
        try  {
            Session session = Hibernate.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
                .uniqueResult();
            System.out.println("User Detail: "+user.getFirstName());
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        String username = "";
        String password = "";
        UserDAO dao = new UserDAO();
        System.out.println(dao.checkLogin("kien", "123456"));
    }
}
