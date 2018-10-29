
package com.dataflux.xsd.archserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for row__out complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="row__out">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ch_name_matchcode_95">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="84"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="en_name_matchcode_95">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="84"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "row__out", propOrder = {
    "chNameMatchcode95",
    "enNameMatchcode95"
})
public class RowOut {

    @XmlElement(name = "ch_name_matchcode_95", required = true)
    protected String chNameMatchcode95;
    @XmlElement(name = "en_name_matchcode_95", required = true)
    protected String enNameMatchcode95;

    /**
     * Gets the value of the chNameMatchcode95 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChNameMatchcode95() {
        return chNameMatchcode95;
    }

    /**
     * Sets the value of the chNameMatchcode95 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChNameMatchcode95(String value) {
        this.chNameMatchcode95 = value;
    }

    /**
     * Gets the value of the enNameMatchcode95 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnNameMatchcode95() {
        return enNameMatchcode95;
    }

    /**
     * Sets the value of the enNameMatchcode95 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnNameMatchcode95(String value) {
        this.enNameMatchcode95 = value;
    }

}
