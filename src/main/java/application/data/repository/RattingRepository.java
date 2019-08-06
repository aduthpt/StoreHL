//package application.data.repository;
//
//import application.data.model.Ratting;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface RattingRepository extends JpaRepository<Ratting, Integer> {
//    @Query(value = "SELECT * FROM dbo_rating r " +
//            "WHERE   r.product_id=:productId "+"order by r.rating_id",nativeQuery = true)
//
//    Ratting findFirstProductId(@Param("productId")int productId);
//
//}
