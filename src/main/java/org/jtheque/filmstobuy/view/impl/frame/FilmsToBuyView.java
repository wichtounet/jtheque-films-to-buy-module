package org.jtheque.filmstobuy.view.impl.frame;

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
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingDialogView;
import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.filmstobuy.view.able.IFilmsToBuyView;
import org.jtheque.filmstobuy.view.impl.JMenuBarToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcAddFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcEditFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcRemoveFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.CloseFilmToBuyViewAction;
import org.jtheque.filmstobuy.view.impl.model.FilmsToBuyTableModel;
import org.jtheque.utils.ui.GridBagUtils;

import javax.swing.JTable;
import javax.swing.KeyStroke;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.Collection;

/**
 * This class represent the graphic interface in which we can add film we want to buy.
 *
 * @author Baptiste Wicht
 */
public final class FilmsToBuyView extends SwingDialogView implements IFilmsToBuyView {
    /**
     * The table to display the films to buy.
     */
    private JXTable tableFilms;

    /**
     * The table model.
     */
    private FilmsToBuyTableModel tableModel;

    /* Instances */
    private final ILanguageManager resources = Managers.getManager(ILanguageManager.class);

    /**
     * Construct a new <code>JFrameFilmsToBuy</code>.
     *
     * @param parent The parent frame.
     */
    public FilmsToBuyView(Frame parent) {
        super(parent);

        setJMenuBar(new JMenuBarToBuy());

        setTitleKey("filmstobuy.view.title");
        setContentPane(buildContentPane());

        pack();
        setLocationRelativeTo(getOwner());
    }

    /**
     * Build the content pane.
     *
     * @return The content pane.
     */
    private Container buildContentPane() {
        PanelBuilder builder = new PanelBuilder();

        updateHeaders();

        AcRemoveFilmToBuy removeAction = new AcRemoveFilmToBuy();
        AcAddFilmToBuy addAction = new AcAddFilmToBuy();

        tableModel = new FilmsToBuyTableModel();

        tableFilms = new JXTable(tableModel);
        tableFilms.setSortable(true);
        tableFilms.getTableHeader().setReorderingAllowed(false);
        tableFilms.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        tableFilms.setColumnControlVisible(true);
        tableFilms.getColumnExt(resources.getMessage("filmstobuy.view.table.id")).setVisible(false);
        tableFilms.getActionMap().put("delete", removeAction);
        tableFilms.getActionMap().put("add", addAction);
        tableFilms.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        tableFilms.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "add");
        tableFilms.packAll();

        builder.addScrolled(tableFilms, builder.gbcSet(0, 0, GridBagUtils.BOTH, GridBagUtils.FIRST_LINE_START, 1.0, 1.0));

        builder.addButtonBar(builder.gbcSet(0, 1, GridBagUtils.HORIZONTAL),
                addAction, removeAction, new AcEditFilmToBuy(), new CloseFilmToBuyViewAction());

        return builder.getPanel();
    }

    /**
     * Update the headers of the table.
     */
    private void updateHeaders() {
        tableModel.setHeader(new String[]{
                resources.getMessage("filmstobuy.view.table.id"),
                resources.getMessage("filmstobuy.view.table.name"),
                resources.getMessage("filmstobuy.view.table.date")});
    }

    @Override
    public JXTable getTable() {
        return tableFilms;
    }

    @Override
    public void refreshText() {
        super.refreshText();

        updateHeaders();

        tableFilms.getColumnExt(resources.getMessage("filmstobuy.view.table.id")).setVisible(false);
    }

    @Override
    protected void validate(Collection<JThequeError> errors) {
    }
}