package dev.onelimit.ycore.velocity.api.compat;

import com.velocitypowered.api.proxy.ProxyServer;

import java.util.Locale;

public final class DependencyChecker {
    private final ProxyServer proxyServer;

    public DependencyChecker(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    public boolean hasPlugin(String pluginId) {
        if (pluginId == null || pluginId.isBlank()) {
            return false;
        }

        String expected = pluginId.trim().toLowerCase(Locale.ROOT);
        return proxyServer.getPluginManager().getPlugin(expected).isPresent()
            || proxyServer.getPluginManager().getPlugins().stream()
            .anyMatch(container -> container.getDescription().getId().toLowerCase(Locale.ROOT).contains(expected));
    }
}
