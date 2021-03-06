//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.30 at 11:14:44 PM CEST 
//
package org.promasi.protocol.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.promasi.protocol.client.Protocol;

/**
 * <p>
 * Java class for onTickRequest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="onTickRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}message">
 *       &lt;sequence>
 *         &lt;element name="dateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "onTickRequest", propOrder = {
    "dateTime"
})
public class OnTickRequest
        extends Message {

    protected String dateTime;

    public OnTickRequest() {
    }

    public OnTickRequest(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public Message dispatch(Protocol protocol) {
        return protocol.dispatch(this);
    }

    /**
     * Gets the value of the dateTime property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDateTime(String value) {
        this.dateTime = value;
    }

}
