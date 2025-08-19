package com.ivanch07.finanzas.repositoy;

import com.ivanch07.finanzas.model.Budget;
import com.ivanch07.finanzas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByUser(User user);
}
