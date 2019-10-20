package org.verizon.userservices.service;

import java.util.List;

import org.verizon.userservices.domain.AppUser;
import org.verizon.userservices.domain.AppGroup;

public interface UserService {

    void registerUser(AppUser user);

    List<AppUser> getAllUsers(int wrkgrpId);

    AppUser getUserById(int userId);

    AppUser getUserByName(String name);

    void deleteUser(int userId);

    AppUser getUserByGarmLevel(int userId);
    // getSuperviserByUserId()
    // getSuperviserByUserName()
    // getUserGsam()
    // getUserPrevileges()
    // modifyUser()
    // decommissionUser()
}
