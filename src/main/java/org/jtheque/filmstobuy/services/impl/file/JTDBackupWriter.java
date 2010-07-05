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
