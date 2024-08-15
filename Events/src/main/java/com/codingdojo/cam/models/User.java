package com.codingdojo.cam.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	// atributos & anotaciones
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "First name is required.")
	@Size(min = 2, message = "First name needs at least 2 chars")
	private String firstName;

	@NotEmpty(message = "Last name is required.")
	@Size(min = 2, message = "Last name needs at least 2 chars")
	private String lastName;

	@NotEmpty(message = "Email is required.")
	@Email(message = "Invalid Email") // verificar que el email sea valido
	private String email;

	@NotEmpty(message = "Password is required.")
	@Size(min = 6, message = "Password needs at least 6 chars")
	private String password;

	@Transient // no se guarda el dato en la base de datos
	@NotEmpty(message = "Confirmation is required.")
	@Size(min = 6, message = "Confirmation needs at least 6 chars")
	private String confirm;

	@NotEmpty(message = "Location is required.")
	private String location;

	@NotEmpty(message = "Province is required.")
	private String province;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	// relacion con event my user has many events
	@OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
	private List<Event> plannedEvents; // eventos que el user creo

	// tabla intermediaria
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_join_events", 
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "event_id"))

	private List<Event> joinedEvents; // los eventos a los que se unió

	// conexion con message
	@OneToMany(mappedBy="author", fetch = FetchType.LAZY)
	private List<Message> messages; //mensajes que envie
	
	// contructor vacio
	public User() {
	}

	// getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Event> getPlannedEvents() {
		return plannedEvents;
	}

	public void setPlannedEvents(List<Event> plannedEvents) {
		this.plannedEvents = plannedEvents;
	}

	public List<Event> getJoinedEvents() {
		return joinedEvents;
	}

	public void setJoinedEvents(List<Event> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	// automatization
	@PrePersist // Before creating a user
	protected void onCreate() {
		this.createdAt = new Date(); // Default current_timestamp
	}

	@PreUpdate // before update
	protected void onUpdate() {
		this.updatedAt = new Date(); // default current_timestamp on update current_timestamp
	}
}
