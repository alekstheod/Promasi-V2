/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.promasi.protocol.messages;

/**
 *
 * @author alekstheod
 */
public interface IMessageProcessor {
   <T extends Message> void process( T t );
}
