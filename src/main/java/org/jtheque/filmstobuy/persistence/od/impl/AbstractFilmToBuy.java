package org.jtheque.filmstobuy.persistence.od.impl;

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

import org.jtheque.core.managers.persistence.AbstractEntity;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.utils.bean.IntDate;

/**
 * Represents a film to buy.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractFilmToBuy extends AbstractEntity implements FilmToBuy {
    /**
     * The title of the film.
     */
    private String title;

    /**
     * The date we've add it.
     */
    private IntDate date;

    @Override
    public final String getTitle() {
        return title;
    }

    @Override
    public final void setTitle(String title) {
        this.title = title;
    }

    @Override
    public final IntDate getDate() {
        return date;
    }

    @Override
    public final void setDate(IntDate date) {
        this.date = date;
    }
}