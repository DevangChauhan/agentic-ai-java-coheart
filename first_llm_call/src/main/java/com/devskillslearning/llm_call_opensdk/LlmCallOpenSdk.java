package com.devskillslearning.llm_call_opensdk;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class LlmCallOpenSdk {

    public static void main(String[] args) {

        final OpenAIClient openAiClient = OpenAIOkHttpClient.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .build();

        final ResponseCreateParams responseCreateParams = ResponseCreateParams.builder()
                .model("gpt-6-astra")
                .input("Tell me about Internet")
                .build();

        final Response response  = openAiClient.responses().create(responseCreateParams);

        response.output().stream()
                .flatMap(item->item.message().stream())
                .flatMap(item->item.content().stream())
                .flatMap(content->content.outputText().stream())
                .forEach(System.out::println);
    }

}

