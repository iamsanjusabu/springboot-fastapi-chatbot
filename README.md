# Spring Boot + FastAPI RAG Chatbot

A Retrieval-Augmented Generation system using:
- Spring Boot (Java)
- FastAPI (Python)
- Llama 3.2 (local inference)
- BGE-small-en-v1.5 embeddings
- PostgreSQL + pgvector
- Docker Compose

---

## Overview

### How it works

Send a POST request to Spring Boot 

http://localhost:8080/api/chatbot

```json
{
  "question":"question here"
}
```

(POSTMAN or curl (POSTMAN preferred), no UI intentionally).

Spring Boot sends the question to FastAPI (POST) which vectorizes it using BGE embeddings model then returns the vector back to Spring Boot.

Spring Boot then does a similiarity search in pgvector, If context is found, it sends the question + context to FastAPI. If not, it sends the question with an empty list.

FastAPI checks if context exists or not. 
- If yes, it builds a system + context + user prompt for llama 3.2
- if not, it builds a system + user prompt for llama 3.2

Llama runs locally, no api calls

Response comes back to Spring Boot and out to the client.

**Spring Boot is the orchestration layer and FastAPI is purely the ML layer.**

*Llama model runs locally with 4-bit quantization (bitsandbytes) due to hardware constraints.*