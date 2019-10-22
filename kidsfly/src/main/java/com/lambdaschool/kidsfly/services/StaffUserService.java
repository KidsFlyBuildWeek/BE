package com.lambdaschool.kidsfly.services;

import com.lambdaschool.kidsfly.models.StaffUser;

import java.util.List;

public interface StaffUserService {
    List<StaffUser> findAll();

    StaffUser findStaffUserById(long id);

    StaffUser save(StaffUser staffuser);

    StaffUser update(StaffUser staffuser, long id);
}
