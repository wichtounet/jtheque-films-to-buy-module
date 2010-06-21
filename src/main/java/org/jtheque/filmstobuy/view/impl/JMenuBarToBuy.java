package org.jtheque.filmstobuy.view.impl;

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

import org.jtheque.core.managers.view.impl.components.menu.JThequeMenu;
import org.jtheque.core.managers.view.impl.components.menu.JThequeMenuItem;
import org.jtheque.filmstobuy.view.impl.actions.AcAddFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcEditFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.AcRemoveFilmToBuy;
import org.jtheque.filmstobuy.view.impl.actions.CloseFilmToBuyViewAction;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * The menu bar for the films to buy frame.
 *
 * @author Baptiste Wicht
 */
public final class JMenuBarToBuy extends JMenuBar {
    private static final long serialVersionUID = -6754057123605990123L;

    /**
     * Construct a new <code>JMenuBarToBuy</code>.
     */
    public JMenuBarToBuy() {
        super();

        JMenu menu = new JThequeMenu("menu.tobuy");

        menu.add(new JThequeMenuItem(new AcAddFilmToBuy()));
        menu.add(new JThequeMenuItem(new AcEditFilmToBuy()));
        menu.add(new JThequeMenuItem(new AcRemoveFilmToBuy()));

        menu.addSeparator();

        menu.add(new JThequeMenuItem(new CloseFilmToBuyViewAction()));

        add(menu);
    }
}