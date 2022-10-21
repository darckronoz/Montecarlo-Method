package controller;

import java.util.ArrayList;
import models.Juego;
import models.Jugador;
import models.Util;
import views.ScatterPlotDemo;

public class Controller {

	ArrayList<Jugador> jugadoresEquipoUno;
	ArrayList<Jugador> jugadoresEquipoDos;
	ArrayList<Juego> juegos;
	
	public Controller() {
		juegos = new ArrayList<>();

		for (int i = 0; i < 500; i++) {
			jugadoresEquipoUno = new ArrayList<>();
			jugadoresEquipoDos = new ArrayList<>();
			armarEquipos();
			Juego juego = new Juego("J"+i,jugadoresEquipoUno,jugadoresEquipoDos);
			juego.iniciarJuego();
			juegos.add(juego);
		}

		jugadoresMasSuertudos();
		jugadoresMasExperimentado();
		equipoGandor();
		generoGanadorPorJuego();
		generoGanadorTotal();
		generarGrafica();
	}

	 /**
	  * este metodo muestra por consola el equipo ganador por juego
*/
	private void equipoGandor() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n Equipo ganador por juego: ");
		for (Juego juego : juegos) {
			System.out.println("\t Juego "+juego.getIdJuego()+" "+ juego.equipoGanadorRondaGrupal().getNombre()+" "+ juego.equipoGanadorRondaGrupal().getMarcadorGlobal());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	 /**
	  * este metodo muestra por consola los jugadores con mas suerte por juego
*/
	private void jugadoresMasSuertudos() {
		System.out.println("\n Jugadores con mas suerte por juego: ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (Juego juego : juegos) {
			System.out.println("\t Juego"+juego.getIdJuego()+" "+ juego.jugadorConMasSuerte());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}

	 /**
	  * este metodo muestra por consola los jugadores con mas experiencia por juego
*/
	private void jugadoresMasExperimentado() {
		System.out.println("\n Jugadores con mas experiencia por juego: ");
		System.out.println("***********************************************************************");
		for (Juego juego : juegos) {
			System.out.println("\t Juego"+juego.getIdJuego()+" "+ juego.jugadorConMasExperiencia());
			System.out.println("***********************************************************************");
		}
	}

	 /**
	  * este metodo muestra por consola el genero ganador por to total de juegos
	  * @return el genero ganador
*/
	private String generoGanadorTotal() {
		System.out.println("\n Genero Ganador Total Juegos: ");
		int femenino=0;
		int masculino=0;
		for (Juego juego : juegos) {
			int genero=	juego.jugadorGanador().getGenero();
			if (genero==0) {
				femenino++;
			}else {
				masculino++;
			}
		}
		return generoG(femenino, masculino);
	}

	 /**
	  * este metodo muestra por consola el genero ganador por juego
*/
	private void generoGanadorPorJuego() {
		System.out.println("\n Genero ganador por Juego: ");
		for (Juego juego : juegos) {
			System.out.println("\t "+juego.generoGanadorJuego());
		}
	}


	 /**
	  * @param femenino cantidad de juegos ganados por el genero femenino
	  * @param masculino cantidad de juegos ganados por el genero masculino
	  * @return el genero ganador
*/
	private String generoG(int femenino, int masculino) {
		if (femenino>masculino) {
			System.out.println("\t FEMENINO");
			return "FEMENINO";
		}else {
			System.out.println("\t MASCULINO");
			return "MASCULINO";
		}
	}
	
	 /**
	  * este metodo se encarga de grear los dos equipos con datos pseudoaleatorios 
*/
	private void armarEquipos() {
		jugadoresEquipoUno.clear();
		jugadoresEquipoDos.clear();
		for (int i = 0; i < 6; i++) {
			jugadoresEquipoUno.add(new Jugador("A"+i,(int) Util.pseudoaleatorios(30,50), 10, (int) Util.pseudoaleatorios(1,4), (int) (Math.random()*2)));
			jugadoresEquipoDos.add(new Jugador("R"+i,(int) Util.pseudoaleatorios(30,50), 10,(int) Util.pseudoaleatorios(1,4), (int) (Math.random()*2)));
		}
	}

	 /**
	  * metodo que se encarg de generar la grafica con los datos guardados en cada uno de los equipos
*/
	private void generarGrafica() {
		ArrayList<Jugador> totalJugadores = new ArrayList<>();
		totalJugadores.addAll(jugadoresEquipoUno);
		totalJugadores.addAll(jugadoresEquipoDos);
		ScatterPlotDemo g = new ScatterPlotDemo(totalJugadores);
		for (Jugador jugador : jugadoresEquipoUno) {
			g.addInforJuegos(jugador.getId(), getPuntajeRondasEquipoUno(jugador.getId()));
		}
		for (Jugador jugador : jugadoresEquipoDos) {
			g.addInforJuegos(jugador.getId(), getPuntajeRondasEquipoDos(jugador.getId()));
		}
		g.iniciarGrafica();
	}
	
	 /**
	  * @param id identificador de un jugador
	  * @return lista de todos los puntajes realizados por un jugador en todas las rondas jugadas esto del quipo uno 
*/

	private ArrayList<Integer> getPuntajeRondasEquipoUno(String id) {
		ArrayList<Integer> puntajes = new ArrayList<>();
		for (Juego juego : juegos) {
			if (juego.getEquipoUno().buscarJugador(id)!=null) {
				for (Integer integer : juego.getEquipoUno().buscarJugador(id).getPuntajesRondas()) {
					puntajes.add(integer);
				}
			}

		}
		return puntajes;
	}
	 /**
	  * @param id identificador de un jugador
	  * @return lista de todos los puntajes realizados por un jugador en todas las rondas jugadas esto del quipo dos
*/
	private ArrayList<Integer> getPuntajeRondasEquipoDos(String id) {
		ArrayList<Integer> puntajes = new ArrayList<>();
		for (Juego juego : juegos) {
			if (juego.getEquipoDos().buscarJugador(id)!=null) {
				for (Integer integer : juego.getEquipoDos().buscarJugador(id).getPuntajesRondas()) {
					puntajes.add(integer);
				}
			}

		}
		return puntajes;
	}

	 /**
	  * metodo para inicializar la aplicación 
	  * @param args datos necesarios para inicializar la aplicacion (no requeridos)
*/
	public static void main(String[] args) {
		new Controller();
	}
}
