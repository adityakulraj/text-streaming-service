package com.adkunwar.text.stream.impl;

import com.adkunwar.text.stream.InferenceProvider;
import org.springframework.stereotype.Component;


@Component
public class ProviderA  implements InferenceProvider {
    @Override
    public String processText(String text) {
        //TODO
        return "ProviderA: " + text;
    }

    @Override
    public String toString() {
        return "providerA";
    }
}
