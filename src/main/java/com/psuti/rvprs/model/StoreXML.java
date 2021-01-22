package com.psuti.rvprs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="store")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreXML {

    String storeName;

    List<ShelfXML> shelves;

    @XmlAttribute(name = "name")
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @XmlElement(name = "shelves")
    public void setShelves(List<ShelfXML> shelves) {
        this.shelves = shelves;
    }
}
