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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.file.IFileManager;
import org.jtheque.core.managers.file.able.BackupReader;
import org.jtheque.core.utils.file.jt.impl.JTDFile;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.utils.bean.IntDate;
import org.jtheque.utils.io.FileException;

import javax.annotation.Resource;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A backup reader for a JTD Backup.
 *
 * @author Baptiste Wicht
 */
public final class JTDBackupReader implements BackupReader {
    private final Collection<FilmToBuy> filmsToBuy = new ArrayList<FilmToBuy>(10);

    @Resource
    private IFilmsToBuyService filmsToBuyService;

    @Override
    public void persistTheData() {
        if (filmsToBuy != null) {
            for (FilmToBuy filmToBuy : filmsToBuy) {
                filmsToBuyService.create(filmToBuy);
            }
        }
    }

    @Override
    public void readBackup(Object object) throws FileException {
        JTDFile file = (JTDFile) object;
        DataInputStream stream = file.getStream();

        try {
            if (stream.readInt() == IFileManager.CONTENT) {
                boolean endOfList = false;

                while (!endOfList) {
                    FilmToBuy film = filmsToBuyService.getEmptyFilmToBuy();

                    film.getTemporaryContext().setId(stream.readInt());
                    film.setTitle(Managers.getManager(IFileManager.class).formatUTFToRead(stream.readUTF()));
                    film.setDate(new IntDate(stream.readInt()));

                    filmsToBuy.add(film);

                    long separator = stream.readLong();

                    if (separator == IFileManager.JT_CATEGORY_SEPARATOR) {
                        endOfList = true;
                    } else if (separator != IFileManager.JT_INTERN_SEPARATOR) {
                        file.setCorrectSeparators(false);
                    }
                }
            } else if (stream.readLong() != IFileManager.JT_CATEGORY_SEPARATOR) {
                file.setCorrectSeparators(false);
            }
        } catch (IOException e) {
            throw new FileException(e);
        }
    }
}