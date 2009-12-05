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

import org.jdom.JDOMException;
import org.jdom.xpath.XPath;
import org.jtheque.core.managers.file.able.BackupReader;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.utils.bean.IntDate;
import org.jtheque.utils.io.FileException;

import javax.annotation.Resource;

/**
 * A backup reader for a XML Backup.
 *
 * @author Baptiste Wicht
 */
public final class XMLBackupReader implements BackupReader {
    @Resource
    private IFilmsToBuyService filmsToBuyService;

    @Override
    public void readBackup(Object root) throws FileException {
        try {
            XPath xpa = XPath.newInstance("//filmsToBuy/film");

            for (Object filmElement : xpa.selectNodes(root)) {
                FilmToBuy film = filmsToBuyService.getEmptyFilmToBuy();
                film.getTemporaryContext().setId(Integer.parseInt(XPath.newInstance("./id").valueOf(filmElement)));
                film.setDate(new IntDate(Integer.parseInt(XPath.newInstance("./date").valueOf(filmElement))));
                film.setTitle(XPath.newInstance("./title").valueOf(filmElement));

                filmsToBuyService.create(film);
            }
        } catch (JDOMException e) {
            throw new FileException(e);
        }
    }

    @Override
    public void persistTheData() {
    }
}