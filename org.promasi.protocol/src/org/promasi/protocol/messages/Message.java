//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.30 at 11:14:44 PM CEST 
//


package org.promasi.protocol.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.promasi.protocol.client.Protocol;


/**
 * <p>Java class for message complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="message">
 *   &lt;complexContent>
 *     &lt;extension base="{}serializableObject">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "message")
@XmlSeeAlso({
    GameStartedRequest.class,
    UpdateGamePlayersListRequest.class,
    GameCanceledResponse.class,
    JoinGameRequest.class,
    CreateGameResponse.class,
    StartGameResponse.class,
    OnExecuteStepRequest.class,
    CreateGameFailedResponse.class,
    JoinGameFailedResponse.class,
    JoinGameResponse.class,
    LoginFailedResponse.class,
    CreateGameRequest.class,
    LoginRequest.class,
    LeaveGameRequest.class,
    LeaveGameResponse.class,
    ProjectFinishedRequest.class,
    HireEmployeeRequest.class,
    AssignEmployeeTasksRequest.class,
    WrongProtocolResponse.class,
    CancelGameResponse.class,
    StartGameRequest.class,
    OnTickRequest.class,
    UpdateAvailableGameListRequest.class,
    GameFinishedRequest.class,
    MessageRequest.class,
    EmployeeDischargedRequest.class,
    ProjectAssignedRequest.class,
    CancelGameRequest.class,
    UpdateGameListRequest.class,
    EmployeeHiredRequest.class,
    LoginResponse.class,
    GameCanceledRequest.class,
    InternalErrorResponse.class,
    DischargeEmployeeRequest.class
})
public abstract class Message
{
    public abstract Message dispatch(Protocol proto);
}
