package problema2;
import java.math.*;

/**
 * Clasa PerecheNumere care are variabilele membre private doi intregi
 * @author Ilie Maria-Madalina
 * @version 1
 * @since 2024
 */
public class PerecheNumere {
    public int x;
    public int y;

    /**
     * Constructor fara parametrii
     */
    public PerecheNumere() {}

    /**
     * Constructorul clasei PerecheNumere cu parametrii
     * @param x-primul numar intreg
     * @param y-al doilea numar intreg
     */
    public PerecheNumere(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine primul numar din pereche
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Getter care da acces de citire a variabilei membre care contine cel de al doilea numar din pereche
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * Setter pentru x
     * @param x
     */
    public void setX(int x)
    {
        this.x=x;
    }

    /**
     * Setter pentru y
     * @param y
     */
    public void setY(int y)
    {
        this.y=y;
    }

    /**
     * Redefinirea metodei toString() din clasa Object, metodă care va fi utilizată pentru afișare
     * @return valorile lui x si y, separate prin virgula
     */
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    /**
     * Metoda ce returneaza o valoare booleana care indica daca cele doua numere care formeaza perechea sunt numere consecutive in sirul lui Fibonacci
     * @param a
     * @param b
     * @return
     */
    public boolean Fibonacci(int a, int b) {
        if((x == a && y == b) || (y == a && x == b)) {
            return true;
        }
        if(x < b || y < b) {
            return false;
        }
        return this.Fibonacci(b, a+b);
    }

    /**
     * Metoda care returneaza cel mai mic multiplu comun al celor doua numere
     * @return
     */
    public int cmmmc() {
        int a = x, b = y;
        while(a != b) {
            if(a > b)
                a=a-b;
            else
                b=b-a;
        }
        int cmmdc = a;
        return x*y/cmmdc;
    }

    /**
     * Metoda care va returna boolean daca cele doua numere au suma cifrelor egala
     * @return
     */
    public boolean sumaCifrelorEgala(){
        int a = x, b = y;
        int sumaa = 0, sumab = 0;
        while(a != 0){
            sumaa += a%10;
            a/=10;
        }
        while(b != 0) {
            sumab += b%10;
            b/=10;
        }
        return sumaa == sumab;
    }

    /**
     * Metoda care va returna boolean daca cele doua numere au acelasi numar de cifre pare
     * @return
     */
    public boolean acelasiNrCifrePare() {
        int a = x, b = y;
        int nra = 0, nrb = 0;
        while(a != 0){
            if(a%2 == 0)
                nra++;
            a=a/10;
        }
        while(b != 0) {
            if(b%2 == 0)
                nrb++;
            b=b/10;
        }
        return nra == nrb;
    }
}