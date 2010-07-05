package org.jtheque.filmstobuy.services.impl.file;

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
