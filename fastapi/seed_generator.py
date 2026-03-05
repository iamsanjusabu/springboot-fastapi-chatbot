from sentence_transformers import SentenceTransformer
from dotenv import load_dotenv
from os import getenv

load_dotenv()

model = SentenceTransformer(getenv("BGE_SMALL_EN_V1_5"))

arr: list[str] = [
    "Sanju is the person who built this project.",
    "Sanju has experience with Python and Java.",
    "Sanju has worked with Spring Boot and FastAPI.",
    "Sanju uses Docker and Docker Compose for containerized setups.",
    "Sanju is familiar with basic AWS concepts.",
    "Sanju is interested in cloud security.",
    "Sanju is comfortable working in Linux environments."
]

with open("db/seed.sql", "a") as file:
    for i in range(0, len(arr)):
        file.write(f"INSERT INTO documents(content, embedding) VALUES ('{arr[i]}',\n'{model.encode(arr[i]).tolist()}') ON CONFLICT (content) DO NOTHING;\n\n")