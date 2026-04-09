# Spring Boot + FastAPI RAG Chatbot

A Retrieval-Augmented Generation chatbot using Spring Boot, FastAPI, Llama 3.2 for local inference, BGE-small-en-v1.5 for embeddings, and PostgreSQL with pgvector for vector storage.

## How it works

There's no UI except an health check endpoint, tests are done using Postman.

Send your question to Spring Boot at `POST http://localhost:8080/api/chatbot` with:

```json
{
  "question": "your question here"
}
```

Spring Boot is the orchestration layer. It takes your question, asks FastAPI to vectorize it using BGE embeddings, then runs a similarity search in pgvector. If relevant context is found, it gets sent along with the question to FastAPI. If not, just the question goes through.

FastAPI handles all the ML. It builds a prompt for Llama 3.2 — with or without context depending on what came back from the vector search — and returns the response up through Spring Boot to you.

Llama runs entirely locally with 4-bit quantization via bitsandbytes (hardware constraints).

## Authentication

Before hitting the chatbot endpoint, get a JWT token from `POST http://localhost:8080/auth/login/jwt` with your username and password. Then include it in your requests as `Authorization: Bearer <token>`. All `/api/*` endpoints are protected.

## Stack

- Spring Boot
- FastAPI
- Llama 3.2
- BGE-small-en-v1.5
- PostgreSQL + pgvector
- Docker Compose

## Running it

Make sure Docker is running, then `docker compose up`. That starts the PostgreSQL container. The chatbot is ready at `http://localhost:8080/api/chatbot`.

## Notes

- Llama runs locally, no external API calls are made for inference
- The FastAPI service is not exposed directly; all requests go through Spring Boot