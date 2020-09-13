package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"login"})
@Entity
public class Subscriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1800196963605528577L;
	@Id
	String login;
	String name;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dateCreated;
	@ManyToMany(mappedBy = "subscribers")
	Set<Account> accounts;
	
	public Subscriber() {
		dateCreated = LocalDateTime.now();
	}

	public Subscriber(String login, String name) {
		this.login = login;
		this.name = name;
		dateCreated = LocalDateTime.now();
	}
	
	
}
