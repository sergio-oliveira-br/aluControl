package com.alucontrol.backendv1.Repository.Customer;

import com.alucontrol.backendv1.Model.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
