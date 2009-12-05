package org.jtheque.filmstobuy.controller.able;

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

import org.jtheque.core.managers.view.able.controller.Controller;
import org.jtheque.filmstobuy.view.able.IFilmsToBuyView;

/**
 * A films to buy controller specification.
 *
 * @author Baptiste Wicht
 */
public interface IToBuyController extends Controller {
    @Override
    IFilmsToBuyView getView();

    /**
     * Create a new FilmToBuy.
     *
     * @param title The title of the film.
     */
    void newFilmToBuy(String title);

    /**
     * Delete the film to buy.
     *
     * @param index The index of the film to delete.
     */
    void deleteFilmToBuy(int index);

    /**
     * Edit a film to buy.
     *
     * @param index The index of the film.
     * @param title The new title of the film.
     */
    void editFilmToBuy(Integer index, String title);
}
