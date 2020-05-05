package test;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.Role;
import cn.itcast.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.AssertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testDemo {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
/**
 * 多对多放弃维护权，被动的一方放弃维护权
 */
    @Test
    @Transactional
    @Rollback(false)
    public void save(){
        User user = new User();
        user.setUserName("刘备");
        Role role = new Role();
        role.setRoleName("小刘");
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);

    }
    @Test
    @Transactional
    @Rollback(false)
    public void save1(){
        User user = new User();
        user.setUserName("张飞");
        Role role = new Role();
        role.setRoleName("小张");
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
    }
    /**
     * 删除用户id为1，同时删除与之关联的角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void delete(){
        User user = userDao.findOne(6l);
        userDao.delete(user);
    }

}
