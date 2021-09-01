package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavoriteNeighbourEvent {
    /**
     * Event trigered when deleting a Neighbour from the Favorites List
     */

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}

