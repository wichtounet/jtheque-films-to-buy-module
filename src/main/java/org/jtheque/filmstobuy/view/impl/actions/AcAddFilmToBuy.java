package org.jtheque.filmstobuy.view.impl.actions;

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
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.core.utils.ui.ValidationUtils;
import org.jtheque.filmstobuy.controller.able.IToBuyController;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Action to add a film to buy.
 *
 * @author Baptiste Wicht
 */
public final class AcAddFilmToBuy extends JThequeAction {
    /**
     * Construct a new AcAddFilmToBuy.
     */
    public AcAddFilmToBuy() {
        super("tobuy.actions.add");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String title = Managers.getManager(IViewManager.class).askUserForText(
                Managers.getManager(ILanguageManager.class).getMessage("tobuy.dialogs.newFilm"));

        Collection<JThequeError> errors = new ArrayList<JThequeError>(1);

        ValidationUtils.rejectIfEmpty(title, "tobuy.dialogs.newFilm.title", errors);

        if (errors.isEmpty()) {
            Managers.getManager(IBeansManager.class).<IToBuyController>getBean("toBuyController").newFilmToBuy(title);
        }
    }
}