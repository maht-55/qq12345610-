package test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class jpqlTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public  void findCustName(){
        Customer name = customerDao.findCustName("王五");
        System.out.println(name);
    }

    @Test
    public void findNameAndId(){
        Customer customer = customerDao.findNameAndId("张三",1l);
        System.out.println(customer);
    }

    /**
     * springDataJpa中使用jpql完成 更新/删除操作
     *      需要手动添加事务的支持，
     *      默认执行结束后，事务自动回滚
     *
     *      @Rollback false 不回滚
     *                 true: 回滚
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void update(){
        customerDao.update(1l,"关羽");
    }

     @Test
    public void findSql(){
         List<Object[]> list = customerDao.findSql();
         for (Object [] obj:list){
             System.out.println(Arrays.toString(obj));
         }
     }

     @Test
    public void findsqls(){
         List<Object[]> sqls = customerDao.findSqls("关%");
         for (Object [] obj:sqls){
             System.out.println(Arrays.toString(obj));
         }
     }

     @Test
    public void testFind(){
         Customer name = customerDao.findCustName("关羽");
         System.out.println(name);
     }

     @Test
         public void testList(){
         List<Customer> like = customerDao.findByCustNameLike("关%");
         for (Customer c:like){
             System.out.println(c);
         }
     }
    @Test
    public void testList1(){
        Customer c = customerDao.findByCustNameLikeAndCustIndustry("关%", "学生");
        System.out.println(c);
    }

}
