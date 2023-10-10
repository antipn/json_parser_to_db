package com.json.parser.db.antipn.repositories;

import com.json.parser.db.antipn.models.Customer;
import com.json.parser.db.antipn.models.sqlObjects.CustomerSQL;
import com.json.parser.db.antipn.models.sqlObjects.ProductSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<List<Customer>> findAllByLastName(String lastName);

    @Query(value =
            "SELECT id_customer as id, c.first_name as firstName, c.last_name as lastName \n" +
                    "from products as a join purchases as b on a.id = b.id_product\n" +
                    "join customers as c on c.id = b.id_customer\n" +
                    "group by id_customer, c.first_name, c.last_name\n" +
                    "order by sum(quantity_purchase) ASC LIMIT :num \n "
            , nativeQuery = true)
    public Optional<List<CustomerSQL>> findBadCustomers(Long num);


    @Query(value = "Select count(*) as work_days from (SELECT EXTRACT(dow FROM generate_series('2023-10-01'::date, '2023-10-09'::date, '1 day')) as dow) as Tdow\n" +
            "where dow between 1 and 5 ;",nativeQuery = true)
    public Integer getTotalWorkingDays();

    @Transactional
    @Modifying
    @Query(value =
            "Delete From w_days;", nativeQuery = true)
    public void deleteFromWDays();

    @Transactional
    @Modifying
    @Query(value =
            "insert into w_days\n" +
                    "select *\n" +
                    "from (select (generate_series(:startDate\\:\\:date, :endDate\\:\\:date, '1 day')) as date) as d\n" +
                    "where extract(dow from d.date) between 1 and 5;"
            , nativeQuery = true)
    public void fillingWDays(String startDate, String endDate);


    @Query(value = "select B.product_name as productname,B.product_price, count(B.id)*B.product_price as expenses\n" +
            "from purchases as A\n" +
            "         join products as B on A.id_product = B.id\n" +
            "         join customers as C on A.id_customer = C.id inner join w_days as D on A.date_purchase = D.working_days\n" +
            "where c.id=:user_id\n" +
            "group by B.id,c.id;", nativeQuery = true)
    public Optional<List<ProductSQL>> getStatisticByUserID(Long user_id);
}
