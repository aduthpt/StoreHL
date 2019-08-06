//package application.data.model;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity(name = "dbo_cmt")
//public class Cmt{
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "cmt_id")
//    @Id
//    private int id;
//
//    @ManyToOne(optional = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//    @ManyToOne(optional = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(name = "cmt")
//    private String cmt;
//
//    @Column(name = "created_date")
//    private Date createdDate;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getCmt() {
//        return cmt;
//    }
//
//    public void setCmt(String cmt) {
//        this.cmt = cmt;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//}
