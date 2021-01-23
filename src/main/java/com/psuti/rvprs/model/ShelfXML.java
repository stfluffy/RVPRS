package com.psuti.rvprs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author Modenov D.A.
 */

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShelfXML {

    private Long id;

    private String name;

    private List<ProductXML> products;

    @XmlAttribute(name = "id")
    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "products")
    public void setProducts(List<ProductXML> products) {
        this.products = products;
    }
}
