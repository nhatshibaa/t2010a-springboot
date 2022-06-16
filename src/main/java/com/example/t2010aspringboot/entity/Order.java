package com.example.t2010aspringboot.entity;

import com.example.t2010aspringboot.entity.base.BaseEntity;
import com.example.t2010aspringboot.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
    @Column(updatable = false,insertable = false)
    private String userId;


    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;

    private double totalPrice;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;
}
