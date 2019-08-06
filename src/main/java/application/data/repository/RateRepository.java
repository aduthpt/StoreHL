package application.data.repository;

import application.data.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository  extends JpaRepository<Rate,Integer> {
    @Query(value = "select avg(star) " +
            "from dbo_rate r " +
            "where r.product_id=:productId",nativeQuery = true)
    Float getRateAvg(@Param("productId") Integer productId);
}
