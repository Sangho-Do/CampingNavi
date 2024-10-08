package com.demo.campingnavi.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.campingnavi.domain.Camp;
import com.demo.campingnavi.domain.ChatRoom;

// import 생략....

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    @Query("SELECT c FROM ChatRoom c WHERE c.campName LIKE %:campName% OR c.name LIKE %:campName%")
    List<ChatRoom> findByCampNameContaining(@Param("campName") String campName);

    @Query("SELECT DISTINCT camp FROM Camp camp, ChatRoom chatRoom " +
            "WHERE chatRoom.camp = camp ")
    List<Camp> getCampListExistingChatRoom();
    List<ChatRoom> findByUserListContains(String memberMseq);
    List<ChatRoom> findByCampNameContainingAndPurposeIn(String campName, String[] purposes);
}