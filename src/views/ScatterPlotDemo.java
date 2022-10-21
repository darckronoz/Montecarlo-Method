package views;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import models.Jugador;


public class ScatterPlotDemo extends JFrame{

	private static final long serialVersionUID = 1L;
	JFreeChart chart;//declaramos un objeto de la clase JFreeChart para construir el grafico4
	XYSeriesCollection dataset;

	
	 /**
	  *@param rondas lista que contiene todas las rondas que se van a graficar 
*/
	public ScatterPlotDemo(ArrayList<Jugador> rondas) {
		super("Gráfica de jugadores vs juego");
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		setLocationRelativeTo(rootPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		crearGrafico(rondas);//metodo para cargar los datos y crear el grafico

		ChartPanel panel = new ChartPanel(chart, false);//ChartPanel es una clase del paquete JFreeChart
		add(panel,BorderLayout.CENTER); //anadimos el panel al JFrame

	}

	 /**
	  * este metodo carga las series por cada jugador 
	  *@param jugadoresEquipoUno lista que contiene todas los jugadores que participaron en los juegos 
*/
	public void crearGrafico(ArrayList<Jugador> jugadoresEquipoUno) { 
		dataset = new XYSeriesCollection();
		for (Jugador jugador : jugadoresEquipoUno) {
			XYSeries  seriesPA = new XYSeries(jugador.getId());//"Producto A" es la etiqueta o nombre
			dataset.addSeries(seriesPA);//anadir la serie del producto A
		}
		chart = ChartFactory.createScatterPlot(
				"Gráfica de puntaje de los jugadores", // Titulo
				"Rondas Jugadas", // Etiqueta Coordenada X
				"Puntaje", // Etiqueta Coordenada Y
				dataset, // Datos
				PlotOrientation.VERTICAL,
				true, // Muestra la leyenda de los productos en el eje de la X
				true,// mostrar la leyenda en cada punto
				false
				);
	}
	
	 /**
	  * este metodo carga los datos para cada uno de los jugadores  (series)
	  *@param nombre representa el nombre de cada serie (jugador)
	  *@param puntajeJuegos lista que contiene cada uno de los puntajes obtenidos por el jugador (serie) 
*/
	public void addInforJuegos(String nombre,ArrayList<Integer> puntajeJuegos) {
		for (int i = 0; i < puntajeJuegos.size(); i++) {
			dataset.getSeries(nombre).add(i,puntajeJuegos.get(i));
		}
	}
	 /**
	  * este metodo re valida , re pinta y muestra la grafica con todos los datos ingresados
*/
	public void iniciarGrafica() {
		revalidate();
		repaint();
		setVisible(true);
	}
}
