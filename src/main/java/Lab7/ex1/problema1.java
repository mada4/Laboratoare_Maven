package Lab7.ex1;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class problema1 {

    public static void scriere(Map<Integer, Carte> carti) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file = new File("src/main/resources/carti.json");
            mapper.writeValue(file, carti);
        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, Carte> citire() {
        try {
            File file = new File("src/main/resources/carti.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            Map<Integer, Carte> carti = mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {
            });
            return carti;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opt;
        System.out.println("\nMeniu: ");
        System.out.println("0.Iesire!");
        System.out.println("1.Afisarea colectiei!");
        System.out.println("2.Stergerea unei carti din colectia Map!");
        System.out.println("3.Adaugarea unei carti la colectia Map!");
        System.out.println("4.Salvarea în fișierul JSON modificările făcute asupra colecției!");
        System.out.println("5.Crearea unei colecții Set<Carte> care extrage din colecția Map cărțile autorului Yual Noah Harari!");
        System.out.println("6.Afisarea ordonata a elementelor din colecția Set, dupa titlul cartii");
        System.out.println("7.Afisarea datelor celei mai vechi cărți din colecția Set");
        do {
            System.out.println("Introduceti optiunea: ");
            opt = scanner.nextInt();
            scanner.nextLine();
            switch (opt) {
                case 0:
                    exit(0);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        } while (opt != 0);
    }
}
