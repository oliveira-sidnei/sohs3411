package br.com.ins.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ESS_USUARIO")
public class Usuario {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "login", nullable = false, unique = true)
	private String nameUser;

	@Column(name = "senha", nullable = false, unique = false)
	private String password;

	@Column(name = "ultimologin", unique = true)
	@Temporal(TemporalType.DATE)
	private Date lastAccess;
	
	@ManyToOne
	@JoinColumn(name = "idPerfil")
	private Perfil perfil;

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", nameUser=" + nameUser + ", password=" + password + ", lastAccess=" + lastAccess
				+ ", perfil=" + perfil + "]";
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	
	
}
