package org.verizon.userservices.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.verizon.userservices.domain.enums.ActiveTypes;
import org.verizon.userservices.domain.enums.ApprovedTypes;
import org.verizon.userservices.domain.enums.RoleNames;

/**
 * This class encapsulates the Role information for the Application.
 * 
 * Roles define what the user will be allowed to do in the Application.
 * 
 * This however does not limit the user access to any data in the system. Data
 * level access is controlled by the
 * {@link org.verizon.userservices.domain.AppAccess} class.
 * 
 * @author abhishek
 * @since 1.0
 */
@Entity
@Table(name = "app_user_group_role", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "user_id",
        "group_id",
        "role_id"
    }, name = "uk_user_group_role")
}, indexes = {
    @Index(name = "idx_user_id", columnList = "user_id", unique = false),
    @Index(name = "idx_group_id", columnList = "group_id", unique = false),
    @Index(name = "idx_role_id", columnList = "role_id", unique = false)
})
public class AppUserGroupRole implements Serializable {
    private static final long serialVersionUID = 6480043829365637056L;

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    private AppGroup group;

    @JoinColumn(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleNames roleName;

    @Column(name = "active_ind", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveTypes activeInd;

    @Column(name = "approved_lvl", nullable = true)
    @Enumerated(EnumType.STRING)
    private ApprovedTypes approvedLvl;

    /**
     * @param user
     *            the user to set
     * @param group
     *            the group to set
     * @param roleName
     *            the roleName to set
     * @return
     * @since 1.0
     * @see AppUserGroupRole
     */
    public static AppUserGroupRole createDefaultRole(AppUser user, AppGroup group, RoleNames roleName) {
        AppUserGroupRole role = new AppUserGroupRole();
        role.setActiveInd(ActiveTypes.ACTIVE);
        role.setRoleName(roleName);
        role.setUser(user);
        role.setGroup(group);
        return role;
    }

    /**
     * @param userId
     *            the id of the user to set
     * @param groupId
     *            the id of the group to set
     * @param roleName
     *            the roleName to set
     * @return
     * @since 1.0
     * @see AppUserGroupRole
     */
    public static AppUserGroupRole createDefaultRole(int userId, int groupId, RoleNames roleName) {
        AppUserGroupRole role = new AppUserGroupRole();
        role.setActiveInd(ActiveTypes.ACTIVE);
        role.setRoleName(roleName);
        role.setUser(AppUser.createDummyUser(userId));
        role.setGroup(AppGroup.createDummyGroup(groupId));
        return role;
    }

    /**
     * @return the roleId
     * @since 1.0
     * @see int
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     * @since 1.0
     * @see int
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the user
     * @since 1.0
     * @see AppUser
     */
    public AppUser getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     * @since 1.0
     * @see AppUser
     */
    public void setUser(AppUser user) {
        this.user = user;
    }

    /**
     * @return the group
     * @since 1.0
     * @see AppGroup
     */
    public AppGroup getGroup() {
        return group;
    }

    /**
     * @param group
     *            the group to set
     * @since 1.0
     * @see AppGroup
     */
    public void setGroup(AppGroup group) {
        this.group = group;
    }

    /**
     * @return the roleName
     * @since 1.0
     * @see RoleNames
     */
    public RoleNames getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     *            the roleName to set
     * @since 1.0
     * @see RoleNames
     */
    public void setRoleName(RoleNames roleName) {
        this.roleName = roleName;
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
     * @author abhishek
     * @since 1.0
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Role [" + user + " : " + group + " : " + roleName + " ( " + activeInd + ") ]";
    }

}
