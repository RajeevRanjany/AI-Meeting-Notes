![GitHub stars](https://img.shields.io/github/stars/<username>/AI-Meeting-Notes)
![GitHub issues](https://img.shields.io/github/issues/<username>/AI-Meeting-Notes)

# AI-Powered Meeting Notes Generator

An AI-assisted backend system that converts meeting audio into structured meeting notes.

## Tech Stack
- Java, Spring Boot
- Python, FastAPI
- Open-source Whisper (Speech-to-Text)
- REST APIs
- JPA (Hibernate), H2 / PostgreSQL
- Docker (optional)

## Architecture
- Audio files are uploaded via a Spring Boot REST API.
- The backend forwards audio to a Python FastAPI service using Whisper for transcription.
- Meeting summaries, action items, and decisions are generated from transcripts.
- Data is persisted using Spring Data JPA.
- Centralized exception handling ensures clean API responses.

## Features
- Speech-to-text using open-source Whisper
- Automated extraction of meeting summaries and action items
- REST APIs for uploading audio and retrieving past meetings
- Global exception handling
- Lightweight UI for demo purposes

## How to Run (Local)
1. Start Python STT service:
```bash
cd stt-service
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
uvicorn app:app --port 8001
