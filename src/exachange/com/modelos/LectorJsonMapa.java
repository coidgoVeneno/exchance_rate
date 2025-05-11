package exachange.com.modelos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import static java.lang.Character.getType;

public class LectorJsonMapa {

    public static Map<String, String> listadoDePaises(String rutaJson){

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(rutaJson)){

            Type tipoMapa = new TypeToken<Map<String, String>>(){}.getType();
            return gson.fromJson(reader, tipoMapa);

        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }



}
