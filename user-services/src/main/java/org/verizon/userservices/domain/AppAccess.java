package org.verizon.userservices.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.verizon.userservices.domain.enums.AccessRelationTypes;

/**
 * This class is having all the information related to Access Levels.
 * 
 * Access Levels can be a combination of all the fields defined in this class or
 * could a derivation thereof.
 * 
 * @author Giri
 */
@Entity
@Table(name = "app_access")
public class AppAccess implements Serializable {
    private static final long serialVersionUID = -2141987549588390318L;

    @Id
    @GeneratedValue
    @Column(name = "access_id")
    private int accessId;

    @Column(name = "relation_id", nullable = false)
    private int relationId;

    @Column(name = "relation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessRelationTypes relationType;

    @Column(name = "garm_level", nullable = false)
    private String garmLevel;

    @Column(name = "gsam_level", nullable = false)
    private String gsamLevel;

    // set default value
    @Column(name = "active_ind", nullable = false, length = 1)
    private String activeInd = "A";

    /**
     * Default constructor
     * 
     * @author abhishek
     * @since 1.0
     */
    public AppAccess() {
        super();
    }

    /**
     * @return the accessId
     * @since 1.0
     * @see int
     */
    public int getAccessId() {
        return accessId;
    }

    /**
     * @param accessId
     *            the accessId to set
     * @since 1.0
     * @see int
     */
    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    /**
     * @return the relationId
     * @since 1.0
     * @see int
     */
    public int getRelationId() {
        return relationId;
    }

    /**
     * @param relationId
     *            the relationId to set
     * @since 1.0
     * @see int
     */
    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    /**
     * @return the relationType
     * @since 1.0
     * @see AccessRelationTypes
     */
    public AccessRelationTypes getRelationType() {
        return relationType;
    }

    /**
     * @param relationType
     *            the relationType to set
     * @since 1.0
     * @see AccessRelationTypes
     */
    public void setRelationType(AccessRelationTypes relationType) {
        this.relationType = relationType;
    }

    /**
     * @return the garmLevel
     * @since 1.0
     * @see String
     */
    public String getGarmLevel() {
        return garmLevel;
    }

    /**
     * @param garmLevel
     *            the garmLevel to set
     * @since 1.0
     * @see String
     */
    public void setGarmLevel(String garmLevel) {
        this.garmLevel = garmLevel;
    }

    /**
     * @return the gsamLevel
     * @since 1.0
     * @see String
     */
    public String getGsamLevel() {
        return gsamLevel;
    }

    /**
     * @param gsamLevel
     *            the gsamLevel to set
     * @since 1.0
     * @see String
     */
    public void setGsamLevel(String gsamLevel) {
        this.gsamLevel = gsamLevel;
    }

    /**
     * @return the activeInd
     * @since 1.0
     * @see String
     */
    public String getActiveInd() {
        return activeInd;
    }

    /**
     * @param activeInd
     *            the activeInd to set
     * @since 1.0
     * @see String
     */
    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AppAccess [ " + relationId + " ( " + relationType + " ) : " + activeInd + " ]";
    }
}
