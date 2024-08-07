package com.adkunwar.text.stream.impl;

import com.adkunwar.text.stream.InferenceProvider;
import com.adkunwar.text.stream.SwitchStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component
public class HttpHeaderSwitchStrategy implements SwitchStrategy {



    @Override
    public boolean checkForSwitch(Map<String,String> criteria) {
        if(criteria.containsKey("switch-provider") && Boolean.valueOf(criteria.get("switch-provider"))) {
            return true;
        }
        return false;
    }

    @Override
    public InferenceProvider switchProviderSilently(Map<String,InferenceProvider> inferenceProviders, String currentProvider) {

        for(String s : inferenceProviders.keySet()) {

            if(!s.equals(currentProvider))
                return inferenceProviders.get(s);

        }

        return inferenceProviders.get(currentProvider);

    }

}
