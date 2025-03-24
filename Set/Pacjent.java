import java.util.Objects;

public class Pacjent implements Comparable<Pacjent> {
    int wiek;
    int priorytet;

    public Pacjent(int wiek, int priorytet) {
        this.wiek = wiek;
        this.priorytet = priorytet;
    }

    @Override
    public int compareTo(Pacjent o) {
        if(this.priorytet != o.priorytet)
            return Integer.compare(this.priorytet, o.priorytet);
        else
            return Integer.compare(this.wiek, o.wiek);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pacjent p) {
            return wiek == p.wiek && priorytet == p.priorytet;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wiek, priorytet);
    }

    @Override
    public String toString() {
        return "Pacjent[wiek = %d, priorytet = %d]".formatted(wiek, priorytet);
    }
}