import whisper
from fastapi import FastAPI, Form
import uvicorn
from pydantic import BaseModel

app = FastAPI()
model = whisper.load_model("tiny")

class Item(BaseModel):
    audio_file: str

@app.post("/stt")
async def stt(audio_file: str = Form(...)):
    print(model.device)
    result = model.transcribe(audio=audio_file, language="ko")
    print(result["text"])

    return result["text"]


# if __name__ == "__main__":
#     uvicorn.run(app, host="0.0.0.0", port = 8000)