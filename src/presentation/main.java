package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import domain.Bestelling;
import domain.Consumering;



public class main extends JFrame {

	public static void main(String[] args) {
            //Aanmaken van frame en paneel
		JFrame frame = new JFrame();
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Bestellingsoverzicht");
		
		JPanel paneel = new Paneel();
	    frame.setContentPane( paneel );
	    frame.setVisible(true);
	}
	

}

class Paneel extends JPanel {
	private static final long serialVersionUID = 1L;
        //Declareren van alle componenten die aanwezig zijn
        //op het paneel.
        private Bestelling bestelling1;
        private JButton knopCola, knopLasagna, Bevestig, knopMinCola, knopMinLasagna, opnieuw, knopBetaal, knopNieuwBestelling;
        private JScrollPane scroll;
        private JTextArea textOverzicht;
        private Consumering Lasagna, Cola;
        private JTextField ColaHv, LasagnaHv;
        private JLabel empty1, empty2, labelCola, labelLasagna;
        private String betaalVerzoekIngedient;
	
	  public Paneel() {
              //Grid layout met oneindig aantal rijen en een maximum van 4 kolommen
              setLayout(new GridLayout( 0, 4, 5, 5));
                  
                  //-=[Klassen]=-
                  //Consumeringen
                  Cola = new Consumering("Cola", 2.00, "Drank");
                  Lasagna = new Consumering("Lasagna", 7.00, "Gerecht");
                  //Bestelling
                  bestelling1 = new Bestelling();
                  //
                  
                  //Aanmaken van de componenten op het paneel
                  //waar nodig worden er actionhandlers toegevoegd.
                  opnieuw = new JButton("Bestelling aanpassen");
                  opnieuw.setEnabled(false);
                  opnieuw.addActionListener(new opnieuwHandler());
                  
                  Bevestig = new JButton("Bevestig");
                  Bevestig.addActionListener(new bevestigHandler());
                  
                  knopBetaal = new JButton("Betaal");
                  knopBetaal.addActionListener(new betaalHandler());
                  knopBetaal.setVisible(false);
                  
                  knopCola = new JButton("+");
                  knopCola.addActionListener(new colaHandler());
                  
                  knopMinCola = new JButton("-");
                  knopMinCola.addActionListener(new colaMinHandler());
                  knopMinCola.setEnabled(false);
                  
                  ColaHv = new JTextField(10);
                  ColaHv.setEditable(false);
                  
                  labelCola = new JLabel("Cola");
                  
                  knopLasagna = new JButton("+");
                  knopLasagna.addActionListener(new lasagnaHandler());
                  
                  knopMinLasagna = new JButton("-");
                  knopMinLasagna.addActionListener(new lasagnaMinHandler());
                  knopMinLasagna.setEnabled(false);
                  
                  labelLasagna = new JLabel("Lasagna");
                  
                  LasagnaHv = new JTextField(10);
                  LasagnaHv.setEditable(false);
		  
                  textOverzicht = new JTextArea(10, 8);
                  textOverzicht.setEditable(false);
                  
                  //Geeft de JTextArea met de naam textOverzicht 
                  //scrollbalken.
                  scroll = new JScrollPane(textOverzicht);
                  
                  empty1 = new JLabel("");
                  empty2 = new JLabel("");
                  
                  knopNieuwBestelling = new JButton("Nieuwe bestelling");
                  knopNieuwBestelling.addActionListener(new resetHandler());
                  knopNieuwBestelling.setVisible(false);
                  
                  //Voeg alle componenten
                  add(labelCola);
                  add(knopCola);
                  add(knopMinCola);
                  add(ColaHv);
                  add(labelLasagna);
                  add(knopLasagna);
                  add(knopMinLasagna);
                  add(LasagnaHv);
                  add(empty1);
                  add(Bevestig);
                  add(scroll);
                  add(opnieuw);
                  add(empty2);
                  add(knopBetaal);
                  add(knopNieuwBestelling);

	  }
          
          //-=[Actionhandlers]=-
            class colaHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              if(Cola.getHoeveelheid() == 0) {
                                  if(!(bestelling1.getBestelling().contains("Cola"))) {
                                         bestelling1.voegToe(Cola);
                                         Cola.upcountHoeveelheid();
                                  }
                                  else {
                                      Cola.upcountHoeveelheid();
                                  }
                              }
                              else {
                                  Cola.upcountHoeveelheid();
                              }
                              knopMinCola.setEnabled(true);
                              int ColaAant = Cola.getHoeveelheid();
                              String ColaAantal = Integer.toString(ColaAant);
                              
                              double ColaPrijs = Cola.getPrijs() * ColaAant;
                              String ColaPrijsS = Double.toString(ColaPrijs);
                              
                              ColaHv.setText(ColaAantal + " stuks à €" + Cola.getPrijs() + " per stuk. Totaal: €" + ColaPrijsS);
                          }
                  }
            
            class colaMinHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              if(Cola.getHoeveelheid() == 0) {
                                  bestelling1.verwijder(Cola);
                                  return;
                              }
                              else {
                                  Cola.downcountHoeveelheid();
                              }
                              //Na de if/else statement nog een keer checken of knopMinCola
                              //moet worden disabled.
                              if(Cola.getHoeveelheid() == 0) {
                                      knopMinCola.setEnabled(false);
                              }
                              int ColaAant = Cola.getHoeveelheid();
                              String ColaAantal = Integer.toString(ColaAant);
                              
                              double ColaPrijs = Cola.getPrijs() * ColaAant;
                              String ColaPrijsS = Double.toString(ColaPrijs);
                              
                              if(Cola.getHoeveelheid() == 0){
                                  bestelling1.verwijder(Cola);
                              }
                              
                              ColaHv.setText(ColaAantal + " stuks à €" + Cola.getPrijs() + " per stuk. Totaal: €" + ColaPrijsS);
                          }
                  }
            
            class lasagnaHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              if(Lasagna.getHoeveelheid() == 0) {
                                         bestelling1.voegToe(Lasagna);
                                         Lasagna.upcountHoeveelheid();
                              }
                              else {
                                  Lasagna.upcountHoeveelheid();
                              }
                              knopMinLasagna.setEnabled(true);
                              int LasagnaAant = Lasagna.getHoeveelheid();
                              String LasagnaAantal = Integer.toString(LasagnaAant);
                              
                              double LasagnaPrijs = Lasagna.getPrijs() * LasagnaAant;
                              String LasagnaPrijsS = Double.toString(LasagnaPrijs);
                              
                              LasagnaHv.setText(LasagnaAantal + " stuks à €" + Lasagna.getPrijs() + " per stuk. Totaal: €" + LasagnaPrijsS);
                           }
                  }
            
            class lasagnaMinHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              if(Lasagna.getHoeveelheid() == 0) {
                                  bestelling1.verwijder(Lasagna);
                                  return;
                              }
                              else {
                                  Lasagna.downcountHoeveelheid();
                              }
                              //Na de if/else statement nog een keer checken of knopMinLasagna
                              //moet worden disabled.
                              if(Lasagna.getHoeveelheid() == 0){
                                      knopMinLasagna.setEnabled(false);
                              }
                              int LasagnaAant = Lasagna.getHoeveelheid();
                              String LasagnaAantal = Integer.toString(LasagnaAant);
                              
                              double LasagnaPrijs = Lasagna.getPrijs() * LasagnaAant;
                              String LasagnaPrijsS = Double.toString(LasagnaPrijs);
                              
                              LasagnaHv.setText(LasagnaAantal + " stuks à €" + Lasagna.getPrijs() + " per stuk. Totaal: €" + LasagnaPrijsS);
                              
                              if(Lasagna.getHoeveelheid() == 0){
                                  bestelling1.verwijder(Lasagna);
                              }
                          }
                  }
            
            class bevestigHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              knopBetaal.setEnabled(true);
                              
                              if(bestelling1.getBestelling() == "") {
                                  knopBetaal.setEnabled(false);
                              }
                              
                              if(Lasagna.getHoeveelheid() <= 0){
                                  bestelling1.verwijder(Lasagna);
                              }
                              if(Cola.getHoeveelheid() <= 0){
                                  bestelling1.verwijder(Cola);
                              }
                              knopCola.setEnabled(false);
                              knopLasagna.setEnabled(false);
                              knopMinCola.setEnabled(false);
                              knopMinLasagna.setEnabled(false);
                              Bevestig.setEnabled(false);
                              knopBetaal.setVisible(true);
                              
                              opnieuw.setEnabled(true);
                              
                              textOverzicht.setText(bestelling1.getBestelling() + "\n Subtotaal: €" + bestelling1.getTotaalPrijs());
                          }
                  }
            
            class opnieuwHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              knopCola.setEnabled(true);
                              knopLasagna.setEnabled(true);
                              knopMinCola.setEnabled(true);
                              knopMinLasagna.setEnabled(true);
                              Bevestig.setEnabled(true);
                              knopBetaal.setVisible(false);
                              
                              opnieuw.setEnabled(false);
                              textOverzicht.setText("");
                              
                          }
                  }
            class betaalHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              knopCola.setEnabled(false);
                              knopLasagna.setEnabled(false);
                              knopMinCola.setEnabled(false);
                              knopMinLasagna.setEnabled(false);
                              Bevestig.setEnabled(false);
                              knopBetaal.setVisible(false);
                              knopNieuwBestelling.setVisible(true);
                              
                              opnieuw.setEnabled(false);
                              
                              textOverzicht.setText(bestelling1.getBestelling() + "\n Subtotaal: €" + bestelling1.getTotaalPrijs() + "\n \n Verzoek tot betaling is doorgegeven.");
                              bestelling1.leegBestelling();
                          }
                  }
            
            class resetHandler implements ActionListener {
                         public void actionPerformed( ActionEvent e )
                          {
                              bestelling1.leegBestelling();
                              ColaHv.setText("");
                              LasagnaHv.setText("");
                              knopNieuwBestelling.setVisible(false);
                              textOverzicht.setText("");
                              
                              knopCola.setEnabled(true);
                              knopLasagna.setEnabled(true);
                              knopMinCola.setEnabled(true);
                              knopMinLasagna.setEnabled(true);
                              Bevestig.setEnabled(true);
                              knopBetaal.setVisible(false);
                              
                              Cola.reset();
                              Lasagna.reset();
                          }
                  }
            
     }




