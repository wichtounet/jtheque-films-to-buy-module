package org.jtheque.filmstobuy.controller.impl;

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

import org.jtheque.core.managers.view.able.controller.AbstractController;
import org.jtheque.filmstobuy.controller.able.IToBuyController;
import org.jtheque.filmstobuy.services.able.IFilmsToBuyService;
import org.jtheque.filmstobuy.view.able.IFilmsToBuyView;

import javax.annotation.Resource;

/**
 * The View Controller for films to buy.
 *
 * @author Baptiste Wicht
 */
public final class ToBuyController extends AbstractController implements IToBuyController {
    @Resource
    private IFilmsToBuyService filmsToBuyService;

    @Resource
    private IFilmsToBuyView filmsToBuyView;

    @Override
    public IFilmsToBuyView getView() {
        return filmsToBuyView;
    }

    @Override
    public void newFilmToBuy(String title) {
        filmsToBuyService.newFilmToBuy(title);
    }

    @Override
    public void deleteFilmToBuy(int index) {
        filmsToBuyService.delete(index);
        filmsToBuyView.refresh();
    }

    @Override
    public void editFilmToBuy(Integer index, String title) {
        filmsToBuyService.editFilmToBuy(index, title);
    }
}
