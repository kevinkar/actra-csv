package com.example.actra.csv.impl.providers;

import com.example.actra.csv.Provider;

import java.util.*;
import java.util.function.Supplier;

/**
 * Keeps track of providers and selects the most likely provider based on the CSV headers.
 * <p>
 * Somewhat inspired by the OSGi approach without being a OSGi project.
 */
public class ProviderSelector {

    private final Map<Integer, Supplier<Provider>> providers = new HashMap<>();

    public ProviderSelector() {
        // Register our known providers, including an empty one.
        registerProvider(Provider01::new, Provider01.getHeaders());
        registerProvider(Provider02::new, Provider02.getHeaders());
        registerProvider(EmptyProvider::new, Provider.getHeaders());
    }

    /**
     * Register a provider in code.
     *
     * @param provider        Provider to register
     * @param providerHeaders Headers of the providers
     */
    private void registerProvider(Supplier<Provider> provider, List<String[]> providerHeaders) {
        providerHeaders.stream().map(Arrays::hashCode).forEach(hashCode -> providers.put(hashCode, provider));
    }

    /**
     * Based on the header strings, finds the likely provider.
     * <p>
     * As per usual, user discretion is advised.
     *
     * @param header Header row of CSV
     * @return Provider, or null for no match
     */
    public Provider resolveProvider(String[] header) {
        if (header == null || header.length == 0) {
            return null;
        }

        Supplier<Provider> provider = providers.get(Arrays.hashCode(header));
        return provider != null ? provider.get() : null;
    }

}
