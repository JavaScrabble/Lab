import java.util.Objects;

public class Ulamek implements Comparable<Ulamek>{
    int licznik;
    int mianownik;

    public Ulamek(int licznik, int mianownik) {
        this.licznik = licznik;
        this.mianownik = mianownik;
    }

    @Override
    public int compareTo(Ulamek o) {
        int lT = this.licznik;
        int lO = o.licznik;

        if(this.mianownik != o.mianownik){
            lT *= o.mianownik;
            lO *= this.mianownik;
        }
        return Integer.compare(lT, lO);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ulamek u){
            return this.licznik == u.licznik && this.mianownik == u.mianownik;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(licznik, mianownik);
    }

    @Override
    public String toString() {
        return "Ulamek[licznik = %d, mianownik = %d]".formatted(licznik, mianownik);
    }
}