package com.sanjusabu.springbootfastapi.dto.llm;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RequestQuestionWithContext {
    private String question;
    private List<String> context;
}
