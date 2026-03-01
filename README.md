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

### POST `/api/chatbot`

### Example 1

**Request**
```json
{
  "question": "Who is Sanju?"
}
```
**Response**
```
"Sanju is the person who built this project."
```

### Example 2
**Request**
```json
{
  "question": "Is Sanju good with Docker?"
}
```
**Response**
```
"Yes, Sanju is good with Docker."
```
