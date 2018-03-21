package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.*;

import base.AlgoritmoGenetico;
import base.Cromosoma;
import base.Gen;
import cruce.UnPunto;
import funciones.Funcion1;
import mutacion.Mutacion;
import seleccion.Ruleta;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tamañoPoblacion;
	private int numeroGeneraciones;
	private double porcentageCruce;
	private double porcentageMutacion;
	private double precision;
	private Gen genMejor;
	private double[] generacion;
	private double[] mejoresFitness;
	
	public Menu() {
		JTextField tamPob = new JTextField("100");
		JTextField numGen = new JTextField("100");
		JTextField porCruce = new JTextField("0.6");
		JTextField porMuta = new JTextField("0.05");
		JTextField preci = new JTextField("0.001");
		JCheckBox eliY = new JCheckBox("si", false);
		JCheckBox eliN = new JCheckBox("no", false);
		JLabel empty = new JLabel();
		JButton ok = new JButton("Ok");

		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null); 
		setTitle("Genetic Algorithm*"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminar el programa al pulsar la X

		JPanel checkBoxPanel = new JPanel(new GridLayout(1, 2));
		checkBoxPanel.add(eliY);
		checkBoxPanel.add(eliN);
		eliN.setSelected(true);

		/*Menu Panel*/
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

		/*Grafica Panel*/

		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel grafica = new Plot2DPanel();

		// define the legend position
		grafica.addLegend("SOUTH");

		// add a line plot to the PlotPanel

		setLayout(new BorderLayout());
		add(menuPanel, BorderLayout.WEST);
		add(grafica, BorderLayout.CENTER);
		pack();

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (tamPob.getText().equals("") || numGen.getText().equals("") || porCruce.getText().equals("") ||
						porMuta.getText().equals("") || preci.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor introduzca todos los datos.");
				}

				else {

					tamañoPoblacion = Integer.parseInt(tamPob.getText());
					numeroGeneraciones = Integer.parseInt(numGen.getText());
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
						JOptionPane.showMessageDialog(null, "Opcion elitista con un 2% de la población.");
						generacion = new double[numeroGeneraciones];
						mejoresFitness = new double[numeroGeneraciones];
						
						Funcion1 funcion1 = new Funcion1(precision);
						Ruleta ruleta = new Ruleta();
						UnPunto cruce = new UnPunto(porcentageCruce);
						Mutacion mutacion = new Mutacion(porcentageMutacion);
						
						int posGenMejor = 0;
						for (int i = 0; i < numeroGeneraciones; i++) {
							/*Crear la poblacion*/ /*Funciona*/
//							funcion1.showPoblacion();
//							funcion1.showFitness();
							
//							funcion1.calcularGenMejorEli(0.02);
//							funcion1.calcularFenotipos();
//							funcion1.calcularFitness();
//							funcion1.showPoblacion();
//							funcion1.showFitness();
							
							/*Ejecutar la ruleta*/ /*Funciona*/
//							ruleta.ejecutarRuleta(funcion1);
//							ruleta.showSeleccionados();
//							funcion1.calcularFenotipos();
//							funcion1.calcularFitness();
//							funcion1.showPoblacion();
//							funcion1.showFitness();

							/*Ejecutar el cruce*/  /*Funciona*/
//							cruce.cruzar(funcion1);
//							cruce.showCruzados();
//							funcion1.calcularFenotipos();
//							funcion1.calcularFitness();
//							funcion1.showPoblacion();
//							funcion1.showFitness();
							
							/*Ejecutar la mutacion*/ /*Funciona*/
//							mutacion.mutar(funcion1);
//							funcion1.calcularFenotipos();
//							funcion1.calcularFitness();
//							funcion1.showPoblacion();
//							funcion1.showFitness();
							
//							posGenMejor = funcion1.getPosGenMejor();
							generacion[i] = i;					
							mejoresFitness[i] = funcion1.getFitness()[posGenMejor];
						}
						
						grafica.setVisible(false);
						pintarGrafica(grafica, generacion, mejoresFitness);
					}

					else if (eliY.isSelected() == false && eliN.isSelected() == true) {
						
						generacion = new double[numeroGeneraciones];
						mejoresFitness = new double[numeroGeneraciones];
						
						AlgoritmoGenetico ag = new AlgoritmoGenetico(tamañoPoblacion, precision);
						Ruleta r = new Ruleta();
						UnPunto p = new UnPunto(porcentageCruce);
						double fitnessMejor;
						double fitnessMejorAbsoluto;
						
						for (int i = 0; i < numeroGeneraciones; i++) {
							ag.showPoblacion();
							System.out.println("Fitnes Mejor: " + ag.getFitnessMejor());
							System.out.println("Fitnes Mejor Absoluto: " + ag.getFitnessMejorAbsoluto());
							r.ejecutarRuleta(ag);
							r.showPuntuacion();
							ag.showPoblacion();
							p.cruzar(ag);
						}
						
						grafica.setVisible(false);
						grafica.removeAllPlots();
						pintarGrafica(grafica, generacion, mejoresFitness);
					}
				} 
			}
		});	
	}
	
	public void pintarGrafica(Plot2DPanel grafica, double[] x, double[] y) {
		// define the legend position
		grafica.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		grafica.addLinePlot("", x, y);		
		add(grafica, BorderLayout.CENTER);	
		grafica.setVisible(true);
	}
}
