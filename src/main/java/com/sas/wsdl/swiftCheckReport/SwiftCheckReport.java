
package com.sas.wsdl.swiftCheckReport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SwiftCheckReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SwiftCheckReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://swiftCheck.webservice.sas.com/}swiftCheckReportInputBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SwiftCheckReport", propOrder = {
    "arg0"
})
public class SwiftCheckReport {

    protected SwiftCheckReportInputBean arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link SwiftCheckReportInputBean }
     *     
     */
    public SwiftCheckReportInputBean getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SwiftCheckReportInputBean }
     *     
     */
    public void setArg0(SwiftCheckReportInputBean value) {
        this.arg0 = value;
    }

}
