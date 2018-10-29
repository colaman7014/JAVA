
package com.sas.wsdl.swiftCheckReport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sas.wsdl.swiftCheckReport package. 
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

    private final static QName _SwiftCheckReportInputBean_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftCheckReportInputBean");
    private final static QName _SwiftCheckReportResponse_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftCheckReportResponse");
    private final static QName _SwiftCheckReport_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftCheckReport");
    private final static QName _SwiftCheckReportOutput_QNAME = new QName("http://swiftCheck.webservice.sas.com/", "SwiftCheckReportOutput");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sas.wsdl.swiftCheckReport
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SwiftCheckReportOutputBean }
     * 
     */
    public SwiftCheckReportOutputBean createSwiftCheckReportOutputBean() {
        return new SwiftCheckReportOutputBean();
    }

    /**
     * Create an instance of {@link SwiftCheckReport }
     * 
     */
    public SwiftCheckReport createSwiftCheckReport() {
        return new SwiftCheckReport();
    }

    /**
     * Create an instance of {@link SwiftCheckReportInputBean }
     * 
     */
    public SwiftCheckReportInputBean createSwiftCheckReportInputBean() {
        return new SwiftCheckReportInputBean();
    }

    /**
     * Create an instance of {@link SwiftCheckReportResponse }
     * 
     */
    public SwiftCheckReportResponse createSwiftCheckReportResponse() {
        return new SwiftCheckReportResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftCheckReportInputBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftCheckReportInputBean")
    public JAXBElement<SwiftCheckReportInputBean> createSwiftCheckReportInputBean(SwiftCheckReportInputBean value) {
        return new JAXBElement<SwiftCheckReportInputBean>(_SwiftCheckReportInputBean_QNAME, SwiftCheckReportInputBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftCheckReportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftCheckReportResponse")
    public JAXBElement<SwiftCheckReportResponse> createSwiftCheckReportResponse(SwiftCheckReportResponse value) {
        return new JAXBElement<SwiftCheckReportResponse>(_SwiftCheckReportResponse_QNAME, SwiftCheckReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftCheckReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftCheckReport")
    public JAXBElement<SwiftCheckReport> createSwiftCheckReport(SwiftCheckReport value) {
        return new JAXBElement<SwiftCheckReport>(_SwiftCheckReport_QNAME, SwiftCheckReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SwiftCheckReportOutputBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://swiftCheck.webservice.sas.com/", name = "SwiftCheckReportOutput")
    public JAXBElement<SwiftCheckReportOutputBean> createSwiftCheckReportOutput(SwiftCheckReportOutputBean value) {
        return new JAXBElement<SwiftCheckReportOutputBean>(_SwiftCheckReportOutput_QNAME, SwiftCheckReportOutputBean.class, null, value);
    }

}
