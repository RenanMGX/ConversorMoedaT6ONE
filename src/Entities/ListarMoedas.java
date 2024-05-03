package Entities;

import java.util.List;
import java.util.Map;

public class ListarMoedas {
    public static void show(Map<String, List<String>> moedas) {
        for (Map.Entry<String, List<String>> entry : moedas.entrySet()) {
            var moeda = entry.getValue();
            System.out.println(String.format("%s. %s -> %s", entry.getKey(), moeda.get(0), moeda.get(1)));


        }
        System.out.println();
    }
}
