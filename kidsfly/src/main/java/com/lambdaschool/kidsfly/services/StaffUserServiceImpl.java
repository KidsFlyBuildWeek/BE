package com.lambdaschool.kidsfly.services;

import com.lambdaschool.kidsfly.models.StaffUser;
import com.lambdaschool.kidsfly.models.Trip;
import com.lambdaschool.kidsfly.repos.StaffUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value= "staffUserService")
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
    public StaffUser findStaffUserById(long id) {
        return staffuserrepos.findById(id).orElseThrow(()->new EntityNotFoundException("Id" + id));
    }


    // String name, String status, String birthdate, String location
    @Override
    public StaffUser save(StaffUser staffuser) {
        StaffUser newStaffUser = new StaffUser();

        newStaffUser.setUsername(staffuser.getUsername());
        newStaffUser.setPassword(staffuser.getPassword());
        newStaffUser.setPhone(staffuser.getPhone());
        newStaffUser.setEmail(staffuser.getEmail());
        newStaffUser.setName(staffuser.getName());
        newStaffUser.setBirthdate(staffuser.getBirthdate());
        newStaffUser.setLocation(staffuser.getLocation());
        newStaffUser.setStatus("pending");


        return staffuserrepos.save(newStaffUser);
    }

    // String name, String status, String birthdate, String location
    @Override
    public StaffUser update(StaffUser staffuser, long id) {
        StaffUser currentStaffUser = staffuserrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(staffuser.getUsername() != null){
            currentStaffUser.setUsername(staffuser.getUsername());
        }

        if(staffuser.getPassword() != null){
            currentStaffUser.setPassword(staffuser.getPassword());
        }

        if(staffuser.getName() != null){
            currentStaffUser.setName(staffuser.getName());
        }

        if(staffuser.getStatus() != null){
            currentStaffUser.setStatus(staffuser.getStatus());
        }

        if(staffuser.getBirthdate() != null){
            currentStaffUser.setBirthdate(staffuser.getBirthdate());
        }

        if(staffuser.getLocation() != null){
            currentStaffUser.setLocation(staffuser.getLocation());
        }

        if(staffuser.getTrips().size() > 0){
            for (Trip t : staffuser.getTrips())
            {
                currentStaffUser.getTrips().add(new Trip(t.getDate(), t.getPassengercount(), t.getChildcount(), t.getAirport(), t.getLuggagetype(), t.getParentuser(), staffuser));
            }
        }

        return staffuserrepos.save(currentStaffUser);
    }
}
