package application.data.repository;

import application.data.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity,Integer> {
    List<ProductEntity> findByProductId(Integer productId);

    @Query("SELECT p FROM dbo_product_entity p ")
    List<ProductEntity> getAll();

    @Query("SELECT p FROM dbo_product_entity p " +
            "WHERE (:productId IS NULL OR (p.productId = :productId))" +
            "AND (:colorId IS NULL OR (p.colorId = :colorId)) " +
            "AND (:sizeId IS NULL OR (p.sizeId = :sizeId))")
    ProductEntity getByProductSizeColor(@Param("productId") Integer productId, @Param("colorId") Integer colorId, @Param("sizeId") Integer sizeId);
}
