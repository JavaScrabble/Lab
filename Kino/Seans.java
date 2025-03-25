import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;


class Seans implements Serializable {
    private static final long serialVersionUID = 1L;


    private String tytul;
    private LocalDate dzien;
    private LocalTime godzina;
    private int ograniczenieWiekowe;
    private HashMap<Character, HashMap<Integer, Boolean>> liczbaMiejsc;


    public Seans(String tytul, LocalDate dzien, LocalTime godzina, int ograniczenieWiekowe) {
        this.tytul = tytul;
        this.dzien = dzien;
        this.godzina = godzina;
        this.ograniczenieWiekowe = ograniczenieWiekowe;
        this.liczbaMiejsc = new HashMap<>();


        // inicjalizacja miejsc w rzędach (A-F, 1-10)
        for (char rzad = 'A'; rzad <= 'F'; rzad++) {
            liczbaMiejsc.put(rzad, new HashMap<>());
            for (int miejsce = 1; miejsce <= 10; miejsce++) {
                liczbaMiejsc.get(rzad).put(miejsce, false); // false = miejsce wolne
            }
        }
    }


    public boolean rezerwujMiejsce(char rzad, int miejsce) {
        if (liczbaMiejsc.containsKey(rzad) && liczbaMiejsc.get(rzad).containsKey(miejsce)) {
            if (!liczbaMiejsc.get(rzad).get(miejsce)) {
                liczbaMiejsc.get(rzad).put(miejsce, true);
                return true;
            }
        }
        return false;
    }


    public void wyswietlMiejsca() {
        System.out.println("Miejsca na seans: " + tytul);
        for (char rzad : liczbaMiejsc.keySet()) {
            System.out.print(rzad + ": ");
            for (int miejsce : liczbaMiejsc.get(rzad).keySet()) {
                System.out.print(liczbaMiejsc.get(rzad).get(miejsce) ? "[X] " : "[O] ");
            }
            System.out.println();
        }
    }


    @Override
    public String toString() {
        return "Seans{" +
                "tytul='" + tytul + '\'' +
                ", dzien=" + dzien +
                ", godzina=" + godzina +
                ", ograniczenieWiekowe=" + ograniczenieWiekowe +
                '}';
    }
}
