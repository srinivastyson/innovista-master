package org.verizon.userservices.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.Transient;

/**
 * Abstract Class allowing all auditing functionality.
 * 
 * @author Giri
 *
 */
abstract class Auditable {
    @Transient
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * @param listener
     *            the change listener implementation to add
     * @since 1.0
     * @see void
     */
    @Transient
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * @param listener
     *            the change listener implementation to remove
     * @since 1.0
     * @see void
     */
    @Transient
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
