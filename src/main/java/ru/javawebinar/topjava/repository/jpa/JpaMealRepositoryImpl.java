package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.getUser().getId() != userId)
            return null;
        if (meal.isNew()){
            em.persist(meal);
            return meal;
        }
        else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        /*Meal ref = em.getReference(Meal.class, id);
        if (ref.getUser().getId() != userId){
            return false;
        }
        else{
            em.remove(ref);
            return true;
        }*/

        /*Query query = em.createQuery("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId");
        return query.setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;*/

        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        if (meal.getUser().getId() != userId)
            return null;
        else return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN, Meal.class)
                .setParameter("userId", userId)
                .setParameter(1, startDate)
                .setParameter(2, endDate)
                .getResultList();
    }
}