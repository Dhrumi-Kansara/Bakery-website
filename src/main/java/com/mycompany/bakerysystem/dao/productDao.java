 
package com.mycompany.bakerysystem.dao;
 
import com.mycompany.bakerysystem.entities.Product;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;
import org.hibernate.query.Query;

public class productDao {
    private SessionFactory factory;

    public productDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    public boolean saveProduct(Product product) {
        boolean f=false;
        try {
            Session session = this.factory.openSession();
            Transaction tx=session.beginTransaction();
            
            session.save(product);
            
            tx.commit();
            session.close();
            f=true;
        } catch (Exception e) {
            e.printStackTrace();
            f=false;
        }
        return f;
    }
    
    //get all products
 
    public  List<Product> getAllProduct() {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Product");
        List<Product> list=query.list();
        return list;
    }
    
    //get all products by category
   
    public  List<Product> getAllProductById(int cId) {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Product as p where p.category.categoryId =: id");
        query.setParameter("id", cId);
        List<Product> list=query.list();
        return list;
    }
}
