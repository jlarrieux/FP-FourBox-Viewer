package com.fp.fourBoxViewer.Boundaries;



import com.fp.fourBoxViewer.Entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



/**
 * Created by jlarrieux on 5/16/2017.
 */
public class HibernateManager implements Persistence {

    private SessionFactory sessionFactory;
    private Session session;


    private void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try{
            sessionFactory= new MetadataSources(registry).buildMetadata().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception ex){
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }


    public Session getSession(){
        setUp();
        return session;
    }



    @Override
    public void create(Item item) {
        setUp();
        session.save(item);
        exit();
    }



    @Override
    public void read() {
        setUp();


        exit();

    }



    @Override
    public void update() {
        setUp();



        exit();

    }



    @Override
    public void delete() {
        setUp();



        exit();

    }




    public void exit() {
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public static void main(String[] args){
        HibernateManager hibernateManager = new HibernateManager();
        Session session = hibernateManager.getSession();

        System.out.println(session.isConnected());
        hibernateManager.exit();
    }
}
