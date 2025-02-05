package com.example.actra.csv.impl.providers;

import com.example.actra.csv.Transformer;
import com.example.actra.csv.Provider;

class EmptyProvider implements Provider {

    public Transformer getCsvTransformer() {
        return new EmptyTransformer();
    }

    @Override
    public String getName() {
        return "";
    }

}