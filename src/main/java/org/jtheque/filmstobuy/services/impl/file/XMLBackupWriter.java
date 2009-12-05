package org.jtheque.filmstobuy.services.impl.file;

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

import org.jtheque.core.managers.file.able.BackupWriter;
import org.jtheque.core.utils.file.XMLWriter;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;

import javax.annotation.Resource;

/**
 * A backup writer for a XML Backup.
 *
 * @author Baptiste Wicht
 */
public final class XMLBackupWriter implements BackupWriter {
    @Resource
    private IFilmsToBuyService filmsToBuyService;

    @Override
    public void write(Object object) {
        XMLWriter writer = (XMLWriter) object;

        writer.add("filmsToBuy");

        for (FilmToBuy film : filmsToBuyService.getFilmsToBuy()) {
            writer.add("film");

            writer.addOnly("id", film.getId());
            writer.addOnly("date", film.getDate().intValue());
            writer.addOnly("title", film.getTitle());

            writer.switchToParent();
        }

        writer.switchToParent();
    }
}