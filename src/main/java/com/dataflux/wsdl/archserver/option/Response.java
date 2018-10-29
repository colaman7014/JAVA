
package com.dataflux.wsdl.archserver.option;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="table_" type="{archserver.xsd.dataflux.com}table__out" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
    "table"
})
public class Response {

    @XmlElement(name = "table_")
    protected TableOut table;

    /**
     * Gets the value of the table property.
     * 
     * @return
     *     possible object is
     *     {@link TableOut }
     *     
     */
    public TableOut getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOut }
     *     
     */
    public void setTable(TableOut value) {
        this.table = value;
    }

}
