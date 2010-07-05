package org.jtheque.filmstobuy.services.impl;

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
