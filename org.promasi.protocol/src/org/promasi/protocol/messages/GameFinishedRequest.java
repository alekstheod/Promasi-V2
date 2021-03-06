//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.30 at 11:14:44 PM CEST 
//


package org.promasi.protocol.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.promasi.game.model.generated.GameModelModel;
import org.promasi.protocol.client.Protocol;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gameFinishedRequest", propOrder = {
    "clientId",
    "gameModel",
    "otherPlayersModels"
})
public class GameFinishedRequest
    extends Message
{

    protected String clientId;
    protected GameModelModel gameModel;
    @XmlElement(required = true)
    protected GameFinishedRequest.OtherPlayersModels otherPlayersModels;

    public GameFinishedRequest(){}
    public GameFinishedRequest(String clientId, GameModelModel gameModel, Map<String, GameModelModel> models) {
        this.clientId = clientId;
        this.gameModel = gameModel;
        for( Map.Entry<String, GameModelModel> entry : models.entrySet() ){
            OtherPlayersModels.Entry newEntry = new OtherPlayersModels.Entry();
            newEntry.setKey(entry.getKey());
            newEntry.setValue(entry.getValue());
            this.otherPlayersModels = new OtherPlayersModels();
            this.otherPlayersModels.getEntry().add(newEntry);
        }
    }

     @Override
    public Message dispatch(Protocol protocol) {
        return protocol.dispatch(this);
    }   
    
    /**
     * Gets the value of the clientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the value of the clientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientId(String value) {
        this.clientId = value;
    }

    /**
     * Gets the value of the gameModel property.
     * 
     * @return
     *     possible object is
     *     {@link GameFinishedRequest.GameModel }
     *     
     */
    public GameModelModel getGameModel() {
        return gameModel;
    }

    /**
     * Sets the value of the gameModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameFinishedRequest.GameModel }
     *     
     */
    public void setGameModel(GameModelModel value) {
        this.gameModel = value;
    }

    /**
     * Gets the value of the otherPlayersModels property.
     * 
     * @return
     *     possible object is
     *     {@link GameFinishedRequest.OtherPlayersModels }
     *     
     */
    public GameFinishedRequest.OtherPlayersModels getOtherPlayersModels() {
        return otherPlayersModels;
    }

    /**
     * Sets the value of the otherPlayersModels property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameFinishedRequest.OtherPlayersModels }
     *     
     */
    public void setOtherPlayersModels(GameFinishedRequest.OtherPlayersModels value) {
        this.otherPlayersModels = value;
    }

    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element ref="{}companyModel"/>
     *                             &lt;element name="gameDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="gameName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element ref="{}marketPlaceModel"/>
     *                             &lt;element ref="{}projectModel" maxOccurs="unbounded"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class OtherPlayersModels {

        protected List<GameFinishedRequest.OtherPlayersModels.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GameFinishedRequest.OtherPlayersModels.Entry }
         * 
         * 
         */
        public List<GameFinishedRequest.OtherPlayersModels.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<GameFinishedRequest.OtherPlayersModels.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element ref="{}companyModel"/>
         *                   &lt;element name="gameDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="gameName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element ref="{}marketPlaceModel"/>
         *                   &lt;element ref="{}projectModel" maxOccurs="unbounded"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected GameModelModel value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link GameFinishedRequest.OtherPlayersModels.Entry.Value }
             *     
             */
            public GameModelModel getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link GameFinishedRequest.OtherPlayersModels.Entry.Value }
             *     
             */
            public void setValue(GameModelModel value) {
                this.value = value;
            }

        }

    }

}
