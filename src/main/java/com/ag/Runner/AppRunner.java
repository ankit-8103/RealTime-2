package com.ag.Runner;

import com.ag.Entatiy.EligbilteyDetailes;
import com.ag.Repo.EligbilteyDetailesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class AppRunner implements ApplicationRunner {
    @Autowired
    private EligbilteyDetailesRepo repo;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        EligbilteyDetailes entity1 = new EligbilteyDetailes();
       entity1.setEligId("1");
       entity1.setName("Ankit");
       entity1.setNumber(810315508L);
       entity1.setGender('M');
       entity1.setPlaneName("SNAP");
       entity1.setPlaneStatus("Approved");
       entity1.setSsn(25477L);
       repo.save(entity1);

        EligbilteyDetailes entity2 = new EligbilteyDetailes();
        entity2 .setEligId("2");
        entity2 .setName("Ajay");
        entity1.setNumber(7987896071L);
        entity2 .setGender('M');
        entity2 .setPlaneName("Rang");
        entity2 .setPlaneStatus("denied");
        entity2 .setSsn(254774L);
        repo.save( entity2 );


        EligbilteyDetailes entity3 = new EligbilteyDetailes();
        entity3 .setEligId("3");
        entity3 .setName("Rohan");
        entity3 .setNumber(9131505585L);
        entity3 .setGender('M');
        entity3 .setPlaneName("Party");
        entity3 .setPlaneStatus("Final");
        entity3 .setSsn(25477L);
        repo.save( entity3 );


        EligbilteyDetailes entity4 = new EligbilteyDetailes();
        entity4.setEligId("4");
        entity4 .setName("Aryan");
        entity4 .setNumber(7000270580L);
        entity4 .setGender('M');
        entity4 .setPlaneName("Club");
        entity4 .setPlaneStatus("Approve");
        entity4 .setSsn(2547L);
        repo.save( entity4 );


    }
}
