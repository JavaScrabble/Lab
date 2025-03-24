public class Main {
        public static void main(String[] args) {
            Set<Pacjent> pacjenci1= new Set<>(3);
            Pacjent jan = new Pacjent(23, 1);
            Pacjent stachu = new Pacjent(44, 1);
            Pacjent dawid = new Pacjent(12, 2);

            pacjenci1.dodajElement(jan);
            pacjenci1.dodajElement(stachu);
            pacjenci1.dodajElement(dawid);

            System.out.println("Zbiór pacjenci1\n" + pacjenci1);

            Set<Pacjent> pacjenci2= new Set<>(3);
            pacjenci2.dodajElement(new Pacjent(10, 6));
            pacjenci2.dodajElement(dawid);
            Set<Pacjent> pacjenci3 = pacjenci2.dodajElementy(pacjenci1);
            System.out.println("Zbiór pacjenci3\n" + pacjenci3);

            System.out.println("------");

            Set<Ulamek> ulamki1 = new Set<>(2);
            Ulamek a = new Ulamek(2, 5);
            Ulamek b = new Ulamek(5, 2);

            ulamki1.dodajElement(a);
            ulamki1.dodajElement(b);

            System.out.println("Zbiór ulamki1\n" + ulamki1);

            Set<Ulamek> ulamki2 = new Set<>(2);
            ulamki2.dodajElement(a);
            Set<Ulamek> ulamki3 = ulamki1.przeciecie(ulamki2);
            System.out.println("Zbiór ulamki3\n" + ulamki3);

            ulamki3.odejmijElementy(ulamki1);
            System.out.println("Zbiór ulamki3 po usunięciu elementów, które są w ulamki1\n" + ulamki3);

        }
}