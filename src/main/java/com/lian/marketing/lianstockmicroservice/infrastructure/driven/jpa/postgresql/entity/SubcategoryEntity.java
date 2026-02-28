package com.lian.marketing.lianstockmicroservice.infrastructure.driven.jpa.postgresql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subcategory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubcategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(unique = false, length = 200)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    @Column(nullable = false)
    private Boolean isActive;
}
