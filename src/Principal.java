import com.google.gson.Gson;
import exachange.com.modelos.ExchangeRates;
import exachange.com.modelos.HistorialDeBusqueda;
import exachange.com.modelos.HttpRequester;
import exachange.com.modelos.LectorJsonMapa;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Principal {

    public static void main(String[] args) throws IOException, InterruptedException {

        String ApiKey = "654df65956487af786f2778b";

        String menuEntrada = """
                ***************************************
                Bienvenido al sistema conversor de moneda.
                1-Ingresa el nombre de el pais para conocer el tipo de cambio en dolares
                2-Elije entre convertir de moneda local a dolares o de dolares moneda local.
                
                3-Escribe salir para cerrar el sistema
                
                *******************************************
                
                """;
        Scanner scanner = new Scanner(System.in);



        List<HistorialDeBusqueda> listadoDeBusqueda = new ArrayList<>();

        String pais;



        //String paisDeProcedencia;

        //int cantidadEnMonedaLocal;

        HttpRequester newRequest = new HttpRequester();

        String json = newRequest.getJsonResponse();


        Map<String, String> paises = LectorJsonMapa.listadoDePaises("src/paises.json");

        Map<String, String> paisesEnminusculas = new HashMap<>();



        for (Map.Entry<String, String> entry : paises.entrySet()){

            paisesEnminusculas.put(entry.getKey().toLowerCase(), entry.getValue());

        }

        if(paisesEnminusculas == null){
            System.out.println("El archivo Json no se pudo cargar");
            return;
        }

        while (true){

            System.out.println(menuEntrada);
            System.out.println("Ingresa el nombre de el pais de origen o escribe salir");

            //No importa si el usuario usa mayusculas o minuscals si el pais existe la app lo encotrara.

            pais = scanner.nextLine().toLowerCase();


            //listadoDeBusqueda.add(historico);




            if(pais.equalsIgnoreCase("Salir")){
                break;
            }

            String codigoMoneda = paisesEnminusculas.get(pais);



            if (codigoMoneda != null){

                HistorialDeBusqueda historico = new HistorialDeBusqueda();



                historico.setPais(pais);
                historico.setCodigoModena(codigoMoneda);


                if (json != null) {

                    Gson gson = new Gson();

                    ExchangeRates response = gson.fromJson(json, ExchangeRates.class);

                    Map<String, Double> rates = response.conversion_rates;

                    System.out.println("El tipo de cambio es: "+rates.get(codigoMoneda) +" "+ codigoMoneda);
                    historico.setRateLocal(rates.get(codigoMoneda));


                    Double cantidadAConvertir = null;


                    //Test para evitar que el usuario ingrese datos incorrectos y se rompa la app.
                    //El tipo de dato debe ser Double


                    while (cantidadAConvertir == null){
                        System.out.println("Introduce la cantidad a convertir");

                        try {
                            cantidadAConvertir = scanner.nextDouble();
                            historico.setCantidad(cantidadAConvertir);
                            scanner.nextLine();
                        } catch (InputMismatchException e){
                            System.out.println("introduce una cantidad valida.");
                            scanner.nextLine();
                        }
                    }

                    int tipoDeCoversion = 3;

                    while (tipoDeCoversion != 0){
                        System.out.println("""
                                -Elije una option
                                1- converir de dolares a moneda local
                                2- Convertir de moneda local a dolares
                                0- Salir de e sistema
                                """);

                        //Test para evitar que el usuario ingrese datos incorrectos y se rompa la app.
                        //El tipo de dato debe ser int

                        try {
                            tipoDeCoversion = scanner.nextInt();
                            historico.setTipoDeConversion(tipoDeCoversion);
                            scanner.nextLine();
                        } catch (InputMismatchException e){
                            System.out.println("introduce una option valida");
                            scanner.nextLine();
                        }


                        if (tipoDeCoversion == 1){
                            Double resultadoDeConversion = cantidadAConvertir * rates.get(codigoMoneda);
                            System.out.println("El total es: " +resultadoDeConversion + "  "+ codigoMoneda);
                            break;
                        }  else if (tipoDeCoversion == 2) {
                            Double resultadoDeConversion = cantidadAConvertir / rates.get(codigoMoneda);
                            System.out.println("El total es: " +resultadoDeConversion + " USD");
                            break;
                        } else if (tipoDeCoversion == 0){
                            System.out.println("Saliendo de el sitema");
                        } else {
                            System.out.println("Las opciones validas son 1,2 y 0. Intenta de nuevo");
                        };
                    }

                    while (true) {
                        System.out.println("Escribe 'nueva' para realizar una nueva conversión o 'salir' para cerrar el sistema:");
                        String tareaSiguiente = scanner.nextLine().trim().toLowerCase();
                        historico.setOptionFinal(tareaSiguiente);

                        //El usuario podra crear una nueva busqueda o cerrar el programa.

                        if (tareaSiguiente.equals("nueva")) {
                            break; // rompe el bucle y vuelve al principio del menú
                        } else if (tareaSiguiente.equals("salir")) {
                            System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
                            return; // termina completamente el programa
                        } else {
                            System.out.println("Opción no válida. Por favor escribe 'nueva' o 'salir'.");
                        }
                    }

                    listadoDeBusqueda.add(historico);

                    System.out.println(listadoDeBusqueda);

                } else {
                    System.out.println("Ocurrió un error al obtener el JSON.");
                }
            } else {




                System.out.println("Pais no encontrado");

            }



        }

    }
}
