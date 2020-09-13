package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"login"})
@Entity
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2273497044215583631L;
	@Id
	String login;
	String topic;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dateCreated;
	@ManyToMany
	@JoinTable(
			name = "ACCOUNT_SUBSCRIBERS",
			joinColumns = @JoinColumn(name = "ACCOUNTS_LOGIN"),
			inverseJoinColumns = @JoinColumn(name = "SUBSCRIBERS_LOGIN")
			)
	Set<Subscriber> subscribers;
	@ManyToOne
	Customer customer;
	
	public Account() {
		dateCreated = LocalDateTime.now();
	}

	public Account(String login, String topic, Customer customer) {
		this.login = login;
		this.topic = topic;
		this.customer = customer;
		dateCreated = LocalDateTime.now();
	}
	
	
}
