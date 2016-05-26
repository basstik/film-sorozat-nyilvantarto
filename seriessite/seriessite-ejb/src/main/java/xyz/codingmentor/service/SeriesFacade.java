/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.codingmentor.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import xyz.codingmentor.entity.Actor;
import xyz.codingmentor.entity.Series;

@Stateless
public class SeriesFacade {

    @PersistenceContext(unitName = "MoviePU")
    private EntityManager em;

    public SeriesFacade() {
    }

    public Series findSeriesById(Long seriesId) {
        TypedQuery<Series> ser = em.createNamedQuery("Series.findSeriesById", Series.class);
        ser.setParameter("id", seriesId);
        return ser.getSingleResult();

    }

    public List<Actor> findActorsInSeries(Long seriesId) {
        TypedQuery<Actor> actors = em.createNamedQuery("Series.findActorsBySeriesId", Actor.class);
        actors.setParameter("id", seriesId);
        return actors.getResultList();
    }

    public List<Actor> findActorsNotInSeries(Long seriesId) {
        TypedQuery<Actor> actors = em.createNamedQuery("Series.findActorsNotInSeriesBySeriesId", Actor.class);
        actors.setParameter("id", seriesId);
        return actors.getResultList();
    }

    public void deleteActorFromSeries(Long seriesId, Long actorId) {
        Series series = em.find(Series.class, seriesId);
        Actor actor = em.find(Actor.class, actorId);
        series.getActors().remove(actor);
    }

    public void addActorToSeries(Long seriesId, Long actorId) {
        Series series = em.find(Series.class, seriesId);
        Actor actor = em.find(Actor.class, actorId);
        series.getActors().add(actor);
    }

    public Actor findActorsById(Long actorId) {
        TypedQuery<Actor> actor = em.createNamedQuery("Actor.findActorsById", Actor.class);
        actor.setParameter("id", actorId);
        return actor.getSingleResult();
    }

    public List<Actor> getActorListNotInSeries(Long seriesId) {
        Query query = em.createNativeQuery("SELECT m.actor_id FROM movie_actor m WHERE m.movie_id != ?");
        query.setParameter(1, seriesId);
        List<Long> actorsId = query.getResultList();
        
        List<Actor> actorsNotInSereis = new ArrayList<>();
        for (Long id: actorsId) {
            actorsNotInSereis.add(findActorsById(id));
        }
        
        return actorsNotInSereis;
       
    }

}
