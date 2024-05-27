package com.alucontrol.backendv1.Repository.Product;

import com.alucontrol.backendv1.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    //Method for sum the item Scaffolds -> Display on Product Page via ProductController
    @Query(value = "SELECT SUM(item_quantity) FROM AluControlV1.products WHERE item_description = 'Scaffolds';", nativeQuery = true)
    Long getSumScaffolds();

    //Method for display the qty of item Scaffolds RENTED -> Display on Product Page via ProductController
}
