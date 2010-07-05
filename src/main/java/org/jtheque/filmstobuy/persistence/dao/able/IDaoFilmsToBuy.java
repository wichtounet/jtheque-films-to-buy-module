package org.jtheque.filmstobuy.persistence.dao.able;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
