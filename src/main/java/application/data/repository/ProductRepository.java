package application.data.repository;

import application.data.model.Color;
import application.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select count(p.id) from dbo_product p")
    long getTotalProducts();

    @Query("SELECT p FROM dbo_product p " +
            "WHERE (:categoryId IS NULL OR (p.categoryId = :categoryId))" +
            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
    Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable, @Param("categoryId") Integer categoryId, @Param("productName") String productName);


    @Query("SELECT p FROM dbo_product p " +
            "WHERE  ( UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
    List<Product> getListProductByName(@Param("productName") String productName);

    @Query(value = "SELECT c.* FROM dbo_product p " +
            "inner join dbo_product_entity pe on(p.product_id = pe.product_id) " +
            "inner join dbo_color c on (c.color_id = pe.color_id) " +
            "WHERE  p.product_id=:productId", nativeQuery = true)
    List<Color> getListColorProduct(@Param("productId") Integer productId);



}
