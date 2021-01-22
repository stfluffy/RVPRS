package com.psuti.rvprs.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Modenov D.A.
 */

@Entity
@Table(name = "product")
@Data
public class Product {

    /**
     * Идентификатор истории.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Имя.
     */
    @Column(name = "name")
    private String name;

    /**
     * Ценка.
     */
    @Column(name = "price")
    private Integer price;

    /**
     * Количество.
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * Полка с товарами.
     */
    @Column(name = "shelf")
    private Long shelf;
}
