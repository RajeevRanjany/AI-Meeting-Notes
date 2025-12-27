package com.ai.meeting_notes.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeetingNotesService {

    public Map<String, Object> generateNotes(String transcript) {

        String summary = transcript.length() > 150
                ? transcript.substring(0, 150) + "..."
                : transcript;

        List<String> actionItems = Arrays.stream(transcript.split("\\."))
                .map(String::trim)
                .filter(s -> s.toLowerCase().contains("will"))
                .collect(Collectors.toList());

        List<String> decisions = new ArrayList<>();
        if (!actionItems.isEmpty()) {
            decisions.add("Responsibilities assigned during the meeting");
        } else {
            decisions.add("General discussion without explicit decisions");
        }

        // Next steps
        List<String> nextSteps = List.of(
                "Review action items",
                "Follow up in next meeting"
        );

        Map<String, Object> notes = new HashMap<>();
        notes.put("summary", summary);
        notes.put("actionItems", actionItems);
        notes.put("decisions", decisions);
        notes.put("nextSteps", nextSteps);

        return notes;
    }
}
