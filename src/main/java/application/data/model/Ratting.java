//package application.data.model;
//
//import javax.persistence.*;
//
//@Entity(name = "dbo_rating")
//public class Ratting {
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "rating_id")
//    @Id
//    private int id;
////
////    @OneToOne(optional = true, fetch = FetchType.LAZY)
////    @JoinColumn(name = "user_id")
////    private User User;
//
//    @ManyToOne(optional = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//    @Column(name = "username")
//    private String userName;
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @Column(name = "point")
//    private double point;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public double getPoint() {
//        return point;
//    }
//
//    public void setPoint(double point) {
//        this.point = point;
//    }
//}
