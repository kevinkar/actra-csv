package com.example.actra.csv.impl;

import com.example.actra.csv.Transformer;
import com.example.actra.csv.Provider;

import java.util.List;
import java.util.Arrays;

/**
 * Class for Provider 02.
 */
class Provider02 implements Provider {

    private static final String[] PROVIDER_02_HEADER_FI = {"Kirjauspäivä", "Määrä", "Maksaja", "Maksunsaaja", "Nimi", "Otsikko", "Viitenumero", "Saldo", "Valuutta", ""};
    private static final String[] PROVIDER_02_HEADER_SV = {"Bokföringsdag", "Belopp", "Avsändare", "Mottagare", "Namn", "Rubrik", "Referensnummer", "Saldo", "Valuta", ""};
    private static final String[] PROVIDER_02_HEADER_EN = {"Booking date", "Amount", "Sender", "Recipient", "Name", "Title", "Reference number", "Balance", "Currency", ""};

    public Transformer getCsvTransformer() {
        return new Provider02Transformer();
    }

    public static List<String[]> getHeaders() {
        return List.of(
                Arrays.copyOf(PROVIDER_02_HEADER_FI, PROVIDER_02_HEADER_FI.length),
                Arrays.copyOf(PROVIDER_02_HEADER_SV, PROVIDER_02_HEADER_SV.length),
                Arrays.copyOf(PROVIDER_02_HEADER_EN, PROVIDER_02_HEADER_EN.length)
        );
    }

    @Override
    public String getName() {
        return "N";
    }

}