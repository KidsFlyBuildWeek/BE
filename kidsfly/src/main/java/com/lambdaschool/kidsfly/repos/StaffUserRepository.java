package com.lambdaschool.kidsfly.repos;

import com.lambdaschool.kidsfly.models.StaffUser;
import org.springframework.data.repository.CrudRepository;

public interface StaffUserRepository extends CrudRepository<StaffUser, Long> {
}
