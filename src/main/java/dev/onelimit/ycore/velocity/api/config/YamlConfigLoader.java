package dev.onelimit.ycore.velocity.api.config;

import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class YamlConfigLoader<T> {
    private final Logger logger;
    private final Path dataDirectory;
    private final Path configPath;
    private final String bundledResourceName;
    private final Yaml yaml;
    private final Function<Map<?, ?>, T> parser;
    private final Supplier<T> fallbackSupplier;

    public YamlConfigLoader(
        Logger logger,
        Path dataDirectory,
        String fileName,
        String bundledResourceName,
        Function<Map<?, ?>, T> parser,
        Supplier<T> fallbackSupplier
    ) {
        this.logger = logger;
        this.dataDirectory = dataDirectory;
        this.configPath = dataDirectory.resolve(fileName);
        this.bundledResourceName = bundledResourceName;
        this.yaml = new Yaml();
        this.parser = parser;
        this.fallbackSupplier = fallbackSupplier;
    }

    public T load() {
        ensureDefaultExists();

        try (Reader reader = Files.newBufferedReader(configPath, StandardCharsets.UTF_8)) {
            Object loaded = yaml.load(reader);
            if (!(loaded instanceof Map<?, ?> root)) {
                logger.warn("Config root in {} is invalid; using defaults.", configPath.getFileName());
                return fallbackSupplier.get();
            }
            return parser.apply(root);
        } catch (Exception ex) {
            logger.error("Failed to load {}, using defaults.", configPath.getFileName(), ex);
            return fallbackSupplier.get();
        }
    }

    private void ensureDefaultExists() {
        try {
            Files.createDirectories(dataDirectory);
            if (Files.exists(configPath)) {
                return;
            }

            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(bundledResourceName)) {
                if (stream == null) {
                    throw new IOException("Missing bundled resource: " + bundledResourceName);
                }
                Files.copy(stream, configPath);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Could not initialize " + configPath.getFileName(), ex);
        }
    }
}
