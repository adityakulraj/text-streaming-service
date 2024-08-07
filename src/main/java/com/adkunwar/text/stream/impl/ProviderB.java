package com.adkunwar.text.stream.impl;

import com.adkunwar.text.stream.InferenceProvider;
import org.springframework.stereotype.Component;

@Component
public class ProviderB implements InferenceProvider {
    @Override
    public String processText(String text) {
        //TODO
        return "ProviderB : " + text;
    }

    @Override
    public String toString() {
        return "providerB";
    }
}
