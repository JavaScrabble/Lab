import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileNameSeans = "seans.dat";
        String fileNameKlient = "klient.xml";




        // ------- Seansy --------
        // tworzymy obiekt ObjectOutputStream do zapisywania do pliku
        ObjectOutputStream wy = new ObjectOutputStream(new FileOutputStream(fileNameSeans));


        // tworzenie seansu
        Seans seans1 = new Seans("Matrix", LocalDate.of(2025, 5, 15), LocalTime.of(19, 30), 16);


        // ---- zapis ---
        wy.writeObject(seans1);
        wy.writeObject(new Seans("Get Out", LocalDate.of(2025, 3, 12), LocalTime.of(19, 00), 16));
        wy.close();


        //--- odczyt ------
        // tworzymy obiekt klasy ObjectInputStream do odczytywania z pliku
        ObjectInputStream we = new ObjectInputStream(new FileInputStream(fileNameSeans));


        // odczytujemy z pliku;
        Seans s1 = (Seans) we.readObject();
        Seans s2 = (Seans) we.readObject();


        //  wyświetlamy wynik
        System.out.println("Odczytane seansy z pliku:");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("-----------");
        we.close();


        // ----- Klient ---------
        // XStream
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY); //forbid everything
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES); // allow primitive types
        // tworzenie klienta
        Klient klient1 = new Klient("Nowak", "Jan", "jan.nowak@gmail.com", "123456789", seans1);


        // rezerwacja miejsc
        klient1.rezerwujMiejsce('A', 3);
        klient1.rezerwujMiejsce('B', 5);


        // wyświetlenie informacji o rezerwacji
        klient1.wyswietlRezerwacje();
        seans1.wyswietlMiejsca();
        System.out.println("-----------");


        // zapisywanie obiektu do pliku
        PrintWriter writer = new PrintWriter(fileNameKlient);
        xstream.toXML(klient1, writer);
        writer.close();


        // powtorny zapis i odczyt seansu1
        wy = new ObjectOutputStream(new FileOutputStream(fileNameSeans));
        wy.writeObject(seans1);
        wy.close();
        // tworzymy obiekt klasy ObjectInputStream do odczytywania z pliku
        we = new ObjectInputStream(new FileInputStream(fileNameSeans));


        // odczytujemy z pliku;
        Seans s11 = (Seans) we.readObject();
        we.close();
        // Wyświetlamy
        System.out.println("Odczytany seans1 z pliku po rezerwacjach:");
        System.out.println(s11);
        s11.wyswietlMiejsca();
        System.out.println("-----------");


        // ----------- Odczyt danych klienta z pliku -------------------------------------------


        // odczyt obiekta z pliku
        File plikKlient = new File(fileNameKlient);


        Klient odczytanyKlient1 = (Klient) xstream.fromXML(plikKlient);


        // wyswietlenie odczytanych danych
        System.out.println("Odczytany klient z pliku:");
        odczytanyKlient1.wyswietlRezerwacje();
    }
}
