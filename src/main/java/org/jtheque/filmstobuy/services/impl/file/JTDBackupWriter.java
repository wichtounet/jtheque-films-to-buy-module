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
import org.jtheque.core.managers.file.able.BackupWriter;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.utils.io.FileException;

import javax.annotation.Resource;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A backup writer for a JTD Backup.
 *
 * @author Baptiste Wicht
 */
public final class JTDBackupWriter implements BackupWriter {
    @Resource
    private IFilmsToBuyService filmsToBuyService;

    @Override
    public void write(Object object) throws FileException {
        DataOutputStream stream = (DataOutputStream) object;

        try {
            if (filmsToBuyService.getFilmsToBuy() == null || filmsToBuyService.getFilmsToBuy().isEmpty()) {
                stream.writeInt(IFileManager.NO_CONTENT);
            } else {
                stream.writeInt(IFileManager.CONTENT);

                boolean firstFilmToBuy = true;
                for (FilmToBuy film : filmsToBuyService.getFilmsToBuy()) {
                    if (firstFilmToBuy) {
                        firstFilmToBuy = false;
                    } else {
                        stream.writeLong(IFileManager.JT_INTERN_SEPARATOR);
                    }

                    stream.writeInt(film.getId());
                    stream.writeUTF(Managers.getManager(IFileManager.class).formatUTFToWrite(film.getTitle()));
                    stream.writeInt(film.getDate().intValue());
                }
            }

            stream.writeLong(IFileManager.JT_CATEGORY_SEPARATOR);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }
}