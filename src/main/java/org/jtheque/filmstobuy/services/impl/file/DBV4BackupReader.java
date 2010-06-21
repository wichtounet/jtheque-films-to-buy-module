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