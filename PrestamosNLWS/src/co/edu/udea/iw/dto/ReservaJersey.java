package co.edu.udea.iw.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaJersey {

	private int idReserva;
	private int estadoReserva;
	private String fechaRealizacion;
	private String horaInicio;
	private String horaFinal;
	private String horaRealizado;
	private String fechaReserva;
	private String usuario;
	private String fechaEntrega;
	private String horaEntrega;
	
	public ReservaJersey() {
		
	}

	public ReservaJersey(int idReserva, int estadoReserva, String fechaRealizacion, String horaInicio,
			String horaFinal, String horaRealizado, String fechaReserva, String usuario, String fechaEntrega,
			String horaEntrega) {
		super();
		this.idReserva = idReserva;
		this.estadoReserva = estadoReserva;
		this.fechaRealizacion = fechaRealizacion;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.horaRealizado = horaRealizado;
		this.fechaReserva = fechaReserva;
		this.usuario = usuario;
		this.fechaEntrega = fechaEntrega;
		this.horaEntrega = horaEntrega;
	}
	
	

	public ReservaJersey(int idReserva, int estadoReserva, String fechaRealizacion, String horaInicio, String horaFinal,
			String horaRealizado, String fechaReserva, String usuario) {
		super();
		this.idReserva = idReserva;
		this.estadoReserva = estadoReserva;
		this.fechaRealizacion = fechaRealizacion;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.horaRealizado = horaRealizado;
		this.fechaReserva = fechaReserva;
		this.usuario = usuario;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(int estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public String getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(String fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getHoraRealizado() {
		return horaRealizado;
	}

	public void setHoraRealizado(String horaRealizado) {
		this.horaRealizado = horaRealizado;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
		
}
