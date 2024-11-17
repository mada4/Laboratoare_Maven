package Lab7.ex1;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;
import static java.util.Arrays.stream;

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
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stergere(Map<Integer, Carte> carti, int id) {
        var entryset = carti.entrySet();
        var it = entryset.iterator();
        while (it.hasNext()) {
            var m = it.next();
            if(m.getKey() == id) {
                it.remove();
            }
        }
    }

    public static void main(String[] args) {
        Map<Integer, Carte> carti = new HashMap<Integer, Carte>();
        carti = citire();
        Scanner scanner = new Scanner(System.in);
        int id;
        String titlul;
        String autorul;
        int anul;
        int opt;
        Set<Carte> carti_set = Set.of();
        System.out.println("\nMeniu: ");
        System.out.println("0.Iesire!");
        System.out.println("1.Afisarea colectiei!");
        System.out.println("2.Stergerea unei carti din colectia Map!");
        System.out.println("3.Adaugarea unei carti la colectia Map!");
        System.out.println("4.Salvarea în fișierul JSON modificările făcute asupra colecției!");
        System.out.println("5.Crearea unei colecții Set<Carte> care extrage din colecția Map cărțile autorului Yual Noah Harari!");
        System.out.println("6.Afisarea ordonata a elementelor din colecția Set, dupa titlul cartii");
        System.out.println("7.Afisarea datelor celei mai vechi cărți din colecția Set");
        System.out.println();
        do {
            System.out.print("Introduceti optiunea: ");
            opt = scanner.nextInt();
            scanner.nextLine();
            switch (opt) {
                case 0:
                    exit(0);
                    break;
                case 1:
                    var entryset = carti.entrySet();
                    var it = entryset.iterator();
                    while (it.hasNext())
                    {
                        var m = it.next();
                        System.out.println(m.getKey() + " : " + m.getValue());
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Introduceti id-ul cartii pe care doriti sa o stergeti: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    stergere(carti, id);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Introduceti o carte noua:");
                    System.out.print("id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("titlul: ");
                    titlul = scanner.nextLine();
                    System.out.print("autorul: ");
                    autorul = scanner.nextLine();
                    System.out.print("anul: ");
                    anul = scanner.nextInt();
                    scanner.nextLine();
                    carti.putIfAbsent(id, new Carte(titlul, autorul, anul));
                    System.out.println();
                    break;
                case 4:
                    scriere(carti);
                    System.out.println("Modificarile au fost salvate in fisier!");
                    scanner.nextLine();
                    break;
                case 5:
                     carti_set =
                            carti.values()
                                    .stream()
                                    .filter((a)->a.autorul().equals("Yuval Noah Harari"))
                                    .collect(Collectors.toSet());
                    System.out.println("\nSetul de carti scrise de autorul Yuval Noah Harari: ");
                    carti_set.forEach(System.out::println);
                    System.out.println();
                    break;
                case 6:
                    System.out.println("\nAfisarea ordonata dupa titlu cartii a elementelor din colectia Set: ");
                    carti_set
                            .stream()
                            .sorted((a, b)-> a.titlul().compareTo(b.titlul()))
                            .forEach(System.out::println);
                    System.out.println();
                    break;
                case 7:
                    System.out.println("\nCea mai veche carte: ");
                    Optional<Carte> carti_vechi = carti_set
                            .stream()
                            .min(Comparator.comparing(Carte::anul));
                    if(carti_vechi.isPresent()) {
                        carti_set
                                .stream()
                                .filter((a) -> a.anul() == carti_vechi.get().anul())
                                .forEach(System.out::println);
                    }
                    else {
                        System.out.println("Cartea nu exista");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Optiune gresita!");
                    break;
            }
        } while (opt != 0);
    }
}
