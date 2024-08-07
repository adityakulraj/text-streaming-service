package com.adkunwar.text.stream.service;

import com.adkunwar.text.stream.InferenceProvider;
import com.adkunwar.text.stream.SwitchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProviderService {
    private final Map<String, InferenceProvider> providers;
    private InferenceProvider currentProvider;

    private final SwitchStrategy switchStrategy;

    @Autowired
    public ProviderService(Map<String, InferenceProvider> providers, SwitchStrategy switchStrategy) {
        this.providers = providers;
        this.currentProvider = providers.getOrDefault("default", providers.get("providerA"));// Set initial provider
        this.switchStrategy = switchStrategy;
    }

    public void switchProvider(String providerName) {
        InferenceProvider provider = providers.get(providerName);
        if (provider != null) {
            this.currentProvider = provider;
        } else {
            throw new IllegalArgumentException("Provider not found");
        }
    }

    public String processText(String text, Map<String, String> headers) {
        if(switchStrategy.checkForSwitch(headers)) {
            this.currentProvider = switchStrategy.switchProviderSilently(this.providers, this.currentProvider.toString());
        }
        return this.currentProvider.processText(text);
    }
}
