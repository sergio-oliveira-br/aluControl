package com.alucontrol.backendv1.Repository;


import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Projection.ItemsTPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface DashboardRepository extends JpaRepository<Rent, Long>
{
    /**This method will present the relation between item vs total price rented*/
    @Query("SELECT RentProjection.rentItem AS rentItem," +
            "SUM(RentProjection.rentTotalPrice) AS rentTotalPrice " +
            "FROM Rent RentProjection " +
            "GROUP BY RentProjection.rentItem")
    List<ItemsTPriceProjection> findItemsTotalPrice();


    /**This method will present each item rented */
    @Query("SELECT RentProjection.rentItem AS rentItem, " +
            "RentProjection.rentTotalPrice AS rentTotalPrice " +
            "FROM Rent RentProjection")
    List<ItemsTPriceProjection> findRentItems();

}
