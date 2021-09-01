package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbour();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get all Favorites
     * @return {@link List}
     */
    List<Neighbour> getFavorite();

    /**
     * Add Favorites to List
     * @param neighbour
     */
    void addFavorite(Neighbour neighbour);

    /**
     * Deletes a Favorites neighbour
     * @param neighbour
     */
    void deleteFavorite(Neighbour neighbour);


}
