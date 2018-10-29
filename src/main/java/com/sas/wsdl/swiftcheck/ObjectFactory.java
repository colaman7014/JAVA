
package com.sas.wsdl.swiftcheck;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.sas.webservice.swiftCheck.bean.SwiftCheckInputBean;
import com.sas.webservice.swiftCheck.bean.SwiftCheckOutputBean;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sas.wsdl.swiftcheck package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SwiftAsyncCheckResponse_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftAsyncCheckResponse");
    private final static QName _SwiftCheckResponse_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftCheckResponse");
    private final static QName _Aml_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "aml");
    private final static QName _SwiftCheck_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftCheck");
    private final static QName _SwiftAsyncCheck_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftAsyncCheck");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sas.wsdl.swiftcheck
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SwiftAsyncCheck }
     * 
     */
    public SwiftAsyncCheck createSwiftAsyncCheck() {
        return new SwiftAsyncCheck();
    }

    /**
     * Create an instance of {@link SwiftCheck }
     * 
     */
    public SwiftCheck createSwiftCheck() {
        return new SwiftCheck();
    }

    /**
     * Create an instance of {@link SwiftCheckResponse }
     * 
     */
    public SwiftCheckResponse createSwiftCheckResponse() {
        return new SwiftCheckResponse();
    }

    /**
     * Create an instance of {@link SwiftAsyncCheckResponse }
     * 
     */
    public SwiftAsyncCheckResponse createSwiftAsyncCheckResponse() {
        return new SwiftAsyncCheckResponse();
    }

    /**
     * Create an instance of {@link SwiftCheckInputBean }
     * 
     */
    public SwiftCheckInputBean createSwiftCheckInputBean() {
        return new SwiftCheckInputBean();
    }

    /**
     * Create an instance of {@link SwiftCheckOutputBean }
     * 
     */
    public SwiftCheckOutputBean createSwiftCheckOutputBean() {
        return new SwiftCheckOutputBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftAsyncCheckResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftAsyncCheckResponse")
    public JAXBElement<SwiftAsyncCheckResponse> createSwiftAsyncCheckResponse(SwiftAsyncCheckResponse value) {
        return new JAXBElement<SwiftAsyncCheckResponse>(_SwiftAsyncCheckResponse_QNAME, SwiftAsyncCheckResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftCheckResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftCheckResponse")
    public JAXBElement<SwiftCheckResponse> createSwiftCheckResponse(SwiftCheckResponse value) {
        return new JAXBElement<SwiftCheckResponse>(_SwiftCheckResponse_QNAME, SwiftCheckResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "aml")
    public JAXBElement<Object> createAml(Object value) {
        return new JAXBElement<Object>(_Aml_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftCheck }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftCheck")
    public JAXBElement<SwiftCheck> createSwiftCheck(SwiftCheck value) {
        return new JAXBElement<SwiftCheck>(_SwiftCheck_QNAME, SwiftCheck.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftAsyncCheck }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftAsyncCheck")
    public JAXBElement<SwiftAsyncCheck> createSwiftAsyncCheck(SwiftAsyncCheck value) {
        return new JAXBElement<SwiftAsyncCheck>(_SwiftAsyncCheck_QNAME, SwiftAsyncCheck.class, null, value);
    }

}
