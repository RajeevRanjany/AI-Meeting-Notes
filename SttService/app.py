from fastapi import FastAPI, UploadFile, File
from faster_whisper import WhisperModel

app = FastAPI()

model = None

@app.on_event("startup")
def load_model():
    global model
    print("Loading Whisper model...")
    model = WhisperModel("base")
    print("Model loaded!")

@app.post("/transcribe")
async def transcribe(file: UploadFile = File(...)):
    audio_path = f"/tmp/{file.filename}"

    with open(audio_path, "wb") as f:
        f.write(await file.read())

    segments, _ = model.transcribe(audio_path)
    text = " ".join([seg.text for seg in segments])

    return {"transcript": text}
