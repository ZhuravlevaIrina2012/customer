package telran.ashkelon2020.customer.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.customer.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
	
	Stream<Subscriber> findByAccountsCustomerId(String customerId);
}
