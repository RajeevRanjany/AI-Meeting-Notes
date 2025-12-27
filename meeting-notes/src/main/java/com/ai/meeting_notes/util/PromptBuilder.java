package com.ai.meeting_notes.util;

public class PromptBuilder {
    public static String buildMeetingPrompt(String transcript) {
        return """
                You are an AI meeting assistant.
                
                Generate structured meeting notes in JSON with:
                - summary
                - key_points
                - action_items (task, owner, deadline)
                - decisions
                
                Transcript:
                """ + transcript;
    }
}
