package com.ai.meeting_notes.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeetingResponse {
    private String transcript;
    private Map<String, Object> notes;
}
