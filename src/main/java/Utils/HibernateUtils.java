package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static SessionFactory getSessionFactory(){
        // 1 đọc cấu hình
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        //2  build session factory
        return   configuration .buildSessionFactory();
    }
}
