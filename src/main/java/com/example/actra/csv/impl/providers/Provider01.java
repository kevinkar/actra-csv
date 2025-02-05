package com.example.actra.csv.impl.providers;

import com.example.actra.csv.Transformer;
import com.example.actra.csv.Provider;

import java.util.Arrays;
import java.util.List;

/**
 * Class for Provider 01.
 */
class Provider01 implements Provider {

    private static final String[] PROVIDER_01_HEADER_FI = {"Kirjauspäivä", "Maksupäivä", "Summa", "Tapahtumalaji", "Maksaja", "Saajan nimi", "Saajan tilinumero", "Saajan BIC-tunnus", "Viitenumero", "Viesti", "Arkistointitunnus"};
    private static final String[] PROVIDER_01_HEADER_SV = {"Bokningsdag", "Betalningsdag", "Belopp", "Betalningstyp", "Betalare", "Mottagarens namn", "Mottagarens kontonummer", "Mottagarens BIC-kod", "Referensnummer", "Meddelande", "Arkiveringskod"};

    public Transformer getCsvTransformer() {
        return new Provider01Transformer();
    }

    public static List<String[]> getHeaders() {
        return List.of(
                Arrays.copyOf(PROVIDER_01_HEADER_FI, PROVIDER_01_HEADER_FI.length),
                Arrays.copyOf(PROVIDER_01_HEADER_SV, PROVIDER_01_HEADER_SV.length)
        );
    }

    @Override
    public String getName() {
        return "S";
    }

}
