import API.ApiExchange;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        ApiExchange conversor = new ApiExchange("https://v6.exchangerate-api.com/", "0e3ee7e729c82cafafaae7b6");
        //ApiExchange conversor = new ApiExchange("https://v6.exchangerate-api.com/", "test");

        Double valor = conversor.ConvertTo("USD", "BRL", 4500);



    }
}