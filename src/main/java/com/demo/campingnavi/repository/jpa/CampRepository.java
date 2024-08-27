package com.demo.campingnavi.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.campingnavi.domain.Camp;

public interface CampRepository extends JpaRepository<Camp, Integer> {
    Camp findFirstByOrderByCseqDesc();

    Optional<Camp> findCampByContentId(String contentId);

    @Query("SELECT camp FROM Camp camp " +
            "WHERE camp.useyn LIKE %:useyn% " +
            "AND camp.name LIKE %:name% " +
            "AND camp.locationB LIKE %:locationB% " +
            "AND camp.locationS LIKE %:locationS% " +
            "INTERSECT " +
            "SELECT camp FROM Camp camp " +
            "WHERE camp.campType LIKE %:campType1% " +
            "OR camp.campType LIKE %:campType2% " +
            "OR camp.campType LIKE %:campType3% " +
            "OR camp.campType LIKE %:campType4% ")
    List<Camp> getCampList(
    		@Param("useyn") String useyn, @Param("name") String name, 
    		@Param("locationB") String locationB, @Param("locationS") String locationS,
    		@Param("campType1") String campType1, @Param("campType2") String campType2, 
    		@Param("campType3") String campType3, @Param("campType4") String campType4);

    List<Camp> findByNameContaining(String name);

    @Query("SELECT c FROM Camp c WHERE c.name LIKE %:keyword%")
    List<Camp> searchCamps(@Param("keyword") String keyword);

    @Query("SELECT c FROM Camp c WHERE c.name=?1")
    Camp findCampByName(String name);

    Camp findByName(String name);

    List<Camp> findByUseynContaining(String useyn);

    @Modifying
    @Query(value="UPDATE Camp SET useyn = 'n' ", nativeQuery = true)
    void campAllDisabled();
}