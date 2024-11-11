package problema3;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.exit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Ilie Maria-Madalina
 * @version 1
 * @since 2024
 */
public class pb3 {
    /**
     * Metoda statica care va salva datele despre piesele de mobilier in fisierul "mobilier.json"
     * @param lista
     */
    public static void scriere(List<Mobilier> lista) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/mobilier.json");
            mapper.writeValue(file, lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda statica care va incarca lista pieselor de mobilier din fisierul "mobilier.json" in program
     * @return
     */
    public static List<Mobilier> citire() {
        try {
            File file = new File("src/main/resources/mobilier.json");
            ObjectMapper mapper = new ObjectMapper();
            List<Mobilier> lista = mapper.readValue(file, new TypeReference<List<Mobilier>>() {});
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Mobilier> lista = citire();
        for (Mobilier m : lista) {
            System.out.println(m);
        }
        scriere(lista);
        Scanner scanner = new Scanner(System.in);
        int opt;
        do {
            System.out.println("\n0.Iesire");
            System.out.println("1.Afisarea elementelor de mobilier din colectie");
            System.out.println("2.Afisarea placilor unei anumite piese de mobilier");
            System.out.println("3.Nr colilor de pal necesare pentru realizarea unui anumit corp de mobile cu dimensiunea de 2800 x 2070 mm");
            System.out.print("Alegeti optiunea: ");
            opt = scanner.nextInt();
            scanner.nextLine();
            String nume_cautat;
            switch (opt) {
                case 0:
                    exit(0);
                    break;
                case 1:
                    /**
                     * Afisarea elementelor de mobilier din colectie si placile care le compun
                     */
                    for(Mobilier m : lista){
                        System.out.println();
                        System.out.println(m);
                    }
                    System.out.println();
                    break;
                case 2:
                    /**
                     * Afisarea caracteristicilor placilor care compun o anumita piesa de mobilier
                     */
                    System.out.print("\nIntroduceti piesa de mobilier cautata: ");
                    nume_cautat = scanner.nextLine();
                    for(Mobilier m : lista){
                        if(m.getNume().equals(nume_cautat)){
                            System.out.println(m);
                        }
                    }
                    break;
                case 3:
                    /**
                     * Afișarea estimativa a numărului colilor de pal necesare pentru realizarea unui anumit corp de mobile știind că o coală de pal are dimensiunea 2800 x 2070 mm
                     */
                    System.out.print("\nIntroduceti corpul de mobila cautat: ");
                    nume_cautat = scanner.nextLine();
                    for(Mobilier m : lista) {
                        if (m.getNume().equals(nume_cautat)) {
                            System.out.println("Pentru " + m.getNume() + " este nevoie de " + m.NrPlaciDePal() + " placi de pal\n");
                        }
                    }
                    break;
                default:
                    System.out.println("Optiune gresita!");
                    break;
            }
        }while(true);
    }
}