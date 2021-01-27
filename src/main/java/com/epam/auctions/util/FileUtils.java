package com.epam.auctions.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Optional;
import java.util.UUID;

public class FileUtils {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static String generateFilenameBasedOnUUID(String filename) {
        return UUID.randomUUID() + "." + getFileExtension(filename).get();
    }

    public static void writeStreamTo(InputStream stream, String path) throws IOException {
        final File targetFile = new File(path);
        Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
