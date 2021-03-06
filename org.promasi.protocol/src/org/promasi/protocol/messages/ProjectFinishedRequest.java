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
import org.promasi.game.model.generated.ProjectModel;
import org.promasi.protocol.client.Protocol;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "projectFinishedRequest", propOrder = {
    "project"
})
public class ProjectFinishedRequest
        extends Message {

    protected ProjectModel project;

    public ProjectFinishedRequest() {
    }

    public ProjectFinishedRequest(ProjectModel project) {
        this.project = project;
    }

    @Override
    public Message dispatch(Protocol protocol) {
        return protocol.dispatch(this);
    }

    /**
     * Gets the value of the project property.
     *
     * @return possible object is {@link ProjectFinishedRequest.Project }
     *
     */
    public ProjectModel getProject() {
        return project;
    }

    /**
     * Sets the value of the project property.
     *
     * @param value allowed object is {@link ProjectFinishedRequest.Project }
     *
     */
    public void setProject(ProjectModel value) {
        this.project = value;
    }
}
