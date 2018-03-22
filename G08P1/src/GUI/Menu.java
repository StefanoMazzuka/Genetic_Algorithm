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
	private int tama�oPoblacion;
	private int numeroGeneraciones;
	private double porcentajeCruce;
	private double porcentajeMutacion;
	private double precision;
	private double[] generacion;
	private double[] mejoresFitnessAbsolutos;
	private double[] mejoresFitness;
	private double[] listaMedias;
	
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
		menuPanel.add(new JLabel("Tama�o poblacion:"));
		menuPanel.add(tamPob);
		menuPanel.add(new JLabel("Numero de generaciones:"));
		menuPanel.add(numGen);
		menuPanel.add(new JLabel("Porcentaje de cruce:"));
		menuPanel.add(porCruce);
		menuPanel.add(new JLabel("Porcentaje de mutaciones:"));
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

					tama�oPoblacion = Integer.parseInt(tamPob.getText());
					numeroGeneraciones = Integer.parseInt(numGen.getText());
					porcentajeCruce = Double.parseDouble(porCruce.getText());
					porcentajeMutacion = Double.parseDouble(porMuta.getText());
					precision = Double.parseDouble(preci.getText());
					
					if (eliY.isSelected() == false && eliN.isSelected() == false) {
						JOptionPane.showMessageDialog(null, "Quiere elitista?.");
					}
					
					else if (eliY.isSelected() == true && eliN.isSelected() == true) {
						JOptionPane.showMessageDialog(null, "Seleccione solo una opcion elitista.");
					}
					
					else if (eliY.isSelected() == true && eliN.isSelected() == false) {
						/*Hacer Elitista*/
					}

					else if (eliY.isSelected() == false && eliN.isSelected() == true) {
						
						generacion = new double[numeroGeneraciones];
						mejoresFitnessAbsolutos = new double[numeroGeneraciones];
						mejoresFitness = new double[numeroGeneraciones];
						
						for (int i = 0; i < numeroGeneraciones; i++) {
							generacion[i] = i;
						}
						
						AlgoritmoGenetico ag = new AlgoritmoGenetico(tama�oPoblacion, precision, porcentajeCruce, 
								porcentajeMutacion, numeroGeneraciones);
						ag.ejecutarFuncion1();
						
						mejoresFitnessAbsolutos = ag.getListaFitnessMejorAbsoluto();
						mejoresFitness = ag.getListaFitnessMejor();
						listaMedias = ag.getListaMedias();
						
						grafica.setVisible(false);
						grafica.removeAllPlots();
						pintarGrafica(grafica, generacion, mejoresFitnessAbsolutos);
						pintarGrafica(grafica, generacion, mejoresFitness);
						pintarGrafica(grafica, generacion, listaMedias);	
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
