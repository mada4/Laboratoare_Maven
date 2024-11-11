package Lab6;

import java.io.File;
import java.io.StringReader;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static java.lang.System.exit;

/**
 * @author Ilie Maria-Madalina
 * @version 1
 * @since 2024
 */
public class ex1 {
    /**
     * Metoda statica care va salva datele despre angajatii firmei in fisierul "angajati.json"
     * @param lista
     */
    public static void scriere(List<Angajat> lista) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file = new File("src/main/resources/angajati.json");
            mapper.writeValue(file, lista);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda statica care va incarca lista angajatilor din fisierul "angajati.json" in program
     * @return
     */
    public static List<Angajat> citire() {
        try {
            File file = new File("src/main/resources/angajati.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> lista = mapper.readValue(file, new TypeReference<List<Angajat>>() {});
            return lista;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {

        List<Angajat> angajati = new ArrayList<>();
        angajati = citire();
        Scanner scanner=new Scanner(System.in);
        int opt;
        System.out.println("\nMeniu:");
        System.out.println("0.Iesire!");
        System.out.println("1.Afișarea listei de angajați!");
        System.out.println("2.Afișarea angajaților care au salariul peste 2500 RON!");
        System.out.println("3.Crearea unei liste cu angajații din luna aprilie, a anului trecut, care au funcție de conducere!");
        System.out.println("4.Afișarea angajaților care nu au funcție de conducere!");
        System.out.println("5.Extragerea din lista de angajați a unei liste de String-uri care conține numele angajaților scrise cu majuscule!");
        System.out.println("6.Afișarea salariilor mai mici de 3000 de RON!");
        System.out.println("7.Afișarea datelor primului angajat al firmei!");
        System.out.println("8.Afișarea de statistici referitoare la salariul angajaților!");
        System.out.println("9.Afișarea unor mesaje care indică dacă printre angajați există cel puțin un “Ion”!");
        System.out.println("10.Afișarea numărului de persoane care s-au angajat în vara anului precedent!");
        do{
            System.out.print("\nIntroduceti optiunea dorita: ");
            opt=scanner.nextInt();
            scanner.nextLine();
            switch (opt){
                case 0:
                    exit(0);
                    break;
                case 1:
                    /**
                     * Afișarea listei de angajați folosind referințe la metode.
                     */
                    System.out.println("\nAfisarea listei cu referinte la metode:");
                    angajati.forEach(System.out::println);
                    break;
                case 2:
                    /**
                     * Afisarea angajatilor care au salariul peste 2500 ron, utilizand stream uri, interfata functionala Predicate, implementata printr o expresie Lambda
                     */
                    System.out.println("\nAngajati cu salariu peste 2500:");
                    angajati
                            .stream()
                            .filter((a)->a.getSalariu()>2500)
                            .forEach(System.out::println);
                    break;
                case 3:
                    /**
                     * Afisarea angajatilor cu functia de conducere sef sau director, din luna aprilie, a anului trecut, utilizand stream uri, expresii Lambda, operatia terminala fiind collect()
                     */
                    System.out.println("\nAngajati din luna aprilie, care au functie de conducere:");
                    List<Angajat> ang=
                            angajati
                                    .stream()
                                    .filter((a)->{
                                        boolean areFunctie = a.getPost().equals("sef") ||a.getPost().equals("director");
                                        boolean lunaAprilie = a.getData_angajarii().getMonth().toString().compareToIgnoreCase("April")==0 && a.getData_angajarii().getYear() == (LocalDate.now().getYear()-1);
                                        return areFunctie && lunaAprilie;
                                    })
                                    .collect(Collectors.toList());

                    ang.forEach(System.out::println);
                    break;
                case 4:
                    /**
                     * Afisarea angajatilor care nu sunt sefi sau directori, in ordine descrescatoare a salariilor, folosind stream uri si expresii Lambda
                     */
                    System.out.println("\nAngajati care nu au functie de conducere in ordine descrescatoare a salariilor:");
                    angajati
                            .stream()
                            .filter((a)-> !(a.getPost().equalsIgnoreCase("sef") ||a.getPost().equalsIgnoreCase("director")))
                            .sorted((a, b)-> a.comparareSalariu(b))
                            .forEach(System.out::println);
                    break;
                case 5:
                    /**
                     * Afisarea listei de string uri care contine numele angajatilor scrise cu majuscule, utilizand stream uri, metoda map() si operatia terminala collect()
                     */
                    System.out.println("\nLista de string-uri care contine numele angajatilor scrise cu majuscule:");
                    List<String> nume_angajati = angajati
                            .stream()
                            .map(Angajat::numeToUpperCase)
                            .collect(Collectors.toList());
                    nume_angajati.forEach(System.out::println);
                    break;
                case 6:
                    /**
                     * Afișarea salariilor mai mici de 3000 de ron, folosind stream-uri, expresii lambda, referințe la metode şi metoda map()
                     */
                    System.out.println("\nSalariile mai mici de 3000:");
                    angajati
                            .stream()
                            .filter((a)->a.getSalariu()<3000)
                            .map(Angajat::getSalariu)
                            .forEach(System.out::println);
                    break;
                case 7:
                    /**
                     * Afișarea datelor primului angajat al firmei
                     */
                    System.out.println("\nPrimul angajat al firmei:");
                    angajati
                            .stream()
                            .min(Comparator.comparing(Angajat::getData_angajarii))
                            .ifPresentOrElse(System.out::println, System.out::println);
                    break;
                case 8:
                    /**
                     * Afișarea de statistici referitoare la salariul angajaților, afișand salariul mediu, salariul minim şi salariul maxim, utilizand stream-uri şi operația terminală collect()
                     */
                    System.out.println("\nStatistici referitoare la salariul angajatilor:");
                    DoubleSummaryStatistics statistics = angajati.stream().collect(Collectors.summarizingDouble(Angajat::getSalariu));
                    System.out.println("Salariul mediu: " + statistics.getAverage());
                    System.out.println("Salariul minim: " + statistics.getMin());
                    System.out.println("Salariul maxim: " + statistics.getMax());
                    break;
                case 9:
                    /**
                     * Afișarea unor mesaje care indică dacă printre angajați există cel puțin un “Ion”
                     */
                    angajati
                            .stream()
                            .filter((a)->a.getNume().equalsIgnoreCase("Ion"))
                            .findAny().ifPresentOrElse(Angajat::existaIon, Angajat::NuexistaIon);
                    break;
                case 10:
                    /**
                     * Afișarea numărului de persoane care s-au angajat în vara anului precedent, utilizand metoda count() din interfaţa Stream
                     */
                    System.out.print("\nNumarul de persoane care s-au angajat in vara anului trecut: ");
                    int nr_angajati = (int) angajati.stream()
                            .filter((a)-> (a.getData_angajarii().getYear()==LocalDate.now().getYear()-1
                                    && (a.getData_angajarii().getMonthValue() == 6
                                    || a.getData_angajarii().getMonthValue() == 7
                                    || a.getData_angajarii().getMonthValue() == 8)))
                            .count();
                    System.out.println(nr_angajati);
                    break;
                default:
                    System.out.println("Optiune gresita!");
                    break;
            }
        }while(opt!=0);
    }
}
