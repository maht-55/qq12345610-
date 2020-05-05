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

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpaTestDemo {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkmanDao linkmanDao;
    @Test
    @Transactional//解决 测试no session问题
    public void find(){
        Customer one = customerDao.getOne(2l);
        Set<Linkman> setLinkman = one.getSetLinkman();
        for(Linkman l:setLinkman){
            System.out.println(l);
        }

    }
    /**
     * 对象导航查询
     *      默认使用的是延迟加载的形式查询
     *        调用get方法并不会立即发送查询，而是在使用关联对象的时候才会执行
     *       延迟加载
     *     修改配置，将延迟加载改为立即加载
     *       fetch,需要配置到多表映射关系的注解上
     */
    @Test
    @Transactional//解决 测试no session问题
    public void find1(){
        Customer one = customerDao.getOne(2l);
        Set<Linkman> setLinkman = one.getSetLinkman();
            System.out.println(setLinkman);
    }

    /**
     * 从 多的一方导航查询 一的一方
     *  默认：立即加载
     */
    @Test
    @Transactional//解决 测试no session问题find
    public void find2(){
        Linkman one = linkmanDao.findOne(3l);
        Customer customer = one.getCustomer();
        System.out.println(customer);
    }


}
