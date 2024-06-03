package Interfaz;

import Clases.Exception.ConexionExcepcion;
import Logica.Conveccion;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Interfaces {
    public void header(){
        System.out.println("*************************************************************");
        System.out.println(" ");
        System.out.println("Bienvenido al sistema de intercambio de monedas");
        System.out.println(" ");
        System.out.println("*************************************************************");
    }

    public void interfaz_principal(){
        System.out.println("Seleccione la opción deseada:");
        System.out.println("1. Obtener todas las tasas de conversión de una moneda determinada");
        System.out.println("2. Obtener el cambio de dos monedas en específico");
        System.out.println("Otro. Salir");
        String valor = new Scanner(System.in).nextLine();
        switch(valor){
            case "1":
               try {
                   interfazTodasLasMonedas();
               }catch (Exception e){
                   System.out.println(e.getMessage());
               }
                break;
                case "2":
                    try {
                        interfazCambio();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            default:
                System.out.println("Gracias por utilizar nuestra aplicación");
                break;
        }

    }

    public void interfazTodasLasMonedas() throws ConexionExcepcion,Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        header();
        System.out.println("Indica la moneda a verificar por su siglas");
        try {
            Map<String,String> monedas = new Conveccion().claveValorMoney();
            for (Map.Entry<String,String>elemento : monedas.entrySet()){
                System.out.println(elemento.getKey() + "-" + elemento.getValue());
            }
            String moneda = new Scanner(System.in).nextLine().toUpperCase();
            for (Map.Entry<String,Double>elemento : new Conveccion().listado_monedas(moneda).entrySet()){
                System.out.println(elemento.getKey() + "-" + new Conveccion().claveValorMoney().get(elemento.getKey())+"-"+ elemento.getValue());
            }
        } catch (IOException | InterruptedException e) {
            throw new ConexionExcepcion("Compruebe su conexión a Internet");
        }
    }

    public void interfazCambio()throws ConexionExcepcion,Exception{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        header();
        System.out.println("Indica las 2 monedas y su valor a verificar por su siglas");
        try {
            Map<String,String> monedas = new Conveccion().claveValorMoney();
            for (Map.Entry<String,String>elemento : monedas.entrySet()){
                System.out.println(elemento.getKey() + "-" + elemento.getValue());
            }
            System.out.println("Indica la primera moneda");
            String moneda = new Scanner(System.in).nextLine().toUpperCase();
            System.out.println("Indica la segunda moneda");
            String moneda1 = new Scanner(System.in).nextLine().toUpperCase();
            System.out.println("Indica la cantidad de "+moneda+" que desea convertir a "+moneda1);
            Double valor = new Scanner(System.in).nextDouble();
            System.out.println(valor+"-"+moneda+" = "+new Conveccion().cambio(moneda,moneda1,valor)+"-"+moneda1);
        }catch (Exception e){
            System.out.println("Compruebe su internet o su ingreso de datos");
        }
    }
}
