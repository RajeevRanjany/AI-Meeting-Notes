package com.ai.meeting_notes.controller;

import com.ai.meeting_notes.dto.MeetingResponse;
import com.ai.meeting_notes.entity.Meeting;
import com.ai.meeting_notes.exception.ResourceNotFoundException;
import com.ai.meeting_notes.repository.MeetingRepository;
import com.ai.meeting_notes.service.MeetingNotesService;
import com.ai.meeting_notes.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;
    private final MeetingNotesService meetingNotesService;
    private final MeetingRepository meetingRepository;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/upload")
    public ResponseEntity<MeetingResponse> uploadMeeting(
            @RequestParam("file") MultipartFile file) throws Exception {

        File tempFile = File.createTempFile("meeting-", file.getOriginalFilename());
        file.transferTo(tempFile);

        Meeting meeting = meetingService.processMeeting(tempFile);

        Map<String, Object> notes =
                meetingNotesService.generateNotes(meeting.getTranscript());

        return ResponseEntity.ok(
                MeetingResponse.builder()
                        .transcript(meeting.getTranscript())
                        .notes(notes)
                        .build()
        );
    }

    @GetMapping("/all_meetings")
    public ResponseEntity<?> getAllMeetings() {
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }

    @GetMapping("/{id}")
    public Meeting getMeetingById(@PathVariable Long id) {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found with id " + id));
    }


}
