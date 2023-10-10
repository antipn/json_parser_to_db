package com.json.parser.db.antipn.repositories;

import com.json.parser.db.antipn.models.Purchase;
import com.json.parser.db.antipn.models.sqlObjects.CustomerSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value =
            "SELECT id_customer as id, c.first_name as firstName, c.last_name as lastName\n" +
                    "FROM purchases join customers as c on purchases.id_customer = c.id\n" +
                    "group by id_product, id_customer, c.first_name, c.last_name having (sum(quantity_purchase)) >= :minTimes and id_product = :productId\n"
            , nativeQuery = true)
    Optional<List<CustomerSQL>> findMinTimes(Long productId, Long minTimes);

    @Query(value = "SELECT DISTINCT id_customer as id, c.first_name as firstName, c.last_name as LastName\n" +
            "from products as a join purchases as b on a.id = b.id_product\n" +
            "join customers as c on c.id = b.id_customer\n" +
            "group by id_customer, a.product_price, c.first_name, c.last_name\n" +
            "having (sum(quantity_purchase) * a.product_price) between :minNum and :maxNum ;",
            nativeQuery = true)
    public Optional<List<CustomerSQL>> findCustomersByPurchasingPriceBetweenMinAndMax(Long minNum, Long maxNum);

}
