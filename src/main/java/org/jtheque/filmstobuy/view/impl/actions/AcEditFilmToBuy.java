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
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.filmstobuy.controller.able.IToBuyController;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;

import org.jdesktop.swingx.JXTable;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

/**
 * Action to edit a film to buy.
 *
 * @author Baptiste Wicht
 */
public final class AcEditFilmToBuy extends JThequeAction {
    /**
     * Construct a new AcEditFilmToBuy.
     */
    public AcEditFilmToBuy() {
        super("tobuy.actions.edit");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        IFilmsToBuyService filmsToBuyService = Managers.getManager(IBeansManager.class).getBean("filmsToBuyService");
        IToBuyController toBuyController = Managers.getManager(IBeansManager.class).getBean("toBuyController");

        JXTable table = toBuyController.getView().getTable();

        //Make sure that only one film is selected
        if (table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, Managers.getManager(ILanguageManager.class).getMessage("tobuy.dialogs.selectFilm"));
        } else if (table.getSelectedRowCount() == 1) {
            FilmToBuy film = filmsToBuyService.getFilmToBuy(
                    (Integer) table.getModel().getValueAt(
                            table.convertRowIndexToModel(table.getSelectedRow()), 0));

            String title = JOptionPane.showInputDialog(null,
                    Managers.getManager(ILanguageManager.class).getMessage("tobuy.dialogs.modifyFilm"),
                    film.getTitle());

            if (title != null) {
                toBuyController.editFilmToBuy(film.getId(), title);
            }
        } else {
            JOptionPane.showMessageDialog(null, Managers.getManager(ILanguageManager.class).getMessage("tobuy.errors.toofilms"));
        }
    }
}
