package untils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理器工厂浪费资源和耗时问题通过静态代码块的形式，
 * 当程序第一次访问此工具类时，创建一个公共的实体管理器工厂对象
 */
public class JpaUntils {
    private static EntityManagerFactory factory;
    static{
       factory= Persistence.createEntityManagerFactory("myJpa");
    }
    public static EntityManager getEntityManger(){
        return factory.createEntityManager();
    }
}
