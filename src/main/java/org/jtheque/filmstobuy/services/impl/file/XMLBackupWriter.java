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
