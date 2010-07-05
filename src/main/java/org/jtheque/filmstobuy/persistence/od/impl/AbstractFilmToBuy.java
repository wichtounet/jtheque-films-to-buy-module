package org.jtheque.filmstobuy.persistence.od.impl;

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
