package com.alucontrol.backendv1.Repository;

import com.alucontrol.backendv1.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
