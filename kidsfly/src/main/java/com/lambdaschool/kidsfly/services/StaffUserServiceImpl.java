package com.lambdaschool.kidsfly.services;

import com.lambdaschool.kidsfly.models.StaffUser;
import com.lambdaschool.kidsfly.models.Trip;
import com.lambdaschool.kidsfly.repos.StaffUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaffUserServiceImpl implements StaffUserService {
    @Autowired
    private StaffUserRepository staffuserrepos;

    @Override
    public List<StaffUser> findAll() {
        List<StaffUser> rtnList = new ArrayList<>();
        staffuserrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public StaffUser findStaffById(long id) {
        return staffuserrepos.findById(id).orElseThrow(()->new EntityNotFoundException("Id" + id));
    }


    // String name, String status, String birthdate, String location
    @Override
    public StaffUser save(StaffUser staffuser) {
        StaffUser newStaffUser = new StaffUser();

        newStaffUser.setName(staffuser.getName());
        newStaffUser.setBirthdate(staffuser.getBirthdate());
        newStaffUser.setLocation(staffuser.getLocation());
        newStaffUser.setStatus("pending");


        return staffuserrepos.save(newStaffUser);
    }

    @Override
    public StaffUser update(StaffUser staffuser, long id) {
        return null;
    }
}
