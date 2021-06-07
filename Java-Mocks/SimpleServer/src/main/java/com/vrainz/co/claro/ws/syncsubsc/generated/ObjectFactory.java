//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.07.13 a las 09:57:16 AM ART 
//


package com.vrainz.co.claro.ws.syncsubsc.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vrainz.co.claro.ws.syncsubsc.generated package. 
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

    private final static QName _ServiceException_QNAME = new QName("http://www.csapi.org/schema/parlayx/common/v2_1", "ServiceException");
    private final static QName _PolicyException_QNAME = new QName("http://www.csapi.org/schema/parlayx/common/v2_1", "PolicyException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vrainz.co.claro.ws.syncsubsc.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserID }
     * 
     */
    public UserID createUserID() {
        return new UserID();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link NamedParameterList }
     * 
     */
    public NamedParameterList createNamedParameterList() {
        return new NamedParameterList();
    }

    /**
     * Create an instance of {@link NamedParameter }
     * 
     */
    public NamedParameter createNamedParameter() {
        return new NamedParameter();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link PolicyException }
     * 
     */
    public PolicyException createPolicyException() {
        return new PolicyException();
    }

    /**
     * Create an instance of {@link TimeMetric }
     * 
     */
    public TimeMetric createTimeMetric() {
        return new TimeMetric();
    }

    /**
     * Create an instance of {@link ChargingInformation }
     * 
     */
    public ChargingInformation createChargingInformation() {
        return new ChargingInformation();
    }

    /**
     * Create an instance of {@link SimpleReference }
     * 
     */
    public SimpleReference createSimpleReference() {
        return new SimpleReference();
    }

    /**
     * Create an instance of {@link ServiceError }
     * 
     */
    public ServiceError createServiceError() {
        return new ServiceError();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/common/v2_1", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicyException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/parlayx/common/v2_1", name = "PolicyException")
    public JAXBElement<PolicyException> createPolicyException(PolicyException value) {
        return new JAXBElement<PolicyException>(_PolicyException_QNAME, PolicyException.class, null, value);
    }

}
