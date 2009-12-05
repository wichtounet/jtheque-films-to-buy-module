package org.jtheque.filmstobuy.controller.impl;

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

import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.filmstobuy.controller.able.IToBuyController;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.filmstobuy.view.able.IFilmsToBuyView;

import javax.annotation.Resource;

/**
 * The View Controller for films to buy.
 *
 * @author Baptiste Wicht
 */
public final class ToBuyController extends AbstractController implements IToBuyController {
    @Resource
    private IFilmsToBuyService filmsToBuyService;

    @Resource
    private IFilmsToBuyView filmsToBuyView;

    @Override
    public IFilmsToBuyView getView() {
        return filmsToBuyView;
    }

    @Override
    public void newFilmToBuy(String title) {
        filmsToBuyService.newFilmToBuy(title);
    }

    @Override
    public void deleteFilmToBuy(int index) {
        filmsToBuyService.delete(index);
        filmsToBuyView.refresh();
    }

    @Override
    public void editFilmToBuy(Integer index, String title) {
        filmsToBuyService.editFilmToBuy(index, title);
    }
}