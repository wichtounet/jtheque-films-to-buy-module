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

import org.jtheque.core.managers.file.able.BackupReader;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.utils.bean.IntDate;
import org.jtheque.utils.io.FileException;

import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

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
