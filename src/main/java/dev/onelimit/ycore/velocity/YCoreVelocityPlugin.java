package dev.onelimit.ycore.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import org.slf4j.Logger;

import java.nio.file.Path;

public final class YCoreVelocityPlugin {
    private final Logger logger;
    private final Path dataDirectory;

    @Inject
    public YCoreVelocityPlugin(Logger logger, @DataDirectory Path dataDirectory) {
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        logger.info("yCore-Velocity initialized at {}", dataDirectory.toAbsolutePath());
    }
}
