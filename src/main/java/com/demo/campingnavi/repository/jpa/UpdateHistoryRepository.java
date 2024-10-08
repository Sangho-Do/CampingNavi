package com.demo.campingnavi.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.campingnavi.domain.UpdateHistory;

public interface UpdateHistoryRepository extends JpaRepository<UpdateHistory, Integer> {
    List<UpdateHistory> findByKindOrderByUpdateTimeDesc(String update);

    @Query("SELECT updateHistory FROM UpdateHistory updateHistory " +
            "WHERE updateHistory.kind LIKE %:kind% " +
            "AND updateHistory.result LIKE %:result% " +
            "ORDER BY updateHistory.useq ")
    List<UpdateHistory> getUpdateHistoryList(@Param("kind") String kind, @Param("result") String result);
}
