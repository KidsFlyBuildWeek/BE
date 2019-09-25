package com.lambdaschool.kidsfly.services;

import com.lambdaschool.kidsfly.models.ParentUser;

import java.util.List;

public interface ParentUserService {
    List<ParentUser> findAll();

    ParentUser findParentUserById(long id);

    ParentUser save(ParentUser parentuser);

    ParentUser update(ParentUser parentuser, long id);
}
