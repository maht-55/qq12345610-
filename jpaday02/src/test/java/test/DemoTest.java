package test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class DemoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 判断id为。。是否存在
     */
     @Test
     public void  Exiext(){
         boolean exists = customerDao.exists(3l);
         System.out.println(exists);
     }
    /**
     * count:统计查询
     */
    @Test
    public void  Count(){
        long count = customerDao.count();
        System.out.println(count);
    }
    /**
     * 查询所有
     */
    @Test
    public void FindAll(){
        List<Customer> list = customerDao.findAll();
        for (Object obj:list){
            System.out.println(obj);
        }
    }
    /**
     * 删除
     */

    @Test
    public void Delete(){
        customerDao.delete(4l);
    }
    /**
     * save:  保存或更新
     *      根据传递的对象是否存在主键id,如果没有id主键属性：保存
     *      存在id属性，根据id查询数据，更新数据
     */
    @Test
    public void Save(){
        Customer customer = new Customer();
       customer.setCustName("刘备");
       customer.setCustAddress("湖北");
        Customer save = customerDao.save(customer);
        System.out.println(save);
    }
    @Test
    public void Save1(){
        Customer customer = new Customer();
        customer.setCustId(4l);
        customer.setCustName("关羽");
        customer.setCustAddress("湖北");
        Customer save = customerDao.save(customer);
        System.out.println(save);
    }
    /**
     * 查询
     */
    @Test
    public void findOne(){
        Customer one = customerDao.findOne(3l);
        System.out.println(one);
    }
    /**
     * 根据 id进行查询
     *  @Transactional:保证getOne正常运行
     *  findOne -em.findOne():立即执行
     *  getOne em.getRefernece() :延迟加载
     *   返回的是一个 客户的动态代理对象
     *   什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void getOne(){
        Customer one = customerDao.getOne(3l);
        System.out.println(one);

    }


}
