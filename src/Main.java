import API.ApiExchange;
import Entities.ListarMoedas;

import java.io.IOException;
import java.security.Signature;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        ApiExchange conversor = new ApiExchange("https://v6.exchangerate-api.com/", "0e3ee7e729c82cafafaae7b6");

        Map<String, List<String>> parameters = new HashMap<>();
        parameters.put("sair", List.of(
                "sair", "exit", "/s", "bye"
        ));
        parameters.put("help", List.of(
                "help", "/h"
        ));
        parameters.put("converter", List.of(
                "converter", "iniciar", "start", "/s"
        ));

        Map<String, List<String>> moedas = new HashMap<>();
        moedas.put("1", List.of("BRL", "Real Brasileiro"));
        moedas.put("2", List.of("USD", "Dolar"));
        moedas.put("3", List.of("EUR", "Euro "));
        moedas.put("4", List.of("JPY", "Iene Japonês"));
        moedas.put("5", List.of("GBP", "Libra Esterlina"));
        moedas.put("6", List.of("CAD", "Dólar Canadense"));
        moedas.put("7", List.of("AUD", "Dólar Australiano"));
        moedas.put("8", List.of("CHF", "Franco Suíço"));
        moedas.put("9", List.of("NZD", "Dólar Neozelandês"));
        moedas.put("10", List.of("CNY", "Renminbi Chinês"));

        System.out.println("Iniciando Programa ConversorMoedaT6ONE");
        System.out.println("no caso de duvidas escreva 'help' para ajuda!");
        while (true){
            System.out.printf("Digite um comando: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (parameters.get("sair").contains(input.toLowerCase())){
                System.out.println("programa encerrado!");
                break;
            } else if (parameters.get("help").contains(input.toLowerCase())) {
                for (Map.Entry<String, List<String>> entry : parameters.entrySet()){
                    if (entry.getKey().equals("sair")) {
                        System.out.println("\nParametros para encerrar o programa:");
                        System.out.println(entry.getValue());
                        System.out.println();

                    } else if (entry.getKey().equals("converter")) {
                        System.out.println("\nParametros para iniciar a conversão:");
                        System.out.println(entry.getValue());
                        System.out.println();
                    }else {
                        System.out.println(entry.getValue());
                    }
                }
            } else if (parameters.get("converter").contains(input.toLowerCase())) {
                System.out.println("");
                Boolean saindoBloco = false;
                //moedas principal
                Map<String, String> moedaSelecionadas = new HashMap<>();
                while (true) {
                    try {
                        ListarMoedas.show(moedas);
                        System.out.printf("escolha a moeda principal: ");
                        scanner = new Scanner(System.in);
                        input = scanner.nextLine();
                        if (parameters.get("sair").contains(input.toLowerCase())){
                            System.out.println("Fechando seletor de moeda!");
                            saindoBloco = true;
                            break;
                        }
                        if (moedas.containsKey(input.toLowerCase())){
                            moedaSelecionadas.put("moedaPrimaria", moedas.get(input).get(0));
                            break;
                        }else {
                            System.out.println("moeda não existe tente novamente");
                            continue;
                        }
                    }catch (Exception error){
                        System.out.println(String.format("Error ao selecionar moeda motivo: %s", error));
                    }
                }
                if (saindoBloco){
                    break;
                }
                //moedas secundaria
                while (true) {
                    try {
                        ListarMoedas.show(moedas);
                        System.out.println(String.format("Moeda Principal selecionada: %s", moedaSelecionadas.get("moedaPrimaria")));
                        System.out.printf("escolha a moeda para conversão: ");
                        scanner = new Scanner(System.in);
                        input = scanner.nextLine();
                        if (parameters.get("sair").contains(input.toLowerCase())){
                            System.out.println("Fechando seletor de moeda!");
                            saindoBloco = true;
                            break;
                        }
                        if (moedas.containsKey(input.toLowerCase())){
                            moedaSelecionadas.put("moedaSecundaria", moedas.get(input).get(0));
                            break;
                        }else {
                            System.out.println("moeda não existe tente novamente");
                            continue;
                        }
                    }catch (Exception error){
                        System.out.println(String.format("Error ao selecionar moeda motivo: %s", error));
                    }
                }
                if (saindoBloco){
                    break;
                }

                //valor a converter
                while (true) {
                    try {
                        System.out.println(String.format("Será convertida a moeda %s para a moeda %s", moedaSelecionadas.get("moedaPrimaria"), moedaSelecionadas.get("moedaSecundaria")));
                        System.out.printf("Qual valor a ser convertido: ");
                        scanner = new Scanner(System.in);
                        input = scanner.nextLine();
                        if (parameters.get("sair").contains(input.toLowerCase())) {
                            System.out.println("Fechando seletor de moeda!");
                            break;
                        }

                        input = input.replace(".", "");
                        input = input.replace(",", ".");
                        Double valorInput = Double.valueOf(input);
                        Double valorConvertido = conversor.ConvertTo(moedaSelecionadas.get("moedaPrimaria"), moedaSelecionadas.get("moedaSecundaria"), valorInput);

                        System.out.println(String.format("\no valor %s convertido de %s para %s é: %s", valorInput, moedaSelecionadas.get("moedaPrimaria"), moedaSelecionadas.get("moedaSecundaria"), valorConvertido));

                        break;
                    }catch (NumberFormatException e) {
                        System.out.println("\né permitido apenas numeros");

                    }catch (Exception error){
                        System.out.println(String.format("\nError ao converter moeda: %s", error));
                    }
                }
            }
            System.out.println();
        }
    }
}