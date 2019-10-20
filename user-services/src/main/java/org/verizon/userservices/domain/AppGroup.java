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
import org.verizon.userservices.domain.enums.GroupCreationTypes;

/**
 * This class is having all the information related to the workgroup's;
 * 
 * @author Giri
 *
 */
@Entity
@Table(name = "app_group", indexes = {
    @Index(name = "idx_owner_id", columnList = "owner_id", unique = false),
    @Index(name = "idx_inhrt_group_id", columnList = "inherited_group_id", unique = false),
    @Index(name = "idx_group_name", columnList = "group_name", unique = true),
    @Index(name = "idx_group_desc", columnList = "group_desc", unique = true)
})
public class AppGroup implements Serializable {
    private static final long serialVersionUID = -2013327979003151782L;

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name", nullable = false, unique = true)
    private String groupName;

    @Column(name = "group_desc", nullable = false)
    private String groupDesc;

    @Column(name = "active_ind", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveTypes activeInd;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<AppUserGroupRole> userRoles;

    @Column(name = "inherited_group_id", nullable = true)
    private int inheritedGroupId;

    @Column(name = "owner_id", nullable = false)
    private int ownerId;

    @Column(name = "group_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupCreationTypes groupType;

    /**
     * @param groupName
     *            group name to be set
     * @param groupDesc
     *            short description of the group
     * @param ownerId
     * @return
     * @since 1.0
     * @see AppGroup
     */
    public static AppGroup createDefaultGroup(String groupName, String groupDesc, int ownerId) {
        AppGroup group = new AppGroup();
        group.setActiveInd(ActiveTypes.ACTIVE);
        group.setGroupType(GroupCreationTypes.STATIC);
        group.setGroupName(groupName);
        group.setGroupDesc(groupDesc);
        group.setOwnerId(ownerId);
        return group;
    }

    /**
     * @param groupId
     * @return
     * @since 1.0
     * @see AppGroup
     */
    public static AppGroup createDummyGroup(int groupId) {
        AppGroup group = new AppGroup();
        group.setGroupId(groupId);
        return group;
    }

    /**
     * @return the groupId
     * @since 1.0
     * @see int
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     *            the groupId to set
     * @since 1.0
     * @see int
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the groupName
     * @since 1.0
     * @see String
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     *            the groupName to set
     * @since 1.0
     * @see String
     */
    public void setGroupName(String groupName) {
        if (null != groupName) {
            this.groupName = groupName.toUpperCase().trim();
        }
        this.groupName = groupName;
    }

    /**
     * @return the groupDesc
     * @since 1.0
     * @see String
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * @param groupDesc
     *            the groupDesc to set
     * @since 1.0
     * @see String
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
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
     * @return the userRoles
     * @since 1.0
     * @see Set<AppUserGroupRole>
     */
    public Set<AppUserGroupRole> getUserRoles() {
        return userRoles;
    }

    /**
     * @param userRoles
     *            the userRoles to set
     * @since 1.0
     * @see Set<AppUserGroupRole>
     */
    public void setUserRoles(Set<AppUserGroupRole> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     * @param userRole
     *            the userRole to add
     * @since 1.0
     */
    @Transient
    public void addUser(AppUserGroupRole userRole) {
        if (null == this.userRoles) {
            this.userRoles = new HashSet<AppUserGroupRole>();
        }
        this.userRoles.add(userRole);
    }

    /**
     * @param originalUserIds
     *            the users to remove
     * @since 1.0
     */
    @Transient
    public void removeUsers(String... originalUserIds) {
        if (null == originalUserIds || 0 == originalUserIds.length) {
            return;
        }
        if (null != this.userRoles) {
            Iterator<AppUserGroupRole> iterator = this.userRoles.iterator();
            while (iterator.hasNext()) {
                AppUserGroupRole userRole = iterator.next();
                for (String originalUserId : originalUserIds) {
                    if (originalUserId.equals(userRole.getUser().getOriginalUserId())) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    /**
     * @return the inheritedGroupId
     * @since 1.0
     * @see int
     */
    public int getInheritedGroupId() {
        return inheritedGroupId;
    }

    /**
     * @param inheritedGroupId
     *            the inheritedGroupId to set
     * @since 1.0
     * @see int
     */
    public void setInheritedGroupId(int inheritedGroupId) {
        this.inheritedGroupId = inheritedGroupId;
    }

    /**
     * @return the ownerId
     * @since 1.0
     * @see int
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     *            the ownerId to set
     * @since 1.0
     * @see int
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return the groupType
     * @since 1.0
     * @see GroupCreationTypes
     */
    public GroupCreationTypes getGroupType() {
        return groupType;
    }

    /**
     * @param groupType
     *            the groupType to set
     * @since 1.0
     * @see GroupCreationTypes
     */
    public void setGroupType(GroupCreationTypes groupType) {
        this.groupType = groupType;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Group [ " + groupName + " ( " + inheritedGroupId + " ) : " + activeInd + " | users " + (null != userRoles ? userRoles.size() : 0) + " ]";
    }
}
