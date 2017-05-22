package com.kaab.proyecto.db.controller.exceptions;

import java.util.ArrayList;
import java.util.List;
/**
 * @author esponjoso
 */
public class IllegalOrphanException extends Exception {
    /**
     * Mensajes.
     */
    private List<String> messages;
    /**
     * @param messagesIOE mensajes.
     */
    public IllegalOrphanException(final List<String> messagesIOE) {
      super((messagesIOE != null
              && 0 <= messagesIOE.size() ? messagesIOE.get(0) : null));
        if (messagesIOE == null) {
            this.messages = new ArrayList<String>();
        } else {
            this.messages = messagesIOE;
        }
    }
    /**
     * @return  messages.
     */
    public final List<String> getMessages() {
        return messages;
    }
}
