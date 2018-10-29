
package com.dataflux.wsdl.archserver.option;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for row__in complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="row__in">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sensitivity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="15"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ch_name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="200"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="en_name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="200"/>
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
@XmlType(name = "row__in", propOrder = {
    "sensitivity",
    "chName",
    "enName"
})
public class RowIn {

    @XmlElement(required = true)
    protected String sensitivity;
    @XmlElement(name = "ch_name", required = true)
    protected String chName;
    @XmlElement(name = "en_name", required = true)
    protected String enName;

    /**
     * Gets the value of the sensitivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensitivity() {
        return sensitivity;
    }

    /**
     * Sets the value of the sensitivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensitivity(String value) {
        this.sensitivity = value;
    }

    /**
     * Gets the value of the chName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChName() {
        return chName;
    }

    /**
     * Sets the value of the chName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChName(String value) {
        this.chName = value;
    }

    /**
     * Gets the value of the enName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnName() {
        return enName;
    }

    /**
     * Sets the value of the enName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnName(String value) {
        this.enName = value;
    }

}
