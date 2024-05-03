import API.ApiExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        ApiExchange conversor = new ApiExchange("https://v6.exchangerate-api.com/", "0e3ee7e729c82cafafaae7b6");
        //ApiExchange conversor = new ApiExchange("https://v6.exchangerate-api.com/", "test");

        Double valor = conversor.ConvertTo("USD", "BRL", 4500);



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

        //Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();
        //System.out.println(input.toLowerCase());
        //System.out.println(input.toLowerCase().equals("sair"));


        System.out.println("Iniciando Programa ConversorMoedaT6ONE");
        System.out.println("no caso de duvidas escreva 'help' para ajuda!");
        while (true){
            System.out.printf("Digite um comando: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (parameters.get("sair").contains(input.toLowerCase())){
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

            }


            System.out.println();


        }

    }
}