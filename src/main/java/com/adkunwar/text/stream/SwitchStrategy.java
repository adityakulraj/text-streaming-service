package com.adkunwar.text.stream;

import java.util.Map;

public interface SwitchStrategy {

    public boolean checkForSwitch(Map<String, String> criteria);

    public InferenceProvider switchProviderSilently(Map<String, InferenceProvider> inferenceProviders, String currentProvider);

}
