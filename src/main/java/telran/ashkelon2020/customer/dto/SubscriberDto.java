package telran.ashkelon2020.customer.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubscriberDto {
	String login;
	String name;
	LocalDateTime dateCreated;

	
	public SubscriberDto(String login, String name) {
		this.login = login;
		this.name = name;
		dateCreated = LocalDateTime.now();
	}

	public SubscriberDto(String login, String name, LocalDateTime dateCreated) {
		this.login = login;
		this.name = name;
		this.dateCreated = dateCreated;
	}
	
	
}
