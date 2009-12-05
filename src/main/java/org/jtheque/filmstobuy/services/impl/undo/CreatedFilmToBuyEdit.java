package org.jtheque.filmstobuy.services.impl.undo;

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
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;

import javax.annotation.Resource;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * An edit corresponding to a creation of an author.
 *
 * @author Baptiste Wicht
 */
public final class CreatedFilmToBuyEdit extends AbstractUndoableEdit {
    private static final long serialVersionUID = -3037357344759861059L;

    private final FilmToBuy filmToBuy;

    @Resource
    private IFilmsToBuyService filmsToBuyService;

    /**
     * Construct a new CreatedAuthorEdit.
     *
     * @param filmToBuy The created film To Buy.
     */
    public CreatedFilmToBuyEdit(FilmToBuy filmToBuy) {
        super();

        Managers.getManager(IBeansManager.class).inject(this);

        this.filmToBuy = filmToBuy;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();

        filmsToBuyService.delete(filmToBuy);
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();

        filmsToBuyService.create(filmToBuy);
    }

    @Override
    public String getPresentationName() {
        return Managers.getManager(ILanguageManager.class).getMessage("undo.edits.create");
    }
}