package org.jtheque.filmstobuy.view.impl;

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
