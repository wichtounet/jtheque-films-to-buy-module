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
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.filmstobuy.controller.able.IToBuyController;

import org.jdesktop.swingx.JXTable;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

/**
 * Action to delete a film to buy.
 *
 * @author Baptiste Wicht
 */
public final class AcRemoveFilmToBuy extends JThequeAction {
    /**
     * Construct a new AcRemoveFilmToBuy.
     */
    public AcRemoveFilmToBuy() {
        super("tobuy.actions.remove");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        IToBuyController toBuyController = Managers.getManager(IBeansManager.class).<IToBuyController>getBean("toBuyController");

        JXTable table = toBuyController.getView().getTable();

        //Make sure that one or more films are selected
        if (table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, Managers.getManager(ILanguageManager.class).getMessage("tobuy.dialogs.selectFilm"));
        } else if (table.getSelectedRowCount() == 1) {
            toBuyController.deleteFilmToBuy((Integer) table.getModel().getValueAt(
                    table.convertRowIndexToModel(table.getSelectedRow()), 0));
        } else {
            int[] toDelete = table.getSelectedRows();

            for (int index : toDelete) {
                int modelIndex = table.convertRowIndexToModel(index);
                toBuyController.deleteFilmToBuy((Integer) table.getModel().getValueAt(modelIndex, 0));
            }
        }
    }
}