package com.adkunwar.text.stream.service;

import com.adkunwar.text.stream.InferenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProviderService {
    private final Map<String, InferenceProvider> providers;
    private InferenceProvider currentProvider;

    @Autowired
    public ProviderService(Map<String, InferenceProvider> providers) {
        this.providers = providers;
        this.currentProvider = providers.get("default"); // Set initial provider
    }

    public void switchProvider(String providerName) {
        InferenceProvider provider = providers.get(providerName);
        if (provider != null) {
            this.currentProvider = provider;
        } else {
            throw new IllegalArgumentException("Provider not found");
        }
    }

    public String processText(String text) {
        return currentProvider.processText(text);
    }
}
