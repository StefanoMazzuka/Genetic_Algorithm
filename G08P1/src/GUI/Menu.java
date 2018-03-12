package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private int porcentageCruce;
	private int porcentageMutacion;
	private int precision;
	private int elitismo;
	
	public Menu() {
		JTextField tamPob = new JTextField();
		JTextField numGen = new JTextField();
		JTextField porCruce = new JTextField();
		JTextField porMuta = new JTextField();
		JTextField preci = new JTextField();
		JTextField eli = new JTextField();
		JLabel empty = new JLabel();
		JButton ok = new JButton("Ok");
		
		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null); 
		setTitle("Algorithm A*"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminar el programa al pulsar la X
		
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
		menuPanel.add(new JLabel("Elitismo y/n:"));
		menuPanel.add(eli);
		menuPanel.add(empty);
		menuPanel.add(ok);
		
		add(menuPanel);
		pack();
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tamañoPoblacion = Integer.parseInt(tamPob.getText());
				numeroGeneraciones = Double.parseDouble(numGen.getText());
				porcentageCruce = Integer.parseInt(porCruce.getText());
				porcentageMutacion = Integer.parseInt(porMuta.getText());
				precision = Integer.parseInt(preci.getText());
				elitismo = Integer.parseInt(eli.getText());
				
				Funcion1 funcion1 = new Funcion1(tamañoPoblacion, numeroGeneraciones, 0, 32);
				
					
				ArrayList<Gen> p = funcion1.getPoblacion();
				double[] f = funcion1.getFenotipo();
				
				System.out.println();
				
				Ruleta r = new Ruleta(funcion1.getFitness());
				r.showSeleccionados();	
				System.out.println();
				
				for (int i = 0; i < tamañoPoblacion; i++) {				
					boolean[] ptotal = p.get(i).getAlelos();		
					for (int j = 0; j < ptotal.length; j++) {
						if(ptotal[j] == true) System.out.print(1);
						else System.out.print(0);
					}
					System.out.println();
					System.out.println(f[i]);
					System.out.println();
				}
				
				UnPunto cruce = new UnPunto(0.7, funcion1);
				cruce.showCruzados();
				Mutacion mutacion1 = new Mutacion(0.02, funcion1);
				System.out.println();

				p = funcion1.getPoblacion();
				f = funcion1.getFenotipo();
				
				for (int i = 0; i < tamañoPoblacion; i++) {
					
					boolean[] ptotal = p.get(i).getAlelos();
					
					for (int j = 0; j < ptotal.length; j++) {
						if(ptotal[j] == true) System.out.print(1);
						else System.out.print(0);
					}
					System.out.println();
					System.out.println(f[i]);
					System.out.println();
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
	public int getPorcentageCruce() {
		return porcentageCruce;
	}
	public int getPorcentageMutacion() {
		return porcentageMutacion;
	}
	public int getPrecision() {
		return precision;
	}
	public int getElitismo() {
		return elitismo;
	}
}
