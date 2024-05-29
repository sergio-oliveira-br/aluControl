package com.alucontrol.backendv1.Repository;


import com.alucontrol.backendv1.Model.Rent;
import com.alucontrol.backendv1.Projection.ItemsAndTotalPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public interface DashboardRepository extends JpaRepository<Rent, Long>
{

}
