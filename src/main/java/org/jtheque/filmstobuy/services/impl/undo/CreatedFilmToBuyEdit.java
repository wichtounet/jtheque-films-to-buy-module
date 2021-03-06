package org.jtheque.filmstobuy.services.impl.undo;

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
        return "undo.edits.create";
    }
}
