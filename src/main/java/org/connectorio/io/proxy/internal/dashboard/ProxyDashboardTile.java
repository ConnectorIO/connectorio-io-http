/**
 * Copyright (C) 2019 Connectorio Sp. z o.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.connectorio.io.proxy.internal.dashboard;

import org.openhab.ui.dashboard.DashboardTile;

import java.util.Dictionary;

public class ProxyDashboardTile implements DashboardTile {

    public static final String DASHBOARD_NAME = "dashboard-name";
    public static final String DASHBOARD_IMAGE = "dashboard-image";
    public static final String DASHBOARD_OVERLAY = "dashboard-overlay";
    private final String url;
    private final String name;
    private final String image;
    private final String overlay;

    public ProxyDashboardTile(String path, Dictionary<String, ?> properties) {
        this.url = path;
        this.name = (String) properties.get(DASHBOARD_NAME);
        this.image = (String) properties.get(DASHBOARD_IMAGE);
        this.overlay = (String) properties.get(DASHBOARD_OVERLAY);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getImageUrl() {
        return image;
    }

    @Override
    public String getOverlay() {
        return overlay;
    }

}
