from fastapi import FastAPI
from request_schemas import RequestQuestion, RequestQuestionWithContext
from embedding_model import embedding
from llama3_model import prompt

app = FastAPI()

@app.post("/api/embed")
def embed(question_json: RequestQuestion) -> list[float]:
    question: str = question_json.question.strip()
    embeddings: list[float] = embedding(question)

    return embeddings

@app.post("/api/chatbot")
def chatbot(question_context_json: RequestQuestionWithContext):
    response = prompt(question_context_json)

    return response

@app.get("/api/health")
def health() -> str:
    return "active"

@app.get("/api/ping")
def pong() -> str:
    return "pong"