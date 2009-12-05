package org.jtheque.filmstobuy.services.impl;

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
import org.jtheque.core.managers.persistence.able.DataListener;
import org.jtheque.core.managers.undo.IUndoRedoManager;
import org.jtheque.filmstobuy.persistence.dao.able.IDaoFilmsToBuy;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.filmstobuy.services.impl.undo.CreatedFilmToBuyEdit;
import org.jtheque.filmstobuy.services.impl.undo.DeletedFilmToBuyEdit;
import org.jtheque.utils.bean.IntDate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * The implementation of the films to buy service.
 *
 * @author Baptiste Wicht
 */
public final class FilmsToBuyService implements IFilmsToBuyService {
    @Resource
    private IDaoFilmsToBuy daoFilmsToBuy;

    @Override
    @Transactional
    public void newFilmToBuy(String title) {
        FilmToBuy film = getEmptyFilmToBuy();

        film.setTitle(title);
        film.setDate(IntDate.today());

        daoFilmsToBuy.create(film);

        Managers.getManager(IUndoRedoManager.class).addEdit(new CreatedFilmToBuyEdit(film));
    }

    @Override
    public FilmToBuy getEmptyFilmToBuy() {
        return daoFilmsToBuy.createFilmToBuy();
    }

    @Override
    @Transactional
    public void delete(int index) {
        boolean deleted = daoFilmsToBuy.delete(index);

        if (deleted) {
            Managers.getManager(IUndoRedoManager.class).addEdit(new DeletedFilmToBuyEdit(daoFilmsToBuy.getFilmToBuy(index)));
        }
    }

    @Override
    @Transactional
    public void editFilmToBuy(Integer index, String title) {
        FilmToBuy film = daoFilmsToBuy.getFilmToBuy(index);

        film.setTitle(title);

        daoFilmsToBuy.save(film);
    }

    @Override
    @Transactional
    public void create(FilmToBuy filmToBuy) {
        daoFilmsToBuy.create(filmToBuy);
    }

    @Override
    public Collection<FilmToBuy> getFilmsToBuy() {
        return daoFilmsToBuy.getFilmsToBuy();
    }

    @Override
    @Transactional
    public void save(FilmToBuy film) {
        daoFilmsToBuy.save(film);
    }

    @Override
    public FilmToBuy getFilmToBuy(int id) {
        return daoFilmsToBuy.getFilmToBuy(id);
    }

    @Override
    @Transactional
    public boolean delete(FilmToBuy filmToBuy) {
        return daoFilmsToBuy.delete(filmToBuy);
    }

    @Override
    public Collection<FilmToBuy> getDatas() {
        return daoFilmsToBuy.getFilmsToBuy();
    }

    @Override
    public void addDataListener(DataListener listener) {
        daoFilmsToBuy.addDataListener(listener);
    }

    @Override
    @Transactional
    public void clearAll() {
        daoFilmsToBuy.clearAll();
    }

    @Override
    public String getDataType() {
        return DATA_TYPE;
    }
}