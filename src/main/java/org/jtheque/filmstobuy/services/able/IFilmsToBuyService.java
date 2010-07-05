package org.jtheque.filmstobuy.services.able;

import org.jtheque.core.managers.persistence.able.DataContainer;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;

import java.util.Collection;

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

/**
 * A service for the films to buy functions.
 *
 * @author Baptiste Wicht
 */
public interface IFilmsToBuyService extends DataContainer<FilmToBuy> {
    String DATA_TYPE = "FilmsToBuy";

    /**
     * Create a new FilmToBuy.
     *
     * @param title The title of the film.
     */
    void newFilmToBuy(String title);

    /**
     * Edit a film to buy.
     *
     * @param index The index of the film.
     * @param title The new title of the film.
     */
    void editFilmToBuy(Integer index, String title);

    /**
     * Delete the film to buy.
     *
     * @param index The index of the film to delete.
     */
    void delete(int index);

    /**
     * Create a new film to buy.
     *
     * @param filmToBuy The film to buy to create.
     */
    void create(FilmToBuy filmToBuy);

    /**
     * Return all the films to buy.
     *
     * @return A List containing all the films to buy.
     */
    Collection<FilmToBuy> getFilmsToBuy();

    /**
     * Save the film to buy.
     *
     * @param film The film to buy to save.
     */
    void save(FilmToBuy film);

    /**
     * Return the film to buy denoted by a specific id.
     *
     * @param id The id we want to search for.
     *
     * @return The corresponding film to buy else null if there is no film to buy with this.
     */
    FilmToBuy getFilmToBuy(int id);

    /**
     * Delete a film to buy.
     *
     * @param filmToBuy The film to buy to delete.
     *
     * @return true if the entity has been deleted else false.
     */
    boolean delete(FilmToBuy filmToBuy);

    /**
     * Return an empty film to buy.
     *
     * @return An empty film to buy.
     */
    FilmToBuy getEmptyFilmToBuy();
}
