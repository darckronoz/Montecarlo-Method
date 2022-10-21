package models;

public class Ronda {

	private int idRonda;
	private Equipo equipoUno;
	private Equipo equipoDos;
	private Jugador jugadorEquipoUno;
	private Jugador jugadorEquipoDos;

	 /**
	  * @param idRonda identificador de la ronda
	  * @param equipoUno equipo numero uno
	  * @param equipoDos equipo numero dos 
*/

	public Ronda(int idRonda, Equipo equipoUno,Equipo equipoDos) {
		this.idRonda=idRonda;
		this.equipoUno=equipoUno;
		this.equipoDos=equipoDos;
	}
	

	 /**
	  * metodo que se encarga de validar si un jugador del equipo Uno ingresado por parametro a realizado  tiros consecutivos 
	  * @param jugadorEquipoUno representa el jugador del equipo uno
*/
	public void validarTirosConsecutivosEquipoUno(Jugador jugadorEquipoUno) {
		if (this.jugadorEquipoUno!=null&&this.jugadorEquipoUno.getId()==jugadorEquipoUno.getId()) {
			this.jugadorEquipoUno.setTirosExtra(this.jugadorEquipoUno.getTirosExtra()+1);
			if (this.jugadorEquipoUno.getTirosExtra()==3) {
				this.equipoUno.setMarcadorGlobal(this.equipoUno.getMarcadorGlobal()+ this.jugadorEquipoUno.lanzarExtra());
			}
		}else {
			this.jugadorEquipoUno=jugadorEquipoUno;
		}
	}
	
	 /**
	  * metodo que se encarga de validar si un jugador del equipo dos ingresado por parametro a realizado  tiros consecutivos 
	  * @param jugadorEquipoDos representa el jugador del equipo dos
*/
	public void validarTirosConsecutivosEquipoDos(Jugador jugadorEquipoDos) {
		if (this.jugadorEquipoDos!=null&&this.jugadorEquipoDos.getId()==jugadorEquipoDos.getId()) {
			this.jugadorEquipoDos.setTirosExtra(this.jugadorEquipoDos.getTirosExtra()+1);
			if (this.jugadorEquipoDos.getTirosExtra()==3) {
				this.equipoDos.setMarcadorGlobal(this.equipoDos.getMarcadorGlobal()+ this.jugadorEquipoDos.lanzarExtra());
			}
		}else {
			this.jugadorEquipoUno=jugadorEquipoDos;
		}
	}
	
	 /**
	  * @return  el jugador con mayor puntaje de los dos equipos
*/
	public Jugador ganadorRonda() {
		Jugador jugador = null;
		if (equipoUno.ganadorIndividual().getPuntaje()>equipoDos.ganadorIndividual().getPuntaje()) {
			jugador= equipoUno.ganadorIndividual();
		}else {
			jugador =equipoDos.ganadorIndividual();
		}
		jugador.setExperiencia(jugador.getExperiencia()+3);
		return jugador;
	}
	
	 /**
	  * @return  el equipo con mayor puntaje global de los dos equipos
*/
	public Equipo ganadorRondaGrupal() {
		Equipo equipo = null;
		if (equipoUno.getMarcadorGlobal()>equipoDos.getMarcadorGlobal()) {
			equipo= equipoUno;
		}else {
			equipo =equipoDos;
		}
		return equipo;
	}

	@Override
	public String toString() {
		return "\n Ronda [idRonda=" + idRonda + ", equipoUno=" + equipoUno;
	}
	
	
	
}
