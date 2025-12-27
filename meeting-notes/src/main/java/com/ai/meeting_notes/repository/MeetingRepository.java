package com.ai.meeting_notes.repository;

import com.ai.meeting_notes.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
