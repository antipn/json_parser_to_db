package com.json.parser.db.antipn.repositories.archive;

import com.json.parser.db.antipn.models.Customer;
import com.json.parser.db.antipn.models.Product;
import com.json.parser.db.antipn.sqlObjects.CustomerSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CommonRepository<T> {//extends JpaRepository<T, Long> {

    Optional<Product> findProductByProductName(String productName);

    @Query(value = "SELECT DISTINCT id_customer as id, c.first_name as firstName, c.last_name as LastName\n" +
            "from products as a join purchases as b on a.id = b.id_product\n" +
            "join customers as c on c.id = b.id_customer\n" +
            "group by id_customer, a.product_price, c.first_name, c.last_name\n" +
            "having (sum(quantity_purchase) * a.product_price) between : minNum and : maxNum;",
            nativeQuery = true)
    public Optional<List<CustomerSQL>> findCustomersByPurchasingPriceBetweenMinAndMax(Long minNum, Long maxNum);


    Optional<List<Customer>> findAllByLastName(String lastName);

    @Query(value =
            "SELECT id_customer as id, c.first_name as firstName, c.last_name as LastName \n" +
                    "from products as a join purchases as b on a.id = b.id_product\n" +
                    "join customers as c on c.id = b.id_customer\n" +
                    "group by id_customer, c.first_name, c.last_name\n" +
                    "order by sum(quantity_purchase) ASC LIMIT : num\n "
            , nativeQuery = true)
    public Optional<List<CustomerSQL>> findBadCustomers(Long num);

    @Query(value =
            "SELECT id_customer as id, c.first_name as firstName, c.last_name as lastName\n" +
                    "FROM purchases join customers as c on purchases.id_customer = c.id\n" +
                    "group by id_product, id_customer, c.first_name, c.last_name having (sum(quantity_purchase)) >= :minTimes and id_product = :productId\n"
            , nativeQuery = true)
    Optional<List<CustomerSQL>> findMinTimes(Long productId, Long minTimes);


}
