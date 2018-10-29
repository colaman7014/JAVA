
package com.sas.wsdl.swiftCheckReport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for swiftCheckReportOutputBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="swiftCheckReportOutputBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nc_reference_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unique_key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status_code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status_message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "swiftCheckReportOutputBean", propOrder = {
    "ncReferenceId",
    "uniqueKey",
    "statusCode",
    "statusMessage"
})
public class SwiftCheckReportOutputBean {

    @XmlElement(name = "nc_reference_id", required = true)
    protected String ncReferenceId;
    @XmlElement(name = "unique_key", required = true)
    protected String uniqueKey;
    @XmlElement(name = "status_code", required = true)
    protected String statusCode;
    @XmlElement(name = "status_message", required = true)
    protected String statusMessage;

    /**
     * Gets the value of the ncReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcReferenceId() {
        return ncReferenceId;
    }

    /**
     * Sets the value of the ncReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcReferenceId(String value) {
        this.ncReferenceId = value;
    }

    /**
     * Gets the value of the uniqueKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqueKey() {
        return uniqueKey;
    }

    /**
     * Sets the value of the uniqueKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqueKey(String value) {
        this.uniqueKey = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

}
