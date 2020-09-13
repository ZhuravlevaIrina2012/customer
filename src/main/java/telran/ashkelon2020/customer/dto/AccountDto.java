package telran.ashkelon2020.customer.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
	String login;
	String topic;
	LocalDateTime dateCreated;
	@Singular
	Set<SubscriberDto> subscribers;
	
	public AccountDto(String login, String topic) {
		this.login = login;
		this.topic = topic;
		dateCreated = LocalDateTime.now();
	}
	
	
}
