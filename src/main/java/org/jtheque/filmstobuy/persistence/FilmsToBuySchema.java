package org.jtheque.filmstobuy.persistence;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.schema.AbstractSchema;
import org.jtheque.core.managers.schema.Insert;
import org.jtheque.filmstobuy.persistence.dao.able.IDaoFilmsToBuy;
import org.jtheque.utils.bean.Version;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.Resource;

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

/**
 * The database schema for the FilmsToBuy Module.
 *
 * @author Baptiste Wicht
 */
public final class FilmsToBuySchema extends AbstractSchema {
    @Resource
    private SimpleJdbcTemplate jdbcTemplate;

    /**
     * Construct a new FilmsToBuySchema.
     */
    public FilmsToBuySchema() {
        super();

        Managers.getManager(IBeansManager.class).inject(this);
    }

    @Override
    public Version getVersion() {
        return new Version("1.0");
    }

    @Override
    public String getId() {
        return "FilmsToBuy-Schema";
    }

    @Override
    public String[] getDependencies() {
        return null;
    }

    @Override
    public void install() {
        jdbcTemplate.update("CREATE TABLE " + IDaoFilmsToBuy.TABLE + " (ID INT IDENTITY PRIMARY KEY, TITLE VARCHAR(100) NOT NULL UNIQUE, ADD_DATE INT NULL)");

        jdbcTemplate.update("CREATE INDEX FILMSTOBUY_IDX ON " + IDaoFilmsToBuy.TABLE + "(ID)");
    }

    @Override
    public void update(Version from) {
        //Nothing for the moment
    }

    @Override
    public void importDataFromHSQL(Iterable<Insert> inserts) {
        for (Insert insert : inserts) {
            if ("OD_FILM_TO_BUY".equals(insert.getTable())) {
                jdbcTemplate.update("INSERT INTO " + IDaoFilmsToBuy.TABLE + " (TITLE, ADD_DATE) VALUES(?, ?)", insert.getString(3), insert.getInt(2));
            }
        }
    }
}