package telran.ashkelon2020.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.customer.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
