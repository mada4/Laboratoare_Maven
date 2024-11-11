package problema3;

import java.util.List;
import java.util.Objects;

/**
 * Clasa Mobilier
 * @author Ilie Maria-Madalina
 * @version 1
 * @since 2024
 */
public class Mobilier {
    private String nume;
    private List<Placa> placi;

    /**
     * Constructorul clasei fara parametrii
     */
    public Mobilier() {}

    /**
     * Constructorul clasei Mobilier cu parametrii
     * @param nume - reprezinta numele pieselor de mobilier
     * @param placi - cuprinde informatiile privind placile de pal care compun piesa de mobilier
     */
    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine numele pieselor
     * @return
     */
    public String getNume()
    {
        return nume;
    }

    /**
     * Getter care da acces de citire a variabilei membre ce contine placile ce compus piesa de mobilier
     * @return
     */
    public List<Placa> getPlaci()
    {
        return placi;
    }

    /**
     * Seteaza numele pieselor de mobilier
     * @param nume
     */
    public void setNume(String nume)
    {
        this.nume = nume;
    }

    /**
     * Setter pentru placi
     * @param placi
     */
    public void setPlaci(List<Placa> placi)
    {
        this.placi = placi;
    }

    /**
     * Redefinirea metodei toString() din clasa Object, metodă care va fi utilizată pentru afișare
     * @return caracteristicile pieselor de mobilier
     */
    @Override
    public String toString() {
        return "Mobilier{" + "nume='" + nume + '\'' + ", placi=" + placi + '}';
    }

    public int NrPlaciDePal() {
        int ariaTotalaCorp = 0;
        int ariaPal = 2800 * 2070;
        for(Placa p : placi) {
            ariaTotalaCorp += p.getLatime()*p.getLungime();
        }

        System.out.println(ariaPal);
        System.out.println(ariaTotalaCorp);
        return ariaTotalaCorp/ariaPal + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mobilier mobilier = (Mobilier) o;
        return Objects.equals(nume, mobilier.nume) && Objects.equals(placi, mobilier.placi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, placi);
    }
}
