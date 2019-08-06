//package application.data.repository;
//
//import application.data.model.Cmt;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface CmtRepository extends JpaRepository<Cmt,Integer> {
//    @Query("select count(c.id) from dbo_cmt c")
//    long getTotalCmts();
//    @Query(value = "SELECT * FROM dbo_cmt cc " +
//            "WHERE cc.user_id = :userId  " +
//            "AND cc.product_id = :productId " +
//            "ORDER BY cc.cmt_id DESC LIMIT 1",nativeQuery = true)
//    Cmt findFirstCmtByUserIdAndProductId(@Param("userId")int userId, @Param("productId") int productId);
//
//}
