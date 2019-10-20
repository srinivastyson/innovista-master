package org.verizon.userservices.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.verizon.userservices.domain.enums.AuditTypes;

/**
 * Abstract Class allowing all auditing functionality.
 * 
 * @author Giri
 *
 */
public class AppAuditer implements Serializable {
    private static final long serialVersionUID = -5748193459507528739L;

    @Id
    @GeneratedValue
    @Column(name = "audit_id")
    private int auditId;

    @Column(name = "created_id", nullable = false)
    private int createdId;

    @Column(name = "created_dt", nullable = false)
    @Type(type = "timestamp")
    private Calendar created;

    @Column(name = "audit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuditTypes auditType;

    @Column(name = "change_type", nullable = false)
    private String changeType;

    @Transient
    private Class<? extends Auditable> changeTypeClazz;

    @Column(name = "change_desc", nullable = false)
    private String changeDescription;

    /**
     * @param createdId
     *            the user id used to create the auditable record
     * @param clazz
     *            the type of the auditable record
     * @param desc
     *            change description for the auditable record
     * @param type
     *            the audit action performed on the record
     * @return
     * @since 1.0
     * @see AppAuditer
     */
    public static AppAuditer createAudit(int createdId, Class<? extends Auditable> clazz, String desc, AuditTypes type) {
        AppAuditer audit = new AppAuditer();
        audit.setCreatedId(createdId);
        audit.setCreated(Calendar.getInstance());
        audit.setChangeTypeClazz(clazz);
        audit.setChangeDescription(desc);
        audit.setAuditType(type);
        return audit;
    }

    /**
     * @return the auditId
     * @since 1.0
     * @see int
     */
    public int getAuditId() {
        return auditId;
    }

    /**
     * @param auditId
     *            the auditId to set
     * @since 1.0
     * @see int
     */
    @SuppressWarnings({
        "unused"
    })
    private void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    /**
     * @return the createdId
     * @since 1.0
     * @see int
     */
    public int getCreatedId() {
        return createdId;
    }

    /**
     * @param createdId
     *            the createdId to set
     * @since 1.0
     * @see int
     */
    public void setCreatedId(int createdId) {
        this.createdId = createdId;
    }

    /**
     * @return the auditType
     * @since 1.0
     * @see AuditTypes
     */
    public AuditTypes getAuditType() {
        return auditType;
    }

    /**
     * @param auditType
     *            the auditType to set
     * @since 1.0
     * @see AuditTypes
     */
    public void setAuditType(AuditTypes auditType) {
        this.auditType = auditType;
    }

    /**
     * @return the created
     * @since 1.0
     * @see Calendar
     */
    public Calendar getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     * @since 1.0
     * @see Calendar
     */
    public void setCreated(Calendar created) {
        this.created = created;
    }

    /**
     * @return the changeType
     * @since 1.0
     * @see String
     */
    public String getChangeType() {
        return changeType;
    }

    /**
     * @param changeType
     *            the changeType to set
     * @since 1.0
     * @see String
     */
    private void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    /**
     * @param changeTypeClazz
     *            the changeTypeClazz to set
     * @since 1.0
     * @see Class<? extends Auditable>
     */
    @Transient
    public void setChangeTypeClazz(Class<? extends Auditable> changeTypeClazz) {
        this.changeTypeClazz = changeTypeClazz;
        this.setChangeType(changeTypeClazz.getSimpleName().toUpperCase());
    }

    /**
     * @return the changeDescription
     * @since 1.0
     * @see String
     */
    public String getChangeDescription() {
        return changeDescription;
    }

    /**
     * @param changeDescription
     *            the changeDescription to set
     * @since 1.0
     * @see String
     */
    public void setChangeDescription(String changeDescription) {
        this.changeDescription = changeDescription;
    }

}
