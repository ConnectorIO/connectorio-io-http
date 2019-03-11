# ConnectorIO IO HTTP

This tiny project allows to proxy requests coming to a given path into service deployed elsewhere.
You can use this project to deploy an endpoint which will re-route requests to LAN resources.

Example configuration (`org.openhab.proxy.cfg` or `services/proxy.cfg`):
```
proxy.hvac.path = /hvac
proxy.hvac.port = 80
proxy.hvac.host = 192.168.1.2
proxy.hvac.dashboard-name = HVAC

proxy.influx.path = /influx
proxy.influx.port = 8086
proxy.influx.host = 192.168.1.3
proxy.influx.dashboard-name = Influx
proxy.influx.dashboard-image = /grafana/public/app/plugins/datasource/influxdb/img/influxdb_logo.svg

proxy.grafana.path = /grafana
proxy.grafana.port = 3000
proxy.grafana.host = 192.168.1.4
proxy.grafana.dashboard-name = Grafana
proxy.grafana.dashboard-image = /grafana/public/img/grafana_icon.svg
```