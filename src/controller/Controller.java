package controller;

import java.util.ArrayList;
import models.Game;
import models.Player;
import models.Util;
import views.ScatterPlotDemo;

public class Controller {

	ArrayList<Player> jugadoresEquipoUno;
	ArrayList<Player> jugadoresEquipoDos;
	ArrayList<Game> juegos;
	
	public Controller() {
		juegos = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			jugadoresEquipoUno = new ArrayList<>();
			jugadoresEquipoDos = new ArrayList<>();
			armarEquipos();
			Game juego = new Game("J"+i,jugadoresEquipoUno,jugadoresEquipoDos);
			juego.iniciarJuego();
			juegos.add(juego);
		}

		gamersMostLucky();
		gameresMostExperience();
		teamWinner();
		genderWinnerGame();
		genderWinnerTotal();
		generatePlot();
	}

	 /**
	  * este metodo muestra por consola el equipo ganador por cada juego
*/
	private void teamWinner() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("\n Equipo Ganador del juego: ");
		for (Game juego : juegos) {
			System.out.println("\t Juego "+juego.getIdJuego()+" "+ juego.equipoGanadorRondaGrupal().getNombre()+" "+ juego.equipoGanadorRondaGrupal().getMarcadorGlobal());
		}
		System.out.println("----------------------------------------------------------------------------");
	}
	
	 /**
	  * Metodo que muestra los jugadores mas suertudos
*/
	private void gamersMostLucky() {
		System.out.println("\n Jugadores con mas suerte en cada juego : ");
		System.out.println("----------------------------------------------------------------------------");
		for (Game juego : juegos) {
			System.out.println("\t Juego"+juego.getIdJuego()+" "+ juego.jugadorConMasSuerte());
			System.out.println("----------------------------------------------------------------------------");
		}
	}

	 /**
	  * este metodo muestra por consola los jugadores con mas experiencia por juego
*/
	private void gameresMostExperience() {
		System.out.println("\n Jugadores con mas experiencia por juego: ");
		System.out.println("***********************************************************************");
		for (Game juego : juegos) {
			System.out.println("\t Juego"+juego.getIdJuego()+" "+ juego.jugadorConMasExperiencia());
			System.out.println("***********************************************************************");
		}
	}

	 /**
	  * este metodo muestra por consola el genero ganador por to total de juegos
	  * @return el genero ganador
*/
	private String genderWinnerTotal() {
		System.out.println("\n Genero Ganador Total Juegos: ");
		int femenino=0;
		int masculino=0;
		for (Game juego : juegos) {
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
	private void genderWinnerGame() {
		System.out.println("\n Genero ganador por Juego: ");
		for (Game juego : juegos) {
			System.out.println("\t "+juego.generoGanadorJuego());
		}
	}


	 /**
	  * @param fem cantidad de juegos ganados por el genero femenino
	  * @param mas cantidad de juegos ganados por el genero masculino
	  * @return el genero ganador
*/
	private String generoG(int fem, int mas) {
		if (fem>mas) {
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
			jugadoresEquipoUno.add(new Player("A"+i,(int) Util.pseudoaleatorios(30,50), 10, (int) Util.pseudoaleatorios(1,4), (int) (Math.random()*2)));
			jugadoresEquipoDos.add(new Player("R"+i,(int) Util.pseudoaleatorios(30,50), 10,(int) Util.pseudoaleatorios(1,4), (int) (Math.random()*2)));
		}
	}

	 /**
	  * metodo que se encarga de generar la grafica con los datos guardados en cada uno de los equipos
*/
	private void generatePlot() {
		ArrayList<Player> totalJugadores = new ArrayList<>();
		totalJugadores.addAll(jugadoresEquipoUno);
		totalJugadores.addAll(jugadoresEquipoDos);
		ScatterPlotDemo g = new ScatterPlotDemo(totalJugadores);
		for (Player jugador : jugadoresEquipoUno) {
			g.addInfoGame(jugador.getId(), getScoreSetTeamOne(jugador.getId()));
		}
		for (Player jugador : jugadoresEquipoDos) {
			g.addInfoGame(jugador.getId(), getScoreSetTeamTwo(jugador.getId()));
		}
		g.initGraphics();
	}
	
	/**
	  * @param id del jugador
	  * @return lista puntajes realizados por un jugador en todas las rondas jugadas del equipo 1
*/

	private ArrayList<Integer> getScoreSetTeamOne(String id) {
		ArrayList<Integer> puntajes = new ArrayList<>();
		for (Game juego : juegos) {
			if (juego.getEquipoUno().buscarJugador(id)!=null) {
				for (Integer integer : juego.getEquipoUno().buscarJugador(id).getPuntajesRondas()) {
					puntajes.add(integer);
				}
			}

		}
		return puntajes;
	}
	 /**
	  * @param id del jugador
	  * @return lista puntajes realizados por un jugador en todas las rondas jugadas del equipo 2
*/
	private ArrayList<Integer> getScoreSetTeamTwo(String id) {
		ArrayList<Integer> puntajes = new ArrayList<>();
		for (Game juego : juegos) {
			if (juego.getEquipoDos().buscarJugador(id)!=null) {
				for (Integer integer : juego.getEquipoDos().buscarJugador(id).getPuntajesRondas()) {
					puntajes.add(integer);
				}
			}

		}
		return puntajes;
	}


	
}
