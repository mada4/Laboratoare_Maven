package problema3;

import java.util.Arrays;

enum Orientare {
    LUNGIME,
    LATIME,
    ORICARE
}

/**
 * Clasa Placa
 * @author Ilie Maria-Mdalina
 * @version 1
 * @since 2024
 */

public class Placa {
    private String descriere;
    private int lungime;
    private int latime;
    private Orientare orientare;
    private boolean[] canturi;
    private int nr_bucati;

    /**
     * Constructorul clasei fara parametrii
     */
    public Placa() {}

    /**
     * Constructorul clasei Placa cu parametrii
     * @param descriere - reprezinta descrierea placilor de pal
     * @param lungime - reprezinta lungimea placii exprimata in milimetrii
     * @param latime - reprezinta latimea piesei exprimata in milimetrii
     * @param orientare - reprezinta orientarea placilor care poate sa fie: lungime, latime, oricare
     * @param canturi - reprezinta o variabila de tip bool care indica prezenta sau absenta cantului pe muchie
     * @param nr_bucati - reprezinta numarul bucatilor
     */
    public Placa(String descriere, int lungime, int latime, Orientare orientare, boolean[] canturi, int nr_bucati) {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.canturi = canturi;
        this.nr_bucati = nr_bucati;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine descrierea placilor de pal
     * @return descriere
     */
    public String getDescriere()
    {
        return descriere;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine lungimea unei placi
     * @return lungimea
     */
    public int getLungime()
    {
        return lungime;
    }

    /**
     * Getter care da acces de citire a variabilei membre ce contine latimea unei placi
     * @return latimea
     */
    public int getLatime()
    {
        return latime;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine orientarea placilor de pal
     * @return orientarea care poate sa fie LUNGIME, LATIME, ORICARE
     */
    public Orientare getOrientare()
    {
        return orientare;
    }

    /**
     * Getter care da acces de citire a variabilei membre ce contine prezenta sau absenta canturilor de pe muchia unei placi de pal
     * @return true or false
     */
    public boolean[] getCanturi()
    {
        return canturi;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine numarul de bucati de pal
     * @return numarul de bucati
     */
    public int getNr_bucati()
    {
        return nr_bucati;
    }

    /**
     * Seteaza descrierea placii
     * @param descriere
     */
    public void setDescriere(String descriere)
    {
        this.descriere = descriere;
    }

    /**
     * Setter pentru lungime
     * @param lungime
     */
    public void setLungime(int lungime)
    {
        this.lungime=lungime;
    }

    /**
     * Setter pentru latime
     * @param latime
     */
    public void setLatime(int latime)
    {
        this.latime=latime;
    }

    /**
     * Setter pentru orientarea placilor
     * @param orientare
     */
    public void setOrientare(Orientare orientare)
    {
        this.orientare=orientare;
    }

    /**
     * Setter pentru canturi
     * @param canturi
     */
    public void setCanturi(boolean[] canturi)
    {
        this.canturi=canturi;
    }

    /**
     * Seteaza numarul de bucati al placilor de pal
     * @param nr_bucati
     */
    public void setNr_bucati(int nr_bucati)
    {
        this.nr_bucati=nr_bucati;
    }

    /**
     * Redefinirea metodei toString() din clasa Object, metodă care va fi utilizată pentru afișare
     * @return caracteristicile placilor separate prin ";"
     */
    @Override
    public String toString() {
        return descriere + ", " + lungime + ", " + latime + ", " + orientare + ", " + Arrays.toString(canturi) + ", " + nr_bucati + '\n';
    }
}
