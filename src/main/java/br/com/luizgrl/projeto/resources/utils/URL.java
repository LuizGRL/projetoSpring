package br.com.luizgrl.projeto.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Integer> decodeIntList(String string){
        return Arrays.asList(string.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
    public static String decodeParam(String string){
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return ""; // caso de um erro vai retornar uma string vazia a inteção desse metodo é tirar um possivel caracter vazio no URI 
        }
    }
}
