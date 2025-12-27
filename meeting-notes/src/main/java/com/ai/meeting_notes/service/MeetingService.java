package com.ai.meeting_notes.service;

import com.ai.meeting_notes.entity.Meeting;
import com.ai.meeting_notes.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final SpeechToTextService speechToTextService;
    private final MeetingNotesService meetingNotesService;
    private final MeetingRepository meetingRepository;

    public Meeting processMeeting(File audioFile) {

        String transcript = speechToTextService.transcribe(audioFile);

        Map<String, Object> notes = meetingNotesService.generateNotes(transcript);
        String summary = (String) notes.get("summary");

        Meeting meeting = Meeting.builder()
                .transcript(transcript)
                .summary(summary)
                .build();

        return meetingRepository.save(meeting);
    }

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }


}
