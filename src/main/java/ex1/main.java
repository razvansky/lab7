package ex1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public static Map<Integer, Carte> citire() throws IOException {
        File file = new File("lab7/src/carti.json");
        ObjectMapper mapper = new ObjectMapper();

        Map<Integer, Carte> carti = mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {
        });

        return carti;
    }

    //subpunctul 4
    static void scriere(Map<Integer, Carte> map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("lab7/src/carti2.json");
        Map<String, Map<String, String>> raw_carti = new HashMap<>();
        map.forEach((ID,Carte)->
        {
            Map<String,String> carti=new HashMap<>();
            carti.put("titlul",Carte.titlul());
            carti.put("autorul",Carte.autorul());
            carti.put("anul", String.valueOf(Carte.anul()));
            raw_carti.put(String.valueOf(ID), carti);
        });
        mapper.writeValue(file, raw_carti);
    }

    public static void main(String[] args) throws IOException {
        Map<Integer, Carte> Colectie = citire();
        //subpunctul 1
        Colectie.forEach((id, carte) -> {
            System.out.println("ID: " + id + " Carte: " + carte);
        });

        //subpunctul 2
        System.out.println("\n Se sterge cartea 3:\n");
        Colectie.remove(3);
        Colectie.forEach((id, carte) -> {
            System.out.println("ID: " + id + " Carte: " + carte);
        });

        //subpunctul 3
        System.out.println("\n Adaugarea unei carti noi: \n");
        Carte carte_noua = new Carte("Morometii", "Eu", 1250);
        Colectie.putIfAbsent(3, carte_noua);
        Colectie.forEach((id, carte) -> {
            System.out.println("ID: " + id + " Carte: " + carte);
        });

        //subpunctul 4
        System.out.println("\nScriere de colectie functionala\n");
        scriere(Colectie);

        //subpunctul 5


    }
}
