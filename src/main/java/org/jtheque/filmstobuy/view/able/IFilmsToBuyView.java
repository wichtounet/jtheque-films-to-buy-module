package org.jtheque.filmstobuy.view.able;

import org.jdesktop.swingx.JXTable;
import org.jtheque.core.managers.view.able.IWindowView;

/**
 * A films to buy view specification.
 *
 * @author Baptiste Wicht
 */
public interface IFilmsToBuyView extends IWindowView {
    /**
     * Return the JXTable used to display the films to buy.
     *
     * @return The JXTable.
     */
    JXTable getTable();
}
