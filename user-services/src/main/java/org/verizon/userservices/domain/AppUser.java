package org.verizon.userservices.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.verizon.userservices.domain.enums.ActiveTypes;
import org.verizon.userservices.domain.enums.YesNoOption;

/**
 * This class is having all the information related to AppUser.
 * 
 * @author Giri
 *
 */
@Entity
@Table(name = "app_user", indexes = {
    @Index(name = "idx_original_id", columnList = "original_id", unique = true),
    @Index(name = "idx_inhrt_user_id", columnList = "inherited_user_id", unique = false),
    @Index(name = "idx_supervisor_id", columnList = "supervisor_id", unique = false),
    @Index(name = "idx_email_id", columnList = "email_id", unique = true)
})
public class AppUser implements Serializable {
    private static final long serialVersionUID = -4987157989535306815L;

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int userId;

    @Column(name = "original_id", unique = true, nullable = false)
    private String originalUserId;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "email_id", unique = true, nullable = false)
    private String emailId;

    @Column(name = "active_ind", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveTypes activeInd;

    @Column(name = "src_system", nullable = false)
    private String srcSystem;

    @Column(name = "is_synced", nullable = false)
    @Enumerated(EnumType.STRING)
    private YesNoOption isSynced;

    @Column(name = "is_system_acc", nullable = false)
    @Enumerated(EnumType.STRING)
    private YesNoOption isSystemAccount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<AppUserGroupRole> groupRoles;

    @Column(name = "inherited_user_id", nullable = false)
    private int inheritedUserId;

    @Column(name = "supervisor_id", nullable = false)
    private int supervisorId;

    @Transient
    private Set<AppUser> reportees;

    /**
     * @param originalUserId
     * @param emailId
     * @return
     * @since  1.0
     * @see AppUser
     */
    public static AppUser createDefaultUser(String originalUserId, String emailId) {
        AppUser user = new AppUser();
        user.setOriginalUserId(originalUserId);
        user.setEmailId(emailId);
        user.setIsSynced(YesNoOption.N);
        user.setIsSystemAccount(YesNoOption.N);
        user.setActiveInd(ActiveTypes.ACTIVE);
        user.setSrcSystem("SYSTEM");
        user.setInheritedUserId(0);
        user.setSupervisorId(0);
        return user;
    }

    /**
     * @param userId
     * @since  1.0
     * @see void
     */
    public static AppUser createDummyUser(int userId) {
        AppUser user = new AppUser();
        user.setUserId(userId);
        return user;
    }

    /**
     * @return the userId
     * @since 1.0
     * @see int
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     * @since 1.0
     * @see int
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the originalUserId
     * @since 1.0
     * @see String
     */
    public String getOriginalUserId() {
        return originalUserId;
    }

    /**
     * @param originalUserId
     *            the originalUserId to set
     * @since 1.0
     * @see String
     */
    public void setOriginalUserId(String originalUserId) {
        this.originalUserId = originalUserId;
    }

    /**
     * @return the firstName
     * @since 1.0
     * @see String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     * @since 1.0
     * @see String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     * @since 1.0
     * @see String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     * @since 1.0
     * @see String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailId
     * @since 1.0
     * @see String
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId
     *            the emailId to set
     * @since 1.0
     * @see String
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return returns <i>true</i> if the user is active else returns
     *         <i>false</i>
     * @since 1.0
     * @see boolean
     */
    @Transient
    public boolean isActive() {
        return (ActiveTypes.ACTIVE.equals(activeInd));
    }

    /**
     * @return the activeInd
     * @since 1.0
     * @see ActiveTypes
     */
    public ActiveTypes getActiveInd() {
        return activeInd;
    }

    /**
     * @param activeInd
     *            the activeInd to set
     * @since 1.0
     * @see ActiveTypes
     */
    public void setActiveInd(ActiveTypes activeInd) {
        this.activeInd = activeInd;
    }

    /**
     * @return the srcSystem
     * @since 1.0
     * @see String
     */
    public String getSrcSystem() {
        return srcSystem;
    }

    /**
     * @param srcSystem
     *            the srcSystem to set
     * @since 1.0
     * @see String
     */
    public void setSrcSystem(String srcSystem) {
        this.srcSystem = srcSystem;
    }

    /**
     * @return returns <i>true</i> if the user data is syunchronized with the
     *         source system, mentioned as <i>srcSystem</i> here
     * @since 1.0
     * @see boolean
     */
    @Transient
    public boolean isSynced() {
        return (YesNoOption.Y.equals(this.isSynced));
    }

    /**
     * @return the isSynced
     * @since 1.0
     * @see YesNoOption
     */
    public YesNoOption getIsSynced() {
        return isSynced;
    }

    /**
     * Sets the synced variable as true.
     * 
     * @since 1.0
     */
    @Transient
    public void setSynced() {
        this.isSynced = YesNoOption.Y;
    }

    /**
     * @param isSynced
     *            the isSynced to set
     * @since 1.0
     * @see YesNoOption
     */
    public void setIsSynced(YesNoOption isSynced) {
        this.isSynced = isSynced;
    }

    /**
     * @return returns <i>true</i> if the user is actually a system user else
     *         returns <i>false</i>
     * @since 1.0
     */
    public boolean isSystemAccount() {
        return (YesNoOption.Y.equals(this.isSystemAccount));
    }

    /**
     * @return the isSystemAccount
     * @since 1.0
     * @see YesNoOption
     */
    public YesNoOption getIsSystemAccount() {
        return isSystemAccount;
    }

    /**
     * Sets the user to be a system account.
     * 
     * @since 1.0
     */
    @Transient
    public void setSystemAccount() {
        this.isSystemAccount = YesNoOption.Y;
    }

    /**
     * @param isSystemAccount
     *            the isSystemAccount to set
     * @since 1.0
     * @see YesNoOption
     */
    public void setIsSystemAccount(YesNoOption isSystemAccount) {
        this.isSystemAccount = isSystemAccount;
    }

    /**
     * @return the groupRoles
     * @since 1.0
     * @see AppUserGroupRole
     */
    public Set<AppUserGroupRole> getGroups() {
        return groupRoles;
    }

    /**
     * @param groupRoles
     *            the groupRoles to set
     * @since 1.0
     * @see AppUserGroupRole
     */
    public void setGroups(Set<AppUserGroupRole> groupRoles) {
        this.groupRoles = groupRoles;
    }

    /**
     * @param groupRole
     *            the groupRole to add
     * @since 1.0
     */
    @Transient
    public void addGroup(AppUserGroupRole groupRole) {
        if (null == this.groupRoles) {
            this.groupRoles = new HashSet<AppUserGroupRole>();
        }
        this.groupRoles.add(groupRole);
    }

    /**
     * @param groupRoles
     *            the groupRoles to remove
     * @since 1.0
     */
    @Transient
    public void removeGroups(String... groupNames) {
        if (null == groupNames || 0 == groupNames.length) {
            return;
        }
        if (null != this.groupRoles) {
            Iterator<AppUserGroupRole> iterator = this.groupRoles.iterator();
            while (iterator.hasNext()) {
                AppUserGroupRole groupRole = iterator.next();
                for (String groupName : groupNames) {
                    if (groupName.equals(groupRole.getGroup().getGroupName())) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    /**
     * @return the inheritedUserId
     * @since 1.0
     * @see int
     */
    public int getInheritedUserId() {
        return inheritedUserId;
    }

    /**
     * @param inheritedUserId
     *            the inheritedUserId to set
     * @since 1.0
     * @see int
     */
    public void setInheritedUserId(int inheritedUserId) {
        this.inheritedUserId = inheritedUserId;
    }

    /**
     * @return the supervisorId
     * @since 1.0
     * @see int
     */
    public int getSupervisorId() {
        return supervisorId;
    }

    /**
     * @param supervisorId
     *            the supervisorId to set
     * @since 1.0
     * @see int
     */
    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    /**
     * @return the reportees
     * @since 1.0
     * @see Set<AppUser>
     */
    @Transient
    public Set<AppUser> getReportees() {
        return reportees;
    }

    /**
     * @param reportees
     *            the reportees to set
     * @since 1.0
     * @see Set<AppUser>
     */
    @Transient
    public void setReportees(Set<AppUser> reportees) {
        this.reportees = reportees;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [ " + originalUserId + " ( " + inheritedUserId + " ) : " + activeInd + " ]";
    }
}
