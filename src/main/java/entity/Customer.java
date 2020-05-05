package entity;

import javax.persistence.*;

/**
 * @Entity :声明实体类
 * @Table  ：配置实体类和表的映射关系
 */
@Entity
@Table(name="cst_customer")
public class Customer {
    //声明主键
    /**
     * IDENTITY:自增 mysql
     * SEQUENCE :序列 oracle
     * TABLE:jpa提供的一种机制，通过一张数据库表的形式帮助我们完成自增
     * AUTO:程序自动的帮我们选择主键生成策略
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private  long custId; //主键
    @Column(name = " cust_name")
    private String custName; //名称
    @Column(name = "cust_source")
    private String custSource; //客户来源
    @Column(name = "cust_level")
    private String custLevel; //客户级别
    @Column(name = "cust_industry")
    private String custIndustry;  //客户所属行业
    @Column(name = "cust_phone")
    private String custPhone; //客户的联系方式
    @Column(name = "cust_address")
    private  String custAddress;  //客户地址

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
