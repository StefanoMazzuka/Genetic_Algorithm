package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.Gen;
import base.Main;
import cruce.UnPunto;
import funciones.Funcion1;
import mutacion.Mutacion;
import seleccion.Ruleta;

public class Menu extends JFrame {

	private int tamañoPoblacion;
	private double numeroGeneraciones;
	private double porcentageCruce;
	private double porcentageMutacion;
	private double precision;
	private boolean elitismo;

	public Menu() {
		JTextField tamPob = new JTextField();
		JTextField numGen = new JTextField();
		JTextField porCruce = new JTextField();
		JTextField porMuta = new JTextField();
		JTextField preci = new JTextField();
		JCheckBox eliY = new JCheckBox("si", false);
		JCheckBox eliN = new JCheckBox("no", false);
		JLabel empty = new JLabel();
		JButton ok = new JButton("Ok");

		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null); 
		setTitle("Algorithm A*"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminar el programa al pulsar la X

		JPanel checkBoxPanel = new JPanel(new GridLayout(1, 2));
		checkBoxPanel.add(eliY);
		checkBoxPanel.add(eliN);

		JPanel menuPanel = new JPanel(new GridLayout(7, 2));
		menuPanel.add(new JLabel("Tamaño poblacion:"));
		menuPanel.add(tamPob);
		menuPanel.add(new JLabel("Numero de generaciones:"));
		menuPanel.add(numGen);
		menuPanel.add(new JLabel("Porcentage de cruce:"));
		menuPanel.add(porCruce);
		menuPanel.add(new JLabel("Porcentage de mutaciones:"));
		menuPanel.add(porMuta);
		menuPanel.add(new JLabel("Precision:"));
		menuPanel.add(preci);
		menuPanel.add(new JLabel("Elitismo:"));
		menuPanel.add(checkBoxPanel);
		menuPanel.add(empty);
		menuPanel.add(ok);

		add(menuPanel);
		pack();

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (tamPob.getText().equals("")|| numGen.getText().equals("") || porCruce.getText().equals("") ||
						porMuta.getText().equals("") || preci.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor introduzca todos los datos.");
				}

				else {

					tamañoPoblacion = Integer.parseInt(tamPob.getText());
					numeroGeneraciones = Double.parseDouble(numGen.getText());
					porcentageCruce = Double.parseDouble(porCruce.getText());
					porcentageMutacion = Double.parseDouble(porMuta.getText());
					precision = Double.parseDouble(preci.getText());
					
					if (eliY.isSelected() == false && eliN.isSelected() == false) {
						JOptionPane.showMessageDialog(null, "Quiere elitista?.");
					}
					
					else if (eliY.isSelected() == true && eliN.isSelected() == true) {
						JOptionPane.showMessageDialog(null, "Seleccione solo una opcion elitista.");
					}
					
					else if (eliY.isSelected() == true && eliN.isSelected() == false) {
						JOptionPane.showMessageDialog(null, "Opcion elitista.");
					}

					else if (eliY.isSelected() == false && eliN.isSelected() == true) {
						Funcion1 funcion1 = new Funcion1(tamañoPoblacion, precision, 0, 32);
						Ruleta r;
						UnPunto cruce;
						Mutacion mutacion1;

						ArrayList<Gen> p = funcion1.getPoblacion();
						double[] f = funcion1.getFenotipo();

						for (int i = 0; i < numeroGeneraciones; i++) {	
							r = new Ruleta(funcion1, funcion1.getFitness());
							cruce = new UnPunto(porcentageCruce, funcion1);
							mutacion1 = new Mutacion(porcentageMutacion, funcion1);
							System.out.println(i);
							for (int k = 0; k < tamañoPoblacion; k++) {				
								boolean[] ptotal = p.get(k).getAlelos();		
								for (int j = 0; j < ptotal.length; j++) {
									if(ptotal[j] == true) System.out.print(1);
									else System.out.print(0);
								}
								System.out.println();
								System.out.println(f[k]);
								System.out.println();
							}
							p = funcion1.getPoblacion();
							funcion1.calcularFenotipos();
							f = funcion1.getFenotipo();
						}
					}
				} 
			}
		});	
	}
	public int getTamañoPoblacion() {
		return tamañoPoblacion;
	}
	public double getNumeroGeneraciones() {
		return numeroGeneraciones;
	}
	public double getPorcentageCruce() {
		return porcentageCruce;
	}
	public double getPorcentageMutacion() {
		return porcentageMutacion;
	}
	public double getPrecision() {
		return precision;
	}
}
