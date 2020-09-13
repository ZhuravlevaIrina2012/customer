package telran.ashkelon2020.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.customer.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, String> {

}
