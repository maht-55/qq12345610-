package demoTest;

import entity.Customer;
import org.junit.Test;
import untils.JpaUntils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class JpaTest02 {
    /**
     * 条件查询
     * jpql :from Customer where  custName like
     * 使用JPA样式的序号参数（例如，`？1’）
     */
    @Test
        public void TiaoJian(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
        String sql ="from Customer where custName like ?1";
        Query query = entityManger.createQuery(sql);
        query.setParameter(1,"李四%");
        List list = query.getResultList();
        for (Object obj:list){
            System.out.println(obj);

        }
        ts.commit();
        entityManger.close();
    }
    /**
     * 分页查询
     * jpql:from Customer
     */
    @Test
    public void Limit(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
        String sql ="from Customer ";
        Query query = entityManger.createQuery(sql);
        query.setFirstResult(0);
        query.setMaxResults(3);
        List list = query.getResultList();
        for (Object obj:list){
            System.out.println(obj);

        }
        ts.commit();
        entityManger.close();
    }
    /**
     * 统计
     * jpql:select count(custId) from Customer
     */
    @Test
    public void Count(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
        String sql ="select count(custId) from Customer  ";
        Query query = entityManger.createQuery(sql);
        /**
         * getResultList():直接将查询对象封装为list集合
         * getSingleResult():得到唯一的结果集
         */
        Object s = query.getSingleResult();
        System.out.println(s);
        ts.commit();
        entityManger.close();
    }
    /**
     * 排序查询
     *  jpql: from Customer order by
     */
    @Test
    public void Order(){
        EntityManager entityManger = JpaUntils.getEntityManger();
        EntityTransaction ts = entityManger.getTransaction();
        ts.begin();
       String sql ="from entity.Customer order by custId desc ";
        Query query = entityManger.createQuery(sql);
        List list = query.getResultList();
        for(Object obj:list){
            System.out.println(list);
        }
        ts.commit();
        entityManger.close();

    }
    /**
     * 查询全部
     *        jqpl:from entity.Customer
     *        sql :select * from cst_customer
     */
    @Test
    public void Save(){
        //获取entityManger对象
        EntityManager entityManger = JpaUntils.getEntityManger();
        //获取事务对象
        EntityTransaction ts = entityManger.getTransaction();
        //开启事务
        ts.begin();
        String sql ="from entity.Customer";
        Query query = entityManger.createQuery(sql);
        List list = query.getResultList();
       for (Object obj:list){
           System.out.println(obj);
       }
        // 提交事务
        ts.commit();
        //释放资源
        entityManger.close();
    }
}
