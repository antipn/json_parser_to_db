package com.json.parser.db.antipn.repositories;

import com.json.parser.db.antipn.models.Customer;
import com.json.parser.db.antipn.sqlObjects.CustomerSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<List<Customer>> findAllByLastName(String lastName);

    @Query(value =
            "SELECT id_customer as id, c.first_name as firstName, c.last_name as LastName \n" +
                    "from products as a join purchases as b on a.id = b.id_product\n" +
                    "join customers as c on c.id = b.id_customer\n" +
                    "group by id_customer, c.first_name, c.last_name\n" +
                    "order by sum(quantity_purchase) ASC LIMIT :num \n "
            , nativeQuery = true)
    public Optional<List<CustomerSQL>> findBadCustomers(Long num);

}
