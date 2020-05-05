package demoTest;

import entity.Customer;
import org.junit.Test;
import untils.JpaUntils;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaTest {

    @Test
    public void Update(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
        Customer customer = entityManger.getReference(Customer.class,2l);
        customer.setCustName("李四");
        customer.setCustAddress("黑龙江");
        entityManger.merge(customer);
        ts.commit();
        entityManger.close();
    }
    @Test
    public void Delete(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
        Customer customer = entityManger.getReference(Customer.class,1l);
        entityManger.remove(customer);
        ts.commit();
        entityManger.close();

    }
    /**
     * 使用getReference方法查询时
     *           1：获取的对象是一个动态代理对象
     *           2：调用getReference方法不会立即发送sql语句查询数据库
     *              当调用查询结果对象的时候，才会发送查询的sql语句，
     *              什么是时候用，什么时候发送sql查询数据库
     *
     *         延迟加载：（懒加载）
     *         1：得到的是一个动态代理对象
     *         2：什么时候用，什么时候才会查询
     *
     */
    @Test
    public void getReference(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
        Customer customer = entityManger.getReference(Customer.class,1l);
        System.out.println(customer);
        ts.commit();
        entityManger.close();

    }
    /**
     * 使用find方法查询
     *      1：查询的对象就是当前客户对象本身
     *      2：在调用find方法的时候，就会发送sql语句查询数据库
     *
     *      立即加载
     */
    @Test
    public void Find(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
        Customer customer = entityManger.find(Customer.class,1l);
        System.out.println(customer);
        ts.commit();
        entityManger.close();
    }
    /**
     * jpa操作步骤：
     * 1.加载配置文件创建工厂(实体管理类工厂)对象
     * 2.通过实体管理工厂获取实体管理器
     * 3.获取事务对象，开启事务
     * 4.完成增删改查操作
     * 5.提交事务（回滚）
     * 6.释放资源
     */
    @Test
    public void Save(){
        //加载配置文件创建工厂（实体管理类工厂）对象
     /*  EntityManagerFactory factory= Persistence.createEntityManagerFactory("myJpa");
       //2.通过实体管理工厂获取实体管理器
        EntityManager em=factory.createEntityManager();*/
        EntityManager em=JpaUntils.getEntityManger();

        //3.获取事务对象，
        EntityTransaction et =em.getTransaction();
        //开启事务
        et.begin();
        //完成增删改查操作
        Customer customer=new Customer();
        customer.setCustName("赵四");
        customer.setCustAddress("黑背");
        //保存
        em.persist(customer);
        //5.提交事务
        et.commit();
        //6.释放资源
        em.close();
      //  factory.close();
    }
}
