package com.psuti.rvprs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductXML {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    @XmlAttribute(name = "id")
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @XmlElement(name = "quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
