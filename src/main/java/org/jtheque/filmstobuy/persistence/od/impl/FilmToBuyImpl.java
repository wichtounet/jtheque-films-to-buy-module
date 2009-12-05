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

import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;

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
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final FilmToBuy other = (FilmToBuy) obj;

        if (getTitle() == null) {
            if (other.getTitle() != null) {
                return false;
            }
        } else if (!getTitle().equals(other.getTitle())) {
            return false;
        }

        if (getDate() == null) {
            if (other.getDate() != null) {
                return false;
            }
        } else if (getDate().intValue() != other.getDate().intValue()) {
            return false;
        }

        return true;
    }
}