
package com.dataflux.wsdl.archserver.option;

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
 *         &lt;element name="en_name_matchcode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="112"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="en_name_matchcode_org">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="140"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ch_name_matchcode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ch_name_matchcode_org">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="400"/>
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
    "enNameMatchcode",
    "enNameMatchcodeOrg",
    "chNameMatchcode",
    "chNameMatchcodeOrg"
})
public class RowOut {

    @XmlElement(name = "en_name_matchcode", required = true)
    protected String enNameMatchcode;
    @XmlElement(name = "en_name_matchcode_org", required = true)
    protected String enNameMatchcodeOrg;
    @XmlElement(name = "ch_name_matchcode", required = true)
    protected String chNameMatchcode;
    @XmlElement(name = "ch_name_matchcode_org", required = true)
    protected String chNameMatchcodeOrg;

    /**
     * Gets the value of the enNameMatchcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnNameMatchcode() {
        return enNameMatchcode;
    }

    /**
     * Sets the value of the enNameMatchcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnNameMatchcode(String value) {
        this.enNameMatchcode = value;
    }

    /**
     * Gets the value of the enNameMatchcodeOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnNameMatchcodeOrg() {
        return enNameMatchcodeOrg;
    }

    /**
     * Sets the value of the enNameMatchcodeOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnNameMatchcodeOrg(String value) {
        this.enNameMatchcodeOrg = value;
    }

    /**
     * Gets the value of the chNameMatchcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChNameMatchcode() {
        return chNameMatchcode;
    }

    /**
     * Sets the value of the chNameMatchcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChNameMatchcode(String value) {
        this.chNameMatchcode = value;
    }

    /**
     * Gets the value of the chNameMatchcodeOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChNameMatchcodeOrg() {
        return chNameMatchcodeOrg;
    }

    /**
     * Sets the value of the chNameMatchcodeOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChNameMatchcodeOrg(String value) {
        this.chNameMatchcodeOrg = value;
    }

}
