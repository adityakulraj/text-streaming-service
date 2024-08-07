package com.adkunwar.text.stream.impl;

import com.adkunwar.text.stream.InferenceProvider;

public class ProviderB implements InferenceProvider {
    @Override
    public String processText(String text) {
        //TODO
        return "ProviderB : " + text;
    }
}
