/*
 * Copyright (C) 2021  Andriy Preizner
 *
 * This file is a part of Open Poker jira plugin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aprey.jira.plugin.openpoker.api;

import com.aprey.jira.plugin.openpoker.persistence.PersistenceService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

public interface ActionProcessor {

    default Optional<Integer> getParam(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        if (param == null || param.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(param));
        } catch (NumberFormatException ignore) {
            return Optional.empty();
        }
    }

    //TODO: persistanceService should be injected to prcossors instead of passing as parameter
    void process(PersistenceService persistenceService, HttpServletRequest request, String issueId);
}
