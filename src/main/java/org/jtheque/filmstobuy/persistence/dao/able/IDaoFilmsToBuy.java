package org.jtheque.filmstobuy.persistence.dao.able;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jtheque.core.managers.persistence.able.JThequeDao;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;

import java.util.Collection;

/**
 * Data Access object to access to films to buy.
 *
 * @author Baptiste Wicht
 */
public interface IDaoFilmsToBuy extends JThequeDao {
    String TABLE = "T_FILMS_TO_BUY";

    /**
     * Returns the complete list of the films to buy. Initializes the list from the DB if she's empty.
     *
     * @return The list of films
     */
    Collection<FilmToBuy> getFilmsToBuy();

    /**
     * Returns the film identified by the id.
     *
     * @param id The id of the film
     *
     * @return The film
     */
    FilmToBuy getFilmToBuy(int id);

    /**
     * Create a new film to buy.
     *
     * @param film The film to buy to create
     */
    void create(FilmToBuy film);

    /**
     * Delete a film to buy.
     *
     * @param film The film to buy.
     *
     * @return true if the object is deleted else false.
     */
    boolean delete(FilmToBuy film);

    /**
     * Delete the film to buy with this id.
     *
     * @param id The id of the film
     *
     * @return true if the object is deleted else false.
     */
    boolean delete(int id);

    /**
     * Save the film to buy.
     *
     * @param film The film to save.
     */
    void save(FilmToBuy film);

    /**
     * Create an empty not persisted film to buy.
     *
     * @return A new film to buy.
     */
    FilmToBuy createFilmToBuy();
}