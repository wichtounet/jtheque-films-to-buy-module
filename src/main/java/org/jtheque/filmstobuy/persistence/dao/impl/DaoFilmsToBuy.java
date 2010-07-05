package org.jtheque.filmstobuy.persistence.dao.impl;

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

import org.jtheque.core.managers.persistence.GenericDao;
import org.jtheque.core.managers.persistence.Query;
import org.jtheque.core.managers.persistence.QueryMapper;
import org.jtheque.core.managers.persistence.able.Entity;
import org.jtheque.core.managers.persistence.context.IDaoPersistenceContext;
import org.jtheque.filmstobuy.persistence.dao.able.IDaoFilmsToBuy;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.persistence.od.impl.FilmToBuyImpl;
import org.jtheque.utils.bean.IntDate;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import javax.annotation.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Data Access Object implementation for films to buy.
 *
 * @author Baptiste Wicht
 */
public final class DaoFilmsToBuy extends GenericDao<FilmToBuy> implements IDaoFilmsToBuy {
    private final ParameterizedRowMapper<FilmToBuy> rowMapper = new FilmToBuyRowMapper();
    private final QueryMapper queryMapper = new FilmToBuyQueryMapper();

    @Resource
    private IDaoPersistenceContext persistenceContext;

    /**
     * Construct a new DaoFilmsToBuy.
     */
    public DaoFilmsToBuy() {
        super(TABLE);
    }

    @Override
    public Collection<FilmToBuy> getFilmsToBuy() {
        return getAll();
    }

    @Override
    public FilmToBuy getFilmToBuy(int id) {
        return get(id);
    }

    @Override
    protected ParameterizedRowMapper<FilmToBuy> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected QueryMapper getQueryMapper() {
        return queryMapper;
    }

    @Override
    protected void loadCache() {
        Collection<FilmToBuy> filmToBuys = persistenceContext.getSortedList(TABLE, rowMapper);

        for (FilmToBuy filmToBuy : filmToBuys) {
            getCache().put(filmToBuy.getId(), filmToBuy);
        }

        setCacheEntirelyLoaded();
    }

    @Override
    protected void load(int i) {
        FilmToBuy filmToBuy = persistenceContext.getDataByID(TABLE, i, rowMapper);

        getCache().put(i, filmToBuy);
    }

    @Override
    public FilmToBuy createFilmToBuy() {
        return new FilmToBuyImpl();
    }

    /**
     * A row mapper to map ResultSet to FilmToBuyImpl.
     *
     * @author Baptiste Wicht
     */
    private final class FilmToBuyRowMapper implements ParameterizedRowMapper<FilmToBuy> {
        @Override
        public FilmToBuy mapRow(ResultSet rs, int i) throws SQLException {
            FilmToBuy filmToBuy = createFilmToBuy();

            filmToBuy.setId(rs.getInt("ID"));
            filmToBuy.setTitle(rs.getString("TITLE"));
            filmToBuy.setDate(new IntDate(rs.getInt("ADD_DATE")));

            return filmToBuy;
        }
    }

    /**
     * A row mapper to map a FilmToBuyImpl to a request.
     *
     * @author Baptiste Wicht
     */
    private static final class FilmToBuyQueryMapper implements QueryMapper {
        @Override
        public Query constructInsertQuery(Entity entity) {
            FilmToBuy filmToBuy = (FilmToBuy) entity;

            String query = "INSERT INTO " + IDaoFilmsToBuy.TABLE + " (TITLE, ADD_DATE) VALUES(?, ?)";

            Object[] parameters = {
                    filmToBuy.getTitle(),
                    filmToBuy.getDate().intValue()
            };

            return new Query(query, parameters);
        }

        @Override
        public Query constructUpdateQuery(Entity entity) {
            FilmToBuy filmToBuy = (FilmToBuy) entity;

            String query = "UPDATE " + IDaoFilmsToBuy.TABLE + " SET TITLE = ?, ADD_DATE = ? WHERE ID = ?";

            Object[] parameters = {
                    filmToBuy.getTitle(),
                    filmToBuy.getDate().intValue(),
                    filmToBuy.getId()
            };

            return new Query(query, parameters);
        }
    }
}
