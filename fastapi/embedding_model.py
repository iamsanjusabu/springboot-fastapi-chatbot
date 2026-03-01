from sentence_transformers import SentenceTransformer
from dotenv import load_dotenv
from os import getenv

load_dotenv()

model = SentenceTransformer(getenv("BGE_SMALL_EN_V1_5"))

def embedding(question: str):
    embeddings: list[float] = model.encode(question).tolist()

    return embeddings