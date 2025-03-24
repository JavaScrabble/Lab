import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class Set<T extends Comparable> {
    private T[] set;
    private int pojemnosc;
    private int rozmiar;

    // Konstruktor
    public Set(int pojemnosc) {
        this.pojemnosc = pojemnosc;
        this.rozmiar = 0;
        set = (T[]) new Comparable[pojemnosc];
    }


    public boolean dodajElement(T element) {

        // sprawdz czy zbior jest pełny
        if (rozmiar == pojemnosc) {
            throw new RuntimeException("Zbiór jest pełny");
        }

        if (szukaj(element) != -1)
            return false;

        int elPozycja = 0;
        int i = 0;
        for (i=0; i<rozmiar; i++){
            if (set[i].compareTo(element) > 0){
                break;
            }
        }
        elPozycja = i;

        for (int j = rozmiar; j > elPozycja; j--) {
            set[j] = set[j-1];
        }

        set[elPozycja] = element;
        rozmiar++;
        return true;
    }

    public int szukaj(T element) {
        for (int i=0; i<rozmiar; i++)
            if (set[i].equals(element))
                return i;

        return -1;
    }

    @Override
    public String toString() {
        return "Rozmiar: " + rozmiar + "\nPojemność: " + pojemnosc + "\nZbiór: "
                + Arrays.toString(set);
    }

    public boolean usunElement(T element) {
        int elPozycja = szukaj(element);
        if (elPozycja == -1)
            return false;

        for (int i = elPozycja; i < rozmiar-1; i++) {
            set[i] = set[i+1];
        }
        set[rozmiar-1] = null;
        rozmiar--;
        return true;
    }

    public Set<T> dodajElementy(Set<T> o){
        Set<T> nowySet = new Set<>(o.rozmiar + this.rozmiar);
        for(int i=0; i<o.rozmiar; i++) {
            nowySet.dodajElement(o.set[i]);
        }

        for(int i=0; i<this.rozmiar; i++) {
            nowySet.dodajElement(this.set[i]);
        }

        return nowySet;
    }

    public void odejmijElementy(Set<T> o){
        for(int i = 0; i < o.rozmiar; i++) {
            usunElement(o.set[i]);
        }
    }

    public Set<T> przeciecie(Set<T> o){
        Set<T> nowy;
        Set<T> iterowany;
        Set<T> sprawdzany;

        if(o.rozmiar > this.rozmiar) {
            iterowany = this;
            sprawdzany = o;
        }
        else {
            iterowany = o;
            sprawdzany = this;
        }

        nowy = new Set<>(sprawdzany.rozmiar);

        for(int i = 0; i < iterowany.rozmiar; i++){
            if(sprawdzany.szukaj(iterowany.set[i]) != -1)
                nowy.dodajElement(iterowany.set[i]);
        }

        return nowy;
    }
}
