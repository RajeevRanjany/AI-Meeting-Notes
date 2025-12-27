package com.ai.meeting_notes.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.util.Map;

@Service
public class SpeechToTextService {
    private final WebClient webClient = WebClient.create("http://127.0.0.1:8001");
    public String transcribe(File audioFile) {
        Map response = webClient.post()
                .uri("/transcribe")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(
                        "file",
                        new FileSystemResource(audioFile)
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return (String) response.get("transcript");
    }
}
