package test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import org.aspectj.weaver.ast.And;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;
    @Test
    public void findOne(){
        /**
         * 自定义查询条件
         *       1.实现Spectication接口(提供泛型：查询对象类型)
         *       2.实现toPredicate(构造查询条件)
         *       3.需要借助方法参数中的两个参数（
         *               root：获取需要查询的对象属性
         *               CriteriaBuilder：构造查询条件的，内部分装了很多的查询条件如（模糊，精准等等）
         *       ）
         *       查询条件：
         *          1.查询方式：
         *                     cb对象
         *          2.比较的属性名称
         *                     root对象
         */
        Specification<Customer>spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.equal(custName, "关羽");
                return predicate;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }
    /**
     * 多条件查询
     */
    @Test
    public void findT(){
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = cb.equal(custName, "关平");
                Predicate p2 = cb.equal(custIndustry, "学生");
                Predicate and = cb.and(p1, p2);
                return and;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }
    /**
     * 模糊查询
     * equals:直接得到Path对象(属性),然后进行比较
     * gt,lt,ge,le,like:得到Path对象，根据Path指定比较的参数类型，再去比较
     * 指定参数类型 path.as(类型的字节码对象)
     *
     */
    @Test
    public  void  findAll(){
        Specification<Customer>spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate like = cb.like(custName.as(String.class), "关%");
                return like;
            }
        };
        List<Customer> all = customerDao.findAll(spec);
        for (Customer ct:all){
            System.out.println(ct);
        }
    }
    /**
     * 排序
     */
    @Test
    public  void  Order(){
        Specification<Customer>spec=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate like = cb.like(custName.as(String.class), "关%");
                return like;
            }
        };
       //创建排序对象，需要调用构造方法实例化sort对象
        //第一个参数：排序的顺序（倒叙，正序）
        //第二个参数：排序的属性名称
        Sort sort =new Sort(Sort.Direction.DESC,"custId");
        List<Customer> all = customerDao.findAll(spec, sort);
        System.out.println(all);
    }
    /**
     * 分页查询
     *   Pageable: 分页查询
     *       分页参数：查询的页码，每页查询的条数
     *     findAll(Specification，Pageable )带有条件的分页
     *     findAll(Pageable )没有条件的分页
     *     返回 Page(springDataJpa为我们封装好的pageBean对象，数据列表，总条数)
     *
     */
    @Test
    public void page(){
        Specification<Customer>spec =null;
        //PageRequest对象是 Pageable实现类
        /**
         * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
         *       第一个参数：当前查询的页数（从0开始）
         *       第二个参数：每页查询的数量
         */
        Pageable  page =new PageRequest(0,2);
        Page<Customer> all = customerDao.findAll(spec, page);
        System.out.println(all.getTotalElements());//得到总条数
        System.out.println(all.getTotalPages());//得到总页数
        System.out.println(all.getContent());//得到数据集合列表

    }

}
