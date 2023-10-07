package com.json.parser.db.antipn.repositories;

import com.json.parser.db.antipn.models.Product;
import com.json.parser.db.antipn.sqlObjects.CustomersSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT DISTINCT id_customer as id, c.first_name as firstName, c.last_name as LastName\n" +
            "from products as a join purchases as b on a.id = b.id_product\n" +
            "join customers as c on c.id = b.id_customer\n" +
            "group by id_customer, a.product_price, c.first_name, c.last_name\n" +
            "having (sum(quantity_purchase) * a.product_price) between : minNum and : maxNum;",
            nativeQuery = true)
    public Optional<List<CustomersSQL>> findCustomersByPurchasingPriceBetweenMinAndMax(Long minNum, Long maxNum);
}