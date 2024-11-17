package Lab7.ex2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;
import static java.util.Arrays.stream;

public class problema2 {
    public static void scriere(Set<InstrumentMuzical> instrumente) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/instrumente.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writeValue(file, instrumente);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> citire() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/instrumente.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            return mapper.readValue(file, new TypeReference<HashSet<InstrumentMuzical>>() {});
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Set<InstrumentMuzical> instrumente = new HashSet<InstrumentMuzical>();
        Scanner scanner = new Scanner(System.in);
        instrumente.add(new Chitara("Rocktile", 1400, TipChitara.valueOf("CLASICA"), 6));
        instrumente.add(new Chitara("Ibanez", 5000, TipChitara.valueOf("ELECTRICA"), 7));
        instrumente.add(new Chitara("Takamine", 750, TipChitara.valueOf("ACUSTICA"), 8));
        instrumente.add(new SetTobe("Efnote", 3900, TipTobe.valueOf("ACUSTICE"), 4,2));
        instrumente.add(new SetTobe("Groove", 890, TipTobe.valueOf("ACUSTICE"), 6,3));
        instrumente.add(new SetTobe("Fender", 1500, TipTobe.valueOf("ELECTRONICE"), 5,4));
        scriere(instrumente);
        System.out.println(instrumente);
        instrumente=citire();
        int opt;
        System.out.println("\nMeniu: ");
        System.out.println("0.Iesire!");
        System.out.println("1.Implementarea utilizata pentru interfata Set de catre ObjectMapper!");
        System.out.println("2.Verificarea dacă colecția Set permite sau nu duplicate!");
        System.out.println("3.Stergerea instrumentelor din Set al căror preț este mai mare de 3000 de RON!");
        System.out.println("4.Afisarea tuturor datelor chitărilor!");
        System.out.println("5.Afisarea tuturor datelor tobelor!");
        System.out.println("6.Afisarea datelor chitării care are cele mai multe corzi!");
        System.out.println("7.Afisarea datelor tobelor acustice, ordonat după numărul de tobe din set!");
        System.out.println();
        do{
            System.out.print("Introduceti optiunea: ");
            opt=scanner.nextInt();
            scanner.nextLine();
            switch (opt)
            {
                case 0:
                    exit(0);
                    break;
                case 1:
                    System.out.println("Implementarea utilizata pentru interfata Set: " + instrumente.getClass().getName());
                    System.out.println();
                    break;
                case 2:
                    InstrumentMuzical verificare=instrumente.iterator().next();
                    boolean aux=instrumente.add(verificare);
                    if(aux)
                        System.out.println("Colectia Set permite duplicate!");
                    else System.out.println("Colectia Set NU permite duplicate!");
                    System.out.println();
                    break;
                case 3:
                    instrumente.removeIf((a) -> a.getPret()>3000);
                    instrumente.forEach(System.out::println);
                    System.out.println();
                    break;
                case 4:
                    instrumente
                            .stream()
                            .filter((a) -> a instanceof Chitara)
                            .forEach(System.out::println);
                    System.out.println();
                    break;
                case 5:
                    instrumente
                            .stream()
                            .filter((a) -> a.getClass() == SetTobe.class)
                            .forEach(System.out::println);
                    System.out.println();
                    break;
                case 6:
                    Optional<InstrumentMuzical> opt_multe_corzi =
                            instrumente
                                    .stream()
                                    .filter((a)->a instanceof Chitara)
                                    .max(Comparator.comparing((a)->((Chitara)a).getNr_corzi()));
                    opt_multe_corzi.ifPresent(System.out::println);
                    System.out.println();
                    break;
                case 7:
                    instrumente
                            .stream()
                            .filter((a)->a instanceof SetTobe)
                            .sorted((a, b) -> ((SetTobe) a).getNr_tobe()-((SetTobe) b).getNr_tobe())
                            .forEach(System.out::println);
                    System.out.println();
                    break;
                default:
                    System.out.println("Optiune gresita!");
                    break;
            }
        }while(opt!=0);
    }
}
