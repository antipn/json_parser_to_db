package com.json.parser.db.antipn.models;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_customers")
    @SequenceGenerator(name = "global_seq_customers", sequenceName = "global_seq_customers", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;
}
