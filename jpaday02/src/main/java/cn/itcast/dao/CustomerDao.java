package cn.itcast.dao;

import cn.itcast.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 符合springDataJpa的Dao层接口规范
 * JpaRepository<操作的实体类型,实体类中主键属性的类型>
 *     封装了crud操作
 * JpaSpecificationExecutor<操作的实体类型>
 *     封装了复杂查询（分页）
 */
public interface CustomerDao  extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {
    /**
     * 配置jpql语句，使用query注解
     */
    @Query(value = "from  Customer  where custName =?1")
    Customer  findCustName(String custName);

    /**
     * 根据客户名称和客户id进行查询
     *   对于多个占位符参数
     *      赋值的时候，默认情况下，占位符的位置需要和方法参数中的位置保持一致
     *    可以指定占位符参数的位置
     *      ？后跟索引的方式，指定占位符的取值来源
     */
    @Query(value = "from Customer  where custId=?2 and custName=?1 ")
    Customer findNameAndId(String custName,Long id);

    /**
     * 使用jpql完成更新操作
     * sql  ：update cst_customer set cust_name=? where cust_id=?
     * jpql ：update Customer set custName=? where custId=?
     * @Query：代表的是进行查询操作
     * @Modifying ：代表的是进行更新操作
     */
    @Query(value = "update Customer set custName=?2 where custId=?1")
    @Modifying
     void  update(Long id,String name);
    /**
     * 使用Sql查询
     *     sql:select * from cst_custmoer
     *     Query: sql 配置
     *           value: sql语句
     *           nativeQuery: true sql查询
     *                        false jpql查询
     */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Object []> findSql();

    @Query(value = "select * from cst_customer where cust_name like  ?1",nativeQuery = true)
    List<Object []> findSqls(String name);
    /**
     * 方法名约定
     *     findBy: 查询
     *             对象的属性名 （首字母大写）：查询的条件
     *             默认使用  ==
     *             特殊查询：
     *
     *     findByCustName :根据客户名称查询
     *     再springdataJpa的运行阶段
     *                会根据方法名称进行解析    findBy   from  实体类
     *                                           属性名        where  属性 =?
     *    1. findBy + 属性名称（根据属性名称进行完成匹配的查询=）
     *    2. findBy + 属性名称 + "查询方式（Like||isnull）"
     *    3.多条件查询
     *      findBy  +  属性名 + "查询方式" + " 多条件连接符(and||or)" + 属性名 + " 查询方式"
     *
     *
     *
     *
     */
    Customer findByCustName(String name);

     List<Customer>  findByCustNameLike(String name);

     Customer findByCustNameLikeAndCustIndustry(String name,String industry);
}
