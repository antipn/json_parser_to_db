package com.json.parser.db.antipn.repositories;

import com.json.parser.db.antipn.models.entity.Customer;
import com.json.parser.db.antipn.models.sqlObjects.CustomerSQL;
import com.json.parser.db.antipn.models.sqlObjects.ProductSQL;
import com.json.parser.db.antipn.models.sqlObjects.WorkingDaysSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<List<Customer>> findAllByLastNameOrderByFirstName(String lastName);

    @Query(value =
            "SELECT id_customer as id, c.first_name as firstName, c.last_name as lastName \n" +
                    "from products as a join purchases as b on a.id = b.id_product\n" +
                    "join customers as c on c.id = b.id_customer\n" +
                    "group by id_customer, c.first_name, c.last_name\n" +
                    "order by sum(quantity_purchase) ASC LIMIT :num \n "
            , nativeQuery = true)
    public Optional<List<CustomerSQL>> findBadCustomers(Long num);

    @Query(value = "SELECT count(*) as workingDays from w_days;"
            , nativeQuery = true)
    public Optional<WorkingDaysSQL> getTotalWorkingDays();

    @Transactional
    @Modifying
    @Query(value =
            "Delete From w_days;", nativeQuery = true)
    public void deleteAllRecordsFromWDaysTable();

    @Transactional
    @Modifying
    @Query(value =
            "insert into w_days\n" +
                    "select *\n" +
                    "from (select (generate_series(:startDate\\:\\:date, :endDate\\:\\:date, '1 day')) as date) as d\n" +
                    "where extract(dow from d.date) between 1 and 5;"
            , nativeQuery = true)
    public void fillingWDaysTable(String startDate, String endDate);

    @Query(value = "select A.id as Id, A.first_name as firstName, A.last_name as lastName from customers as A join purchases as B on A.id = B.id_customer\n" +
            "    inner join w_days C on C.working_days = B.date_purchase group by A.id order by a.id ASC;\n", nativeQuery = true)
    public Optional<List<CustomerSQL>> getCustomerForStatistics();


    @Query(value = "select B.product_name as productName, count(B.id)*B.product_price as expenses\n" +
            "from purchases as A\n" +
            "         join products as B on A.id_product = B.id\n" +
            "         join customers as C on A.id_customer = C.id inner join w_days as D on A.date_purchase = D.working_days\n" +
            "where C.id=:customerId\n" +
            "group by B.id,C.id;", nativeQuery = true)
    public Optional<List<ProductSQL>> getStatisticByCystomerId(Long customerId);
}
