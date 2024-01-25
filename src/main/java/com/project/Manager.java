package com.project;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Manager {

    private static SessionFactory factory; 
    
    public static void createSessionFactory() {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }

    public static void close () {
        factory.close();
    }
  
    public static Biblioteca addBiblioteca(String nom, String ciutat) {
        Session session = factory.openSession();
        Transaction tx = null;
        Biblioteca result = null;
        try {
            tx = session.beginTransaction();
            result = new Biblioteca();
            result.setNom(nom);
            result.setCiutat(ciutat);
            session.save(result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }

    public static Llibre addLlibre(String nom, String editorial) {
        Session session = factory.openSession();
        Transaction tx = null;
        Llibre result = null;
        try {
            tx = session.beginTransaction();
            result = new Llibre();
            result.setNom(nom);
            result.setEditorial(editorial);
          
        
            session.save(result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }
    public static Persona addPersona(String dni, String nom, String telefon) {
        Session session = factory.openSession();
        Transaction tx = null;
        Persona result = null;
        try {
            tx = session.beginTransaction();
            result = new Persona();
            result.setDni(dni);
            result.setNom(nom);
            result.setTelefon(telefon);
            session.save(result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }
    
    public static Autor addAutor(String nom) {
        Session session = factory.openSession();
        Transaction tx = null;
        Autor result = null;
        try {
            tx = session.beginTransaction();
            result = new Autor();
            result.setNom(nom);

            session.save(result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }
    


    public static <T> T getById(Class<? extends T> clazz, long id){
        Session session = factory.openSession();
        Transaction tx = null;
        T obj = null;
        try {
            tx = session.beginTransaction();
            obj = clazz.cast(session.get(clazz, id)); 
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return obj;
    }
    
    public static void updateBiblioteca(long bibliotecaId, String nom, String ciutat) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Biblioteca biblioteca = (Biblioteca) session.get(Biblioteca.class, bibliotecaId);
            biblioteca.setNom(nom);
            biblioteca.setCiutat(ciutat);
          
            session.update(biblioteca);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateLlibre(long llibreId, String nom, String editorial) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Llibre llibre = (Llibre) session.get(Llibre.class, llibreId);
            llibre.setNom(nom);
            llibre.setEditorial(editorial);
     

            session.update(llibre);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updatePersona(long personaId, String dni, String nom, String telefon) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Persona persona = (Persona) session.get(Persona.class, personaId);
            persona.setDni(dni);
            persona.setNom(nom);
            persona.setTelefon(telefon);
            session.update(persona);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateAutor(long autorId, String nom) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Autor autor = (Autor) session.get(Autor.class, autorId);
            autor.setNom(nom);
            session.update(autor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

  
    public static <T> void delete(Class<? extends T> clazz, Serializable id){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            T obj = clazz.cast(session.get(clazz, id)); 
            session.delete(obj); 
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz) {
        return listCollection(clazz, "");
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz, String where){
        Session session = factory.openSession();
        Transaction tx = null;
        Collection<?> result = null;
        try {
            tx = session.beginTransaction();
            if (where.length() == 0) {
                result = session.createQuery("FROM " + clazz.getName()).list(); 
            } else {
                result = session.createQuery("FROM " + clazz.getName() + " WHERE " + where).list();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return result;
    }

    public static <T> String collectionToString(Class<? extends T> clazz, Collection<?> collection){
        String txt = "";
        for(Object obj: collection) {
            T cObj = clazz.cast(obj);
            txt += "\n" + cObj.toString();
        }
        if (txt.substring(0, 1).compareTo("\n") == 0) {
            txt = txt.substring(1);
        }
        return txt;
    }

    public static void queryUpdate (String queryString) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(queryString);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
    }

    public static List<Object[]> queryTable (String queryString) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Object[]> result = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(queryString);
            @SuppressWarnings("unchecked")
            List<Object[]> rows = query.list();
            result = rows;
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return result;
    }

    public static String tableToString (List<Object[]> rows) {
        String txt = "";
        for (Object[] row : rows) {
            for (Object cell : row) {
                txt += cell.toString() + ", ";
            }
            if (txt.length() >= 2 && txt.substring(txt.length() - 2).compareTo(", ") == 0) {
                txt = txt.substring(0, txt.length() - 2);
            }
            txt += "\n";
        }
        if (txt.length() >= 2) {
            txt =  txt.substring(0, txt.length() - 2);
        }
        return txt;
    }
}
