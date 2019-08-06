package application.data.repository;

import application.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select count(o.id) from dbo_order o")
    long getTotalOrders();

    @Query("SELECT o FROM dbo_order o " +
            "WHERE (:guid IS NULL OR (o.guid = :guid))" +
            "AND (:userName IS NULL OR (o.userName = :userName))")
    List<Order> findOrderByGuidOrUserName(@Param("guid") String guid, @Param("userName") String userName);

    @Query(value = "SELECT * FROM dbo_order o " +
            "WHERE o.order_id = :orderId  "
            +
            "ORDER BY o.order_id DESC LIMIT 1",nativeQuery = true)
    Order findFirstOrderByOrderIdContaining(@Param("orderId") int orderId);

//    @Query("SELECT o FROM dbo_order o " +
//            "WHERE (:id IS NULL OR (o.id = :id))" )
//    List<Order> findOrderById(@Param("Id")int id);
}
