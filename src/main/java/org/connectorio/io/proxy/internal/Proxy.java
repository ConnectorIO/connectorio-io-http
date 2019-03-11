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
package org.connectorio.io.proxy.internal;

import org.connectorio.io.proxy.internal.dashboard.ProxyDashboardTile;
import org.connectorio.io.proxy.internal.servlet.SimpleHttpProxyServlet;
import org.eclipse.jetty.proxy.AbstractProxyServlet;
import org.openhab.ui.dashboard.DashboardTile;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import javax.servlet.ServletException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

public class Proxy {

    public static final String PATH = "path";
    public static final String HOST = "host";
    public static final String PORT = "port";


    private final BundleContext bundleContext;
    private final String path;
    private final Dictionary<String, ?> properties;
    private final HttpService httpService;
    private ServiceRegistration<DashboardTile> dashboardTile;

    public Proxy(BundleContext context, String path, Dictionary<String, ?> properties, HttpService httpService) {
        this.bundleContext = context;
        this.path = path;
        this.properties = properties;
        this.httpService = httpService;
    }

    public void register() throws ServletException, NamespaceException {
        String host = (String) properties.get("host");
        Integer port = Optional.ofNullable(properties.get("port"))
            .map(Object::toString)
            .map(str -> {
                try {
                    return Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    return null;
                }
            }).orElse(null);


        AbstractProxyServlet proxyServlet = new SimpleHttpProxyServlet(host, port, path);
        httpService.registerServlet(path, proxyServlet, properties, httpService.createDefaultHttpContext());

        if (properties.get(ProxyDashboardTile.DASHBOARD_NAME) != null) {
            dashboardTile = bundleContext.registerService(DashboardTile.class, new ProxyDashboardTile(path, properties), new Hashtable<>());
        }
    }

    public void unregister() {
        if (dashboardTile != null) {
            dashboardTile.unregister();
        }
        httpService.unregister(path);

    }
}
