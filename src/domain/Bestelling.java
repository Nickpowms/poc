package domain;

import java.util.ArrayList;

public class Bestelling {
    private ArrayList<Consumering> product;
    
    public Bestelling(){
        product = new ArrayList();
    }
    
    public void voegToe(Consumering consumering) {
        product.add(consumering);
    }
    
    public void verwijder(Consumering consumering) {
        product.remove(consumering);
    }
    
    public String getBestelling() {
        
        String overzicht = "";
        
        for(Consumering p : product) {
            if (!(p.getHoeveelheid() <= 0)){
                overzicht += p.getHoeveelheid() + "x " + p.getNaam() + " bedraagt € " + p.getHoeveelheid() * p.getPrijs() + "\n";
            }
        }
        return overzicht;
    }
    
    public void leegBestelling() {
        product.clear();
    }
    
    public double getTotaalPrijs() {
        
        double totaalprijs = 0;
        for(Consumering p : product) {
                totaalprijs += p.getHoeveelheid() * p.getPrijs();
        }
        return totaalprijs;
    }
    
}
// sup mydood