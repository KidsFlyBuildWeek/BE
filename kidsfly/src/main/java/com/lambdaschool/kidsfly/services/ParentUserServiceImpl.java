package com.lambdaschool.kidsfly.services;

import com.lambdaschool.kidsfly.models.ParentUser;
import com.lambdaschool.kidsfly.models.Trip;
import com.lambdaschool.kidsfly.repos.ParentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "parentUserService")
public class ParentUserServiceImpl implements ParentUserService{
    @Autowired
    private ParentUserRepository parentuserrepos;

    @Override
    public List<ParentUser> findAll() {
        List<ParentUser> rtnList = new ArrayList<>();
        parentuserrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public ParentUser findParentUserById(long id) {

        return parentuserrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id " + id));
    }

    @Transactional
    @Override
    public ParentUser save(ParentUser parentuser) {
        ParentUser newParentUser = new ParentUser();

        newParentUser.setEmail(parentuser.getEmail());
        newParentUser.setName(parentuser.getName());
        newParentUser.setAddress(parentuser.getAddress());
        newParentUser.setAirport(parentuser.getAirport());
        newParentUser.setStatus("pending");

        return parentuserrepos.save(newParentUser);
    }

    @Override
    public ParentUser update(ParentUser parentuser, long id) {

        ParentUser currentParentUser = parentuserrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (parentuser.getEmail() != null){
            currentParentUser.setEmail(parentuser.getEmail());
        }

        if (parentuser.getName() != null){
            currentParentUser.setName(parentuser.getName());
        }

        if (parentuser.getAddress() != null){
            currentParentUser.setAddress(parentuser.getAddress());
        }

        if (parentuser.getAirport() != null){
            currentParentUser.setAirport(parentuser.getAirport());
        }

        if (parentuser.getStatus() != null){
            currentParentUser.setStatus(parentuser.getStatus());
        }

        if (parentuser.getTrips().size() > 0 ){
            for (Trip t : parentuser.getTrips())
            {
                currentParentUser.getTrips().add(new Trip(t.getDate(), t.getPassengercount(), t.getLuggagetype(), currentParentUser));
            }
        }

        return parentuserrepos.save(currentParentUser);
    }
}
