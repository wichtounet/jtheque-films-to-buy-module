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
import org.jtheque.utils.DatabaseUtils;
import org.jtheque.utils.bean.IntDate;
import org.jtheque.utils.io.FileException;

import javax.annotation.Resource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A backup reader for a DBV4 Backup.
 *
 * @author Baptiste Wicht
 */
public final class DBV4BackupReader implements BackupReader {
    @Resource
    private IFilmsToBuyService filmsToBuyService;

    @Override
    public void readBackup(Object object) throws FileException {
        Connection connection = (Connection) object;

        Collection<FilmToBuy> filmsToBuy = new ArrayList<FilmToBuy>(10);

        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM t_filmstobuy");

            while (result.next()) {
                FilmToBuy filmToBuy = filmsToBuyService.getEmptyFilmToBuy();

                filmToBuy.setTitle(result.getString("title"));
                filmToBuy.setDate(new IntDate(result.getInt("date")));

                filmsToBuy.add(filmToBuy);
            }

            for (FilmToBuy filmToBuy : filmsToBuy) {
                filmsToBuyService.create(filmToBuy);
            }
        } catch (SQLException e) {
            throw new FileException(e);
        } finally {
            DatabaseUtils.close(result);
            DatabaseUtils.close(statement);
        }
    }

    @Override
    public void persistTheData() {
    }
}
