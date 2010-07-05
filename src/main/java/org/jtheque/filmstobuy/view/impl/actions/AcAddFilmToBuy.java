package org.jtheque.filmstobuy.view.impl.actions;

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
