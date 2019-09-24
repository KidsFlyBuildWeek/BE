package com.lambdaschool.kidsfly;

import com.lambdaschool.kidsfly.models.ParentUser;
import com.lambdaschool.kidsfly.models.StaffUser;
import com.lambdaschool.kidsfly.models.Trip;
import com.lambdaschool.kidsfly.services.ParentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SeedData implements CommandLineRunner {
    @Autowired
    ParentUserService parentUserService;

    @Override
    public void run(String[] args) throws Exception {
        // String email, String name, String address, String airport
        // String date, int passengercount, String luggagetype, ParentUser parentuser
        // String name, String status, List<Trip> trips

        StaffUser frank = new StaffUser("Frank Skeever", "approved");


        String parent1Name = "Rachael Lewandowski";
        ParentUser p1 = new ParentUser("rlew@email.com", parent1Name, "123 Main St", "IND");
        p1.getTrips().add(new Trip("9/23/2019", 4, "carryon", p1));

        parentUserService.save(p1);

        //

        String parent2Name = "Carol Pritchard";
        ParentUser p2 = new ParentUser("cpritchardw@email.com", parent2Name, "123 Seymour Ave", "ORD");
        p2.getTrips().add(new Trip("11/11/2019", 2, "checked", p2));
        p2.getTrips().add(new Trip("01/23/2020", 1, "carryon", p2));

        parentUserService.save(p2);
    }
}
