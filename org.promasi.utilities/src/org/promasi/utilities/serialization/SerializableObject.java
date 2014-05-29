/**
 *
 */
package org.promasi.utilities.serialization;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

/**
 * @author m1cRo
 *
 */
public class SerializableObject {

    /**
     *
     * @return
     */
    public String serialize() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (XMLEncoder xmlEncoder = new XMLEncoder(outputStream)) {
            xmlEncoder.writeObject(this);
        }
        
        return outputStream.toString();
    }
}
