package com.example.groomingsalonapp.Repository;

import com.example.groomingsalonapp.Domain.Handling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HandlingRepository extends JpaRepository<Handling, Long> {

    @Query(value = "SELECT SUM(h.total_cost) * (1 - :discount) FROM handling h " +
            "JOIN appointment a ON h.handling_id = a.handling_id",
            nativeQuery = true)
    Double calculateTotalRevenueWithDiscount(@Param("discount") double discount);
}
