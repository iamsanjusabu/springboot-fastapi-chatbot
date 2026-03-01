from pydantic import BaseModel

class RequestQuestion(BaseModel):
    question: str


class RequestQuestionWithContext(BaseModel):
    context: list[str]
    question: str