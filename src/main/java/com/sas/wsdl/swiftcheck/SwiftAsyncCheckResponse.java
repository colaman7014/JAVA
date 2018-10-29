
package com.sas.wsdl.swiftcheck;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.sas.webservice.swiftCheck.bean.SwiftCheckOutputBean;


/**
 * <p>Java class for SwiftAsyncCheckResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SwiftAsyncCheckResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://swiftCheck.webservice.sas.com/}swiftCheckOutputBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SwiftAsyncCheckResponse", propOrder = {
    "_return"
})
public class SwiftAsyncCheckResponse {

    @XmlElement(name = "return")
    protected SwiftCheckOutputBean _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link SwiftCheckOutputBean }
     *     
     */
    public SwiftCheckOutputBean getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link SwiftCheckOutputBean }
     *     
     */
    public void setReturn(SwiftCheckOutputBean value) {
        this._return = value;
    }

}
