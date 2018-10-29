
package com.sas.wsdl.swiftCheckReport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for swiftCheckReportInputBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="swiftCheckReportInputBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nc_reference_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unique_key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calling_system" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branch_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nc_result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nc_case_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nc_case_status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hit_list_session" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "swiftCheckReportInputBean", propOrder = {
    "ncReferenceId",
    "uniqueKey",
    "branchNumber",
    "unit",
    "ncResult",
    "ncCaseId",
    "ncCaseStatus",
    "hitListSession"
})
public class SwiftCheckReportInputBean {

    @XmlElement(name = "nc_reference_id", required = true)
    protected String ncReferenceId;
    @XmlElement(name = "unique_key", required = true)
    protected String uniqueKey;
    @XmlElement(name = "branch_number", required = true)
    protected String branchNumber;
    @XmlElement(required = true)
    protected String unit;
    @XmlElement(name = "nc_result", required = true)
    protected String ncResult;
    @XmlElement(name = "nc_case_id", required = true)
    protected String ncCaseId;
    @XmlElement(name = "nc_case_status", required = true)
    protected String ncCaseStatus;
    @XmlElement(name = "hit_list_session", required = true)
    protected String hitListSession;

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
     * Gets the value of the branchNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchNumber() {
        return branchNumber;
    }

    /**
     * Sets the value of the branchNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchNumber(String value) {
        this.branchNumber = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the ncResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcResult() {
        return ncResult;
    }

    /**
     * Sets the value of the ncResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcResult(String value) {
        this.ncResult = value;
    }

    /**
     * Gets the value of the ncCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcCaseId() {
        return ncCaseId;
    }

    /**
     * Sets the value of the ncCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcCaseId(String value) {
        this.ncCaseId = value;
    }

    /**
     * Gets the value of the ncCaseStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcCaseStatus() {
        return ncCaseStatus;
    }

    /**
     * Sets the value of the ncCaseStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcCaseStatus(String value) {
        this.ncCaseStatus = value;
    }

    /**
     * Gets the value of the hitListSession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHitListSession() {
        return hitListSession;
    }

    /**
     * Sets the value of the hitListSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHitListSession(String value) {
        this.hitListSession = value;
    }

}
