package domain;

public class Consumering {
    private String ConsumeringNaam;
    private double Prijs;
    private String Type;
    int hoeveelheid = 0;
    
public Consumering( String Naam, double Bedrag, String Soort ){
    ConsumeringNaam = Naam;
    Prijs = Bedrag;
    Type = Soort;
}

public void newConsumering( String Naam, double Bedrag, String Soort) {
    ConsumeringNaam = Naam;
    Prijs = Bedrag;
    Type = Soort;
}

public void setPrice( double Bedrag) {
    Prijs = Bedrag;
}

public void setName( String Naam) {
    ConsumeringNaam = Naam;
}

public void setType( String Soort ){
    Type = Soort;
}

public void upcountHoeveelheid(){
    hoeveelheid += 1;
}

public void downcountHoeveelheid() {
    hoeveelheid -= 1;
}

public int getHoeveelheid() {
    return hoeveelheid;
}

public double getPrijs() {
    return Prijs;
}

public String getNaam() {
    return ConsumeringNaam;
}

public void reset(){
    hoeveelheid = 0;
}

}
