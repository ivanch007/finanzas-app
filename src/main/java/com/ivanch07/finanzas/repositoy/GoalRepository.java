package com.ivanch07.finanzas.repositoy;

import com.ivanch07.finanzas.model.Goal;
import com.ivanch07.finanzas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long>{

    List<Goal> findByUser(User user);
}
