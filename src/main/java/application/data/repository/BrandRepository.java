package application.data.repository;

import application.data.model.Brand;

import application.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {

    @Query("select count(b.id) from dbo_brand b")
    long getTotalBrands();
    @Query("SELECT b FROM dbo_brand b " +
            "WHERE (:brandName IS NULL OR UPPER(b.name) LIKE CONCAT('%',UPPER(:brandName),'%'))")
    List<Brand> getListBrandByBrandNameContaining(@Param("brandName") String brandName);
}
