package com.ivanch07.finanzas.repositoy;

import com.ivanch07.finanzas.model.Category;
import com.ivanch07.finanzas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUser(User user);
}
