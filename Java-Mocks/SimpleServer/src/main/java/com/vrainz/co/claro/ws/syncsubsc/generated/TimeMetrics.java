//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.07.13 a las 09:57:16 AM ART 
//


package com.vrainz.co.claro.ws.syncsubsc.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TimeMetrics.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="TimeMetrics"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Millisecond"/&gt;
 *     &lt;enumeration value="Second"/&gt;
 *     &lt;enumeration value="Minute"/&gt;
 *     &lt;enumeration value="Hour"/&gt;
 *     &lt;enumeration value="Day"/&gt;
 *     &lt;enumeration value="Week"/&gt;
 *     &lt;enumeration value="Month"/&gt;
 *     &lt;enumeration value="Year"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TimeMetrics")
@XmlEnum
public enum TimeMetrics {

    @XmlEnumValue("Millisecond")
    MILLISECOND("Millisecond"),
    @XmlEnumValue("Second")
    SECOND("Second"),
    @XmlEnumValue("Minute")
    MINUTE("Minute"),
    @XmlEnumValue("Hour")
    HOUR("Hour"),
    @XmlEnumValue("Day")
    DAY("Day"),
    @XmlEnumValue("Week")
    WEEK("Week"),
    @XmlEnumValue("Month")
    MONTH("Month"),
    @XmlEnumValue("Year")
    YEAR("Year");
    private final String value;

    TimeMetrics(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TimeMetrics fromValue(String v) {
        for (TimeMetrics c: TimeMetrics.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
