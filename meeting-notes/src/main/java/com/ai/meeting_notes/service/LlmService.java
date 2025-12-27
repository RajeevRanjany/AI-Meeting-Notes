package com.ai.meeting_notes.service;

import com.ai.meeting_notes.util.PromptBuilder;
import org.springframework.stereotype.Service;

@Service
public class LlmService {
    public String generateNotes(String transcript) {
        String prompt = PromptBuilder.buildMeetingPrompt(transcript);
        // TODO: Call LLM API (OpenAI / Llama)
        return "AI generated meeting notes";
    }
}
