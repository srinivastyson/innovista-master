package org.verizon.userservices.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.verizon.userservices.domain.AppGroup;

public interface UserWorkGroupServiceRepository extends JpaRepository<AppGroup, Integer >{

}
