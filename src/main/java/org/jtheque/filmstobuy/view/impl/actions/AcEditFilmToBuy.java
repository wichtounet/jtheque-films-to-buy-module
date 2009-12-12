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

import org.jdesktop.swingx.JXTable;
import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.filmstobuy.controller.able.IToBuyController;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;

import javax.annotation.Resource;
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