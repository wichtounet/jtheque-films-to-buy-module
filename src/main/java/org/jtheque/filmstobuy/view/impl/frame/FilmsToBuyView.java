package org.jtheque.filmstobuy.view.impl.frame;

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

import org.jtheque.core.managers.view.able.components.IModel;
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingBuildedDialogView;
import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.filmstobuy.view.able.IFilmsToBuyView;
import org.jtheque.filmstobuy.view.impl.JMenuBarToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcAddFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcEditFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcRemoveFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.CloseFilmToBuyViewAction;
import org.jtheque.filmstobuy.view.impl.model.FilmsToBuyTableModel;
import org.jtheque.utils.ui.GridBagUtils;

import org.jdesktop.swingx.JXTable;

import javax.swing.JTable;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;

/**
 * This class represent the graphic interface in which we can add film we want to buy.
 *
 * @author Baptiste Wicht
 */
public final class FilmsToBuyView extends SwingBuildedDialogView<IModel> implements IFilmsToBuyView {
    /**
     * The table to display the films to buy.
     */
    private JXTable tableFilms;

    /**
     * The table model.
     */
    private FilmsToBuyTableModel tableModel;

    @Override
    protected void initView() {
        setJMenuBar(new JMenuBarToBuy());

        setTitleKey("filmstobuy.view.title");
    }

    @Override
    protected void buildView(PanelBuilder builder) {
        updateHeaders();

        AcRemoveFilmToBuy removeAction = new AcRemoveFilmToBuy();
        AcAddFilmToBuy addAction = new AcAddFilmToBuy();

        tableModel = new FilmsToBuyTableModel();

        tableFilms = new JXTable(tableModel);
        tableFilms.setSortable(true);
        tableFilms.getTableHeader().setReorderingAllowed(false);
        tableFilms.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tableFilms.setColumnControlVisible(true);
        tableFilms.getColumnExt(getMessage("filmstobuy.view.table.id")).setVisible(false);
        tableFilms.getActionMap().put("delete", removeAction);
        tableFilms.getActionMap().put("add", addAction);
        tableFilms.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        tableFilms.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "add");
        tableFilms.packAll();

        builder.addScrolled(tableFilms, builder.gbcSet(0, 0, GridBagUtils.BOTH, GridBagUtils.FIRST_LINE_START, 1.0, 1.0));

        builder.addButtonBar(builder.gbcSet(0, 1, GridBagUtils.HORIZONTAL),
                addAction, removeAction, new AcEditFilmToBuy(), new CloseFilmToBuyViewAction());
    }

    /**
     * Update the headers of the table.
     */
    private void updateHeaders() {
        tableModel.setHeader(new String[]{
                getMessage("filmstobuy.view.table.id"),
                getMessage("filmstobuy.view.table.name"),
                getMessage("filmstobuy.view.table.date")});
    }

    @Override
    public JXTable getTable() {
        return tableFilms;
    }

    @Override
    public void refreshText() {
        super.refreshText();

        updateHeaders();

        tableFilms.getColumnExt(getMessage("filmstobuy.view.table.id")).setVisible(false);
    }
}
