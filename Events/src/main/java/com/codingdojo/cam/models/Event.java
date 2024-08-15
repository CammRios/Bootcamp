package com.codingdojo.cam.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "events")
public class Event {

	// atributos & anotaciones
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Event name is required.")
	private String eventName;

	@Future // permite la validacion de fecha en el futuro
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Event date is required.")
	private Date eventDate;

	@NotEmpty(message = "Event Location is required.")
	private String location;

	@NotEmpty(message = "Event Province is required.")
	private String province;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	// relacion con user: my event has one creator
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // llave foranea
	private User host;// user que creo el event

	// tabla intermediaria
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_join_events",
			   joinColumns = @JoinColumn(name = "event_id"),
			   inverseJoinColumns = @JoinColumn(name = "user_id"))
	
	private List<User> joinedUsers; // users que se unieron al event

	//conexion con messages
	@OneToMany(mappedBy="event", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //borrado en cascada
	private List<Message> eventMessages; //mesajes del evento
	
	
	public Event() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<User> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}

	public List<Message> getEventMessages() {
		return eventMessages;
	}

	public void setEventMessages(List<Message> eventMessages) {
		this.eventMessages = eventMessages;
	}

	@PrePersist // Before creating a user
	protected void onCreate() {
		this.createdAt = new Date(); // Default current_timestamp
	}

	@PreUpdate // before update
	protected void onUpdate() {
		this.updatedAt = new Date(); // default current_timestamp on update current_timestamp
	}
}
