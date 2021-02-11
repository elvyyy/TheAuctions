package com.epam.auctions.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

/**
 * The type File utils.
 */
public class FileUtils {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    /**
     * Generates filename based on uuid string.
     *
     * @param filename the filename
     * @return the string
     */
    public static String generateFilenameBasedOnUUID(String filename) {
        return UUID.randomUUID() + "." + getFileExtension(filename).get();
    }

    /**
     * Gets file extension.
     *
     * @param filename the filename
     * @return the file extension
     */
    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    /**
     * Writes stream to {@code InputStream}.
     *
     * @param stream the stream
     * @param path   the path
     * @throws IOException the io exception
     */
    public static void writeStreamTo(InputStream stream, String path) throws IOException {
        final File targetFile = new File(path);
        Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
