package com.epam.gym.dao;

import com.epam.gym.model.Trainee;

import java.util.List;
import java.util.Optional;

public interface TraineeDao {
    Trainee create(Trainee trainee);

    /**
     * If userId is not found, throws RunTimeException()
     */
    Trainee update(Trainee trainee);

    boolean delete(Long id);

    Optional<Trainee> findById(Long id);

    List<Trainee> findAll();
}
