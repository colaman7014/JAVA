
package com.sas.wsdl.swiftcheck;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;


/**
 * <p>Java class for SwiftCheck complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SwiftCheck">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://swiftCheck.webservice.sas.com/}swiftCheckInputBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SwiftCheck", propOrder = {
    "arg0"
})
public class SwiftCheck {

    protected SwiftCheckInputBean arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link SwiftCheckInputBean }
     *     
     */
    public SwiftCheckInputBean getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SwiftCheckInputBean }
     *     
     */
    public void setArg0(SwiftCheckInputBean value) {
        this.arg0 = value;
    }

}
