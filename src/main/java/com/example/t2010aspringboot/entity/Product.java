package com.example.t2010aspringboot.entity;

import com.example.t2010aspringboot.entity.base.BaseEntity;
import com.example.t2010aspringboot.enums.ProductStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String slug;
    private String description;
    private String detail;
    private double price;
    private String thumbnail;
    private String manufacturer;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
}
