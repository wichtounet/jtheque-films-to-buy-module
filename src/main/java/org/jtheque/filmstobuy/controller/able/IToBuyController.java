package org.jtheque.filmstobuy.controller.able;

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
