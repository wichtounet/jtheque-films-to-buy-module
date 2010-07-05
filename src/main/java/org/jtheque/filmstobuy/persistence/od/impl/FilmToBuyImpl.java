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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.properties.IPropertiesManager;

/**
 * A film to buy implementation.
 *
 * @author Baptiste Wicht
 */
public final class FilmToBuyImpl extends AbstractFilmToBuy {
    @Override
    public String getDisplayableText() {
        return getTitle();
    }

    @Override
    public String toString() {
        return getDisplayableText();
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = HASHCODE_PRIME * result + getId();
        result = HASHCODE_PRIME * result + (getTitle() == null ? 0 : getTitle().hashCode());
        result = HASHCODE_PRIME * result + (getDate() == null ? 0 : getDate().intValue());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return Managers.getManager(IPropertiesManager.class).areEquals(this, obj, "title", "date");
    }
}
