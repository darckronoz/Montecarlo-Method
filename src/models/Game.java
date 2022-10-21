package models;

import java.util.ArrayList;

public class Game {

	private String idJuego;
	private ArrayList<Round> rondas;
	private Team equipoUno;
	private Team equipoDos;
	int equipoAlanzar;

	/**
	 * @param idJuego representa el identificador para cada juego
	 * @param jugadoresEquipoUno lista la cual contiene los jugadores que hacen parte del equipo uno
	 * @param jugadoresEquipoDos lista la cual contiene los jugadores que hacen parte del equipo dos
	 */
	public Game(String idJuego,ArrayList<Player> jugadoresEquipoUno,ArrayList<Player> jugadoresEquipoDos) {
		this.idJuego=idJuego;
		rondas = new ArrayList<>();
		equipoUno = new Team("Azul",jugadoresEquipoUno);
		equipoDos = new Team("Rojo",jugadoresEquipoDos);
	}

	/**
	 * metodo que inicia una nueva ronda y la almacena en la lista de rondas
	 * @param numeroRonda cantidad de rondas a jugar
	 */
	private void iniciarRonda(int numeroRonda) {
		Round ronda = new Round(numeroRonda, equipoUno, equipoDos);
		while (equipoUno.jugadoresDiponibles()>0&&equipoDos.jugadoresDiponibles()>0) {
			lanzarATablero();
		}		
		ronda.validarTirosConsecutivosEquipoUno(equipoUno.sorteTiroExtra());
		ronda.validarTirosConsecutivosEquipoDos(equipoDos.sorteTiroExtra());
		ronda.ganadorRonda();
		rondas.add(ronda);
	}

	/**
	 * metodo que inicia x cantidad de rondas las cuales completan un juego  
	 * return verdadero si inicio una nueva ronda
	 */
	public boolean iniciarJuego() {
		int iniciarRoanda=0;
		while (iniciarRoanda<10) {
			iniciarRonda(iniciarRoanda);
			equipoUno.siguienteRonda();
			equipoDos.siguienteRonda();
			iniciarRoanda++;
		}
		return true;
	}
	/**
	 * metodo que indica el equipo que deve lanzar a continuacion
	 */
	public void lanzarATablero() {
		if (equipoAlanzar==0) {			
			equipoUno.ALanzar();
			equipoAlanzar++;
		}
		if (equipoAlanzar==1) {
			equipoDos.ALanzar();
			equipoAlanzar--;
		}
	}

	/**
	 * @return el equipo ganador por una ronda individual
	 */

	public Team equipoGanadorRondaIndivudual() {
		if (equipoUno.ganadorIndividual().getPuntaje()> equipoDos.ganadorIndividual().getPuntaje()) {
			return equipoUno;
		}else if(equipoUno.ganadorIndividual().getPuntaje()< equipoDos.ganadorIndividual().getPuntaje()) {
			return equipoDos;
		}else if(equipoUno.ganadorIndividual().getPuntaje()== equipoDos.ganadorIndividual().getPuntaje()) {
			lanzarATableroDesempate();
		}
		return null;
	}

	/**
	 * @return el equipo ganador por su puntaje global
	 */
	public Team equipoGanadorRondaGrupal() {
		Team equipoGanador = null;
		if (equipoUno.getMarcadorGlobal()> equipoDos.getMarcadorGlobal()) {
			equipoGanador = equipoUno;
		}else {
			equipoGanador = equipoDos;
		}
		return equipoGanador;
	}

	/**
	 * metodo que permite realizar un lanzamiento extra a el ganador de cada equipo
	 */
	private void lanzarATableroDesempate() {
		equipoUno.ganadorIndividual().lanzarExtra();
		equipoDos.ganadorIndividual().lanzarExtra();
	}

	/**
	 * @return el jugador con mas suerte de los dos equipos
	 */
	public Player jugadorConMasSuerte() {
		if ( jugadorConMasSuerteEquipoUno().getSumatoriaSuerte()>jugadorConMasSuerteEquipoDos().getSumatoriaSuerte()) {
			return  jugadorConMasSuerteEquipoUno();
		}
		else {
			return  jugadorConMasSuerteEquipoDos();
		}
	}

	/**
	 * @return el jugador con mas suerte del quipo uno
	 */
	private Player jugadorConMasSuerteEquipoUno() {
		Player ganadorMasSuertudo =null;
		int maxSuerte =0;
		for (Player jugador : equipoUno.getJugadores()) {
			if (jugador.getSumatoriaSuerte()>maxSuerte) {
				ganadorMasSuertudo=jugador;
				maxSuerte=jugador.getSumatoriaSuerte();
			}
		}	
		return ganadorMasSuertudo;
	}

	/**
	 * @return el jugador con mas suerte del quipo dos
	 */
	private Player jugadorConMasSuerteEquipoDos() {
		Player ganadorMasSuertudo =null;
		int maxSuerte =0;
		for (Player jugador : equipoDos.getJugadores()) {
			if (jugador.getSumatoriaSuerte()>maxSuerte) {
				ganadorMasSuertudo=jugador;
				maxSuerte=jugador.getSumatoriaSuerte();
			}
		}

		return ganadorMasSuertudo;
	}

	/**
	 * @return el jugador con mas experiencia del quipo uno
	 */
	private Player jugadorConMasExperienciaEquipoUno() {
		Player ganadorMasExperimentado =null;
		int maxExperiencia =0;
		for (Player jugador : equipoUno.getJugadores()) {
			if (jugador.getExperiencia()>maxExperiencia) {
				ganadorMasExperimentado=jugador;
				maxExperiencia=jugador.getExperiencia();
			}
		}	
		return ganadorMasExperimentado;
	}

	/**
	 * @return el jugador con mas experiencia del quipo uno
	 */
	private Player jugadorConMasExperienciaEquipoDos() {
		Player ganadorMasExperimentado =null;
		int maxExperiencia =0;
		for (Player jugador : equipoDos.getJugadores()) {
			if (jugador.getExperiencia()>maxExperiencia) {
				ganadorMasExperimentado=jugador;
				maxExperiencia=jugador.getExperiencia();
			}
		}

		return ganadorMasExperimentado;
	}

	/**
	 * @return el genero con mas victorias en cada una de las rondas
	 */
	public String generoGanadorJuego() {
		int femenino=0;
		int masculino=0;
		for (Round ronda : rondas) {
			int genero=	ronda.ganadorRonda().getGenero();
			if (genero==0) {
				femenino++;
			}else {
				masculino++;
			}
		}
		return generoG(femenino, masculino);
	}

	/**
	 * @param femenino numero que representa la cantidad de veces que ganao el genero femenino
	 * @param masculino numero que representa la cantidad de veces que gano el genero masculino
	 * @return el genero ganador
	 */
	private String generoG(int femenino, int masculino) {
		if (femenino>masculino) {
			return "FEMENINO";
		}else {
			return "MASCULINO";
		}
	}

	/**
	 * @return el jugador con mas experiencia de los dos equipos
	 */
	public Player jugadorConMasExperiencia() {
		if ( jugadorConMasExperienciaEquipoUno().getExperiencia()>jugadorConMasExperienciaEquipoDos().getExperiencia()) {
			return  jugadorConMasExperienciaEquipoUno();
		}
		else {
			return  jugadorConMasExperienciaEquipoDos();
		}
	}

	/**
	 * @return el jugador ganador de los dos equipos por su puntaje 
	 */
	public Player jugadorGanador() {
		if (equipoUno.ganadorEquipo().getPuntaje()>equipoDos.ganadorEquipo().getPuntaje()) {
			return  equipoUno.getJugadorGanador();
		}
		else {
			return  equipoDos.getJugadorGanador();
		}
	}

	 /**
	  * @return el equipo uno
*/
	public Team getEquipoUno() {
		return equipoUno;
	}

	 /**
	  * @return el equipo dos
*/
	public Team getEquipoDos() {
		return equipoDos;
	}

	 /**
	  * @return cantidad de rondas jugadas 
*/
	public ArrayList<Round> getRondas() {
		return rondas;
	}

	 /**
	  * @return identificador del juego
*/
	public String getIdJuego() {
		return idJuego;
	}

	@Override
	public String toString() {
		return "Juego [rondas=" + rondas +"]";
	}




}
