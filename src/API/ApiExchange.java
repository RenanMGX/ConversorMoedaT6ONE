package API;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiExchange {
    private String Url;
    private Object Token;
    private Map<String, String> Headers = new HashMap<>();
    private Gson gson = new Gson();

    //Authorization: Bearer YOUR-API-KEY

    public ApiExchange(String url, String token) {
        if (url.endsWith("/")){
            url = url.substring(0, (url.length() - 1));
        }
        this.Url = url;
        this.Token = token;
        this.Headers.put("Authorization", String.format("Bearer %s", token));
    }

    public String getUrl(){
        return Url;
    }
    public Object getToken(){
        return Token;
    }

    public Double ConvertTo(String primaryCoin, String secondaryCoin, Double value) throws Exception {
        try {
            var response = Request.get(String.format("%s/v6/pair/%s/%s", this.Url, primaryCoin, secondaryCoin), this.Headers);
            if (response.statusCode() != 200) {
                System.out.println(String.format("não foi possivel consumir a api, %s", response.body()));
            }
            Double baseValueCoin = gson.fromJson((String) response.body(), JsonSeriable.class).conversion_rate();

            return value * baseValueCoin;

        }catch (Exception error){
            System.out.println(String.format("não foi possivel consumir a api, %s", error));
            return -99.99;
        }
    }
    public Double ConvertTo(String primaryCoin, String secondaryCoin, Integer value) throws Exception {
        try {
            var response = Request.get(String.format("%s/v6/pair/%s/%s", this.Url, primaryCoin, secondaryCoin), this.Headers);
            if (response.statusCode() != 200) {
                System.out.println(String.format("não foi possivel consumir a api, %s", response.body()));
            }
            Double baseValueCoin = gson.fromJson((String) response.body(), JsonSeriable.class).conversion_rate();

            return value * baseValueCoin;

        }catch (Exception error){
            System.out.println(String.format("não foi possivel consumir a api, %s", error));
            return -99.99;
        }
    }


}
