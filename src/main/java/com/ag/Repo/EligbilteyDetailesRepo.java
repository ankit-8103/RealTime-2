package com.ag.Repo;

import com.ag.Entatiy.EligbilteyDetailes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EligbilteyDetailesRepo extends JpaRepository<EligbilteyDetailes,Integer> {
    @Query("Select distince(planeName) from EligbilteyDetailes")
    public List<String> findPlaneName();
    @Query("Select distince(planeStatus) from EligbilteyDetailes")
    public  List<String> findPlaneStatus();
}
