package org.jtheque.filmstobuy.view.impl.model;

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
import org.jtheque.core.managers.log.ILoggingManager;
import org.jtheque.core.managers.persistence.able.DataListener;
import org.jtheque.filmstobuy.persistence.od.able.FilmToBuy;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.utils.bean.IntDate;
import org.jtheque.utils.collections.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import java.util.List;

/**
 * A table model to display films to buy.
 *
 * @author Baptiste Wicht
 */
public final class FilmsToBuyTableModel extends AbstractTableModel implements DataListener, TableModelListener {
    private static final long serialVersionUID = 1296194815930212582L;

    /**
     * The different columns of the films to buy table.
     *
     * @author Baptiste Wicht
     */
    private interface Columns {
        int ID = 0;
        int NAME = 1;
        int DATE = 2;
    }

    /**
     * Headers of the table.
     */
    private String[] headers;

    /**
     * The films to buy to display.
     */
    private List<FilmToBuy> films;

    @Resource
    private IFilmsToBuyService filmsToBuyService;

    /**
     * Construct a new <code>FilmsToBuyTableModel</code>.
     */
    public FilmsToBuyTableModel() {
        super();

        addTableModelListener(this);
    }

    /**
     * Init the model.
     */
    @PostConstruct
    private void init() {
        filmsToBuyService.addDataListener(this);

        films = CollectionUtils.copyOf(filmsToBuyService.getFilmsToBuy());
    }

    /**
     * Set the header of the table.
     *
     * @param headers The header of the table model
     */
    public void setHeader(String[] headers) {
        this.headers = headers.clone();

        fireTableStructureChanged();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public int getRowCount() {
        return films.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;

        FilmToBuy film = films.get(rowIndex);

        if (film != null) {
            switch (columnIndex) {
                case Columns.ID:
                    value = film.getId();
                    break;
                case Columns.NAME:
                    value = film.getTitle();
                    break;
                case Columns.DATE:
                    value = film.getDate();
                    break;
                default:
                    value = "";
                    break;
            }
        }

        return value;
    }

    @Override
    public void setValueAt(Object value, int column, int row) {
        if (value != null) {
            FilmToBuy film = films.get(row);

            boolean edited = false;

            switch (column) {
                case Columns.NAME:
                    film.setTitle((String) value);
                    edited = true;
                    break;
                case Columns.DATE:
                    film.setDate(new IntDate((Integer) value));
                    edited = true;
                    break;
                default:
                    Managers.getManager(ILoggingManager.class).getLogger(getClass()).
                            debug("Anormal entry in switch clause. ");
                    break;
            }

            if (edited) {
                filmsToBuyService.save(film);
            }

            fireTableCellUpdated(column, row);
        }
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == Columns.NAME || column == Columns.DATE;
    }

    @Override
    public void tableChanged(TableModelEvent event) {
        //If it's a modification and if it's en event on a specific column.
        if (event.getType() == TableModelEvent.UPDATE &&
                event.getColumn() != -1 &&
                event.getFirstRow() == event.getLastRow()) {
            filmsToBuyService.save(films.get(event.getFirstRow()));
        }
    }

    @Override
    public void dataChanged() {
        films.clear();
        films.addAll(filmsToBuyService.getFilmsToBuy());

        fireTableStructureChanged();
    }
}
