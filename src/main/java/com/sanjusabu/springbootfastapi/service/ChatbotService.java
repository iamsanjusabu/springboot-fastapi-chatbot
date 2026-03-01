package com.sanjusabu.springbootfastapi.service;

import com.sanjusabu.springbootfastapi.dto.RequestQuestion;
import com.sanjusabu.springbootfastapi.dto.RequestQuestionWithContext;
import com.sanjusabu.springbootfastapi.repository.VectorTableRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChatbotService {

    private final RestTemplate restTemplate;
    private final VectorTableRepository vectorTableRepo;

    public ChatbotService(RestTemplate restTemplate, VectorTableRepository vectorTableRepo) {
        this.restTemplate = restTemplate;
        this.vectorTableRepo = vectorTableRepo;
    }

    public String postRawTextToFastAPI(String question) {
        RequestQuestion reqQuestion = new RequestQuestion();
        reqQuestion.setQuestion(question);

        ResponseEntity<List<Float>> response = restTemplate.exchange(
                "http://localhost:8000/api/embed",
                HttpMethod.POST,
                new HttpEntity<>(reqQuestion),
                new ParameterizedTypeReference<List<Float>>() {}
        );

        List<Float> referenceFloats = response.getBody();

        if (referenceFloats.isEmpty()) {
            return ResponseForAndFromLLM(question, List.of());
        }

        float[] actualFloats = new float[referenceFloats.size()];

        for (int i = 0; i < referenceFloats.size(); i++) {
            actualFloats[i] = referenceFloats.get(i);
        }

        List<String> matches = vectorTableRepo.closest5Matches(actualFloats);
        return ResponseForAndFromLLM(question, matches);
    }

    public String ResponseForAndFromLLM(String question, List<String> context) {
        RequestQuestionWithContext reqQuestionWithContext = new RequestQuestionWithContext();
        reqQuestionWithContext.setQuestion(question);
        reqQuestionWithContext.setContext(context);

        return restTemplate.postForObject(
                "http://localhost:8000/api/chatbot",
                reqQuestionWithContext,
                String.class
        );
    }
}
