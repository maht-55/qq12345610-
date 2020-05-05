package test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.dao.LinkmanDao;
import cn.itcast.entity.Customer;
import cn.itcast.entity.Linkman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkmanDao linkmanDao;
    @Test
    @Transactional
    @Rollback(false)
    public void  save(){
        Customer customer =new Customer();
        customer.setCustName("刘备");
        Linkman linkman=new Linkman();
        linkman.setLkmName("小刘 ");
        /**
         *  配置了客户到联系人的方式
         *     从客户的角度上，发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         *     由于我们配置了客户到联系人的关系，客户可以对外键进行维护
         */
        customer.getSetLinkman().add(linkman);
        customerDao.save(customer);
        linkmanDao.save(linkman);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void  save1(){
        Customer customer =new Customer();
        customer.setCustName("张飞");
        Linkman linkman=new Linkman();
        linkman.setLkmName("小张 ");
        /**
         *  配置了客户到联系人的方式
         *     从客户的角度上，发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         *     由于我们配置了客户到联系人的关系，客户可以对外键进行维护
         */
        linkman.setCustomer(customer);
        customerDao.save(customer);
        linkmanDao.save(linkman);

    }
    @Test
    @Transactional
    @Rollback(false)
    public void  save2(){
        Customer customer =new Customer();
        customer.setCustName("张飞");
        Linkman linkman=new Linkman();
        linkman.setLkmName("小张 ");
        linkman.setCustomer(customer);
        customer.getSetLinkman().add(linkman);
        customerDao.save(customer);
        linkmanDao.save(linkman);
    }

    /**
     * 级联添加 ：保存一个客户的同时，保存客户的所有联系人
     *            需要在操作主体的实体类上，配置casacde属性
     */
    @Test
    @Transactional
    @Rollback(false)
    public void  save3(){
        Customer customer =new Customer();
        customer.setCustName("关羽");
        Linkman linkman=new Linkman();
        linkman.setLkmName("小关 ");
        linkman.setCustomer(customer);
        customer.getSetLinkman().add(linkman);
        customerDao.save(customer);
    }

    /**
     * 级联删除 ：
     *        删除1号客户的同时，删除1号客户的所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void  save4(){
        Customer one = customerDao.findOne(1l);
        customerDao.delete(one);

    }




}
