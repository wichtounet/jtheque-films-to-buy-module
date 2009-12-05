package org.jtheque.filmstobuy.persistence.od.able;

import org.jtheque.core.managers.persistence.able.Entity;
import org.jtheque.utils.bean.IntDate;

/**
 * @author Baptiste Wicht
 */
public interface FilmToBuy extends Entity {
    /**
     * Return the title.
     *
     * @return the title
     */
    String getTitle();

    /**
     * Set a new title to the film.
     *
     * @param title the title to set
     */
    void setTitle(String title);

    /**
     * Return the date of the film.
     *
     * @return the date
     */
    IntDate getDate();

    /**
     * Set the date of the add of the film.
     *
     * @param date the date to set
     */
    void setDate(IntDate date);
}
