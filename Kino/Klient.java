import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


class Klient {
    private String nazwisko;
    private String imie;
    private String mail;
    private String telefon;
    private Seans seans;
    private List<String> miejsca;


    public Klient(String nazwisko, String imie, String mail, String telefon, Seans seans) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.mail = mail;
        this.telefon = telefon;
        this.seans = seans;
        this.miejsca = new ArrayList<>();
    }


    public boolean rezerwujMiejsce(char rzad, int miejsce) {
        if (seans.rezerwujMiejsce(rzad, miejsce)) {
            miejsca.add(rzad + String.valueOf(miejsce));
            return true;
        }
        return false;
    }


    public void wyswietlRezerwacje() {
        System.out.println("Rezerwacja klienta: " + imie + " " + nazwisko);
        System.out.println("Seans: " + seans);
        System.out.println("Miejsca: " + String.join(", ", miejsca));
    }


    @Override
    public String toString() {
        return "Klient{" +
                "nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", mail='" + mail + '\'' +
                ", telefon='" + telefon + '\'' +
                ", seans=" + seans +
                ", miejsca=" + miejsca +
                '}';
    }


    // ---------------- Zapis i odczyt XML ----------------


    public void zapiszDoXML(String fileName) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("klient", Klient.class);


        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(xstream.toXML(this));
            System.out.println("Dane klienta zapisane do XML: " + fileName);
        } catch (IOException e) {
            System.err.println("Błąd zapisu do XML: " + e.getMessage());
        }
    }


    public static Klient odczytajZXML(String fileName) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("klient", Klient.class);


        try (FileReader reader = new FileReader(fileName)) {
            return (Klient) xstream.fromXML(reader);
        } catch (IOException e) {
            System.err.println("Błąd odczytu z XML: " + e.getMessage());
            return null;
        }
    }
}
