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
 * <p>Java class for createGameFailedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createGameFailedResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}message">
 *       &lt;sequence>
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
@XmlType(name = "createGameFailedResponse")
public class CreateGameFailedResponse
    extends Message
{
    @Override
    public Message dispatch(Protocol protocol) {
        return protocol.dispatch(this);
    }

}
