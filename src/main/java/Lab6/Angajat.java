package Lab6;

import java.time.LocalDate;

/**
 * Clasa Angajat, ce cuprinde evidenta angajatilor unei firme
 * @author Ilie Maria-Madalina
 * @version 1
 * @since 2024
 */
public class Angajat {
    private String nume;
    private String post;
    private LocalDate data_angajarii;
    private float salariu;

    /**
     * Constructor fara parametrii
     */
    public Angajat() {}

    /**
     * Constructorul clasei Angajat cu parametrii
     * @param nume - reprezinta numele angajatilor firmei
     * @param post - reprezinta locul pe care il ocupa un angajat in cadrul firmei
     * @param data_angajarii - reprezinta data in care acesta s a angajat
     * @param salariu - reprezinta salariul pe care il primeste fiecare angajat
     */
    public Angajat(String nume, String post, LocalDate data_angajarii, float salariu) {
        this.nume = nume;
        this.post = post;
        this.data_angajarii = data_angajarii;
        this.salariu = salariu;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine numele angajatului
     * @return numele
     */
    public String getNume()
    {
        return nume;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine postul pe care il ocupa angajatul
     * @return postul
     */
    public String getPost()
    {
        return post;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine data in care a fost angajat in cadrul firmei
     * @return data angajarii
     */
    public LocalDate getData_angajarii()
    {
        return data_angajarii;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine salariul pe care angajatul il primeste
     * @return salariu
     */
    public float getSalariu()
    {
        return salariu;
    }

    /**
     * Seteaza numele angajatului
     * @param nume
     */
    public void setNume(String nume)
    {
        this.nume = nume;
    }

    /**
     * Setter pentru postul ocupat
     * @param post
     */
    public void setPost(String post)
    {
        this.post = post;
    }

    /**
     * Setter pentru data in care a fost angajat
     * @param data_angajarii
     */
    public void setData_angajarii(LocalDate data_angajarii)
    {
        this.data_angajarii = data_angajarii;
    }

    /**
     * Seteaza salariu pe care il primeste un angajat
     * @param salariu
     */
    public void setSalariu(float salariu)
    {
        this.salariu = salariu;
    }

    /**
     * Redefinirea metodei toString() din clasa Object, metodă care va fi utilizată pentru afișare
     * @return datele angajatilor din cadrul firmei, separate prin virgula
     */
    @Override
    public String toString() {
        return nume + "," + post + "," + data_angajarii + "," + salariu;
    }

    public int comparareSalariu(Angajat ang) {
        if (this.salariu <= ang.getSalariu()) {
            return 1;
        }
        return -1;
    }

    /**
     * Returneaza numele angajatilor scrise cu majuscule
     * @return
     */
    public String numeToUpperCase(){
        return nume.toUpperCase();
    }

    /**
     * Se apeleaza prin referinta atunci cand obiectul din clasa Optional, dupa prelucrarea cu Stream API, NU este gol
     */
    public void existaIon(){
        System.out.println("Firma are cel putin un Ion angajat");
    }

    /**
     * Se apeleaza prin referinta atunci cand obiectul din clasa Optional, dupa prelucrarea cu Stream API, ESTE gol
     */
    public static void NuexistaIon(){
        System.out.println("Firma nu are nici un Ion angajat");
    }
}
