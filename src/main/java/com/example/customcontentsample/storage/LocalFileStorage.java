package com.example.customcontentsample.storage;

import com.example.customcontentsample.entity.LocalFileRef;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Component
public class LocalFileStorage {

    private static final Logger log = LoggerFactory.getLogger(LocalFileStorage.class);

    protected volatile Path storageRoot;

    public LocalFileRef saveStream(LocalFileRef fileRef, InputStream stream) {
        Path relativePath = createRelativeFilePath(fileRef.getOriginalFileName());
        String path = pathToString(relativePath);
        save(path, stream);
        fileRef.setPath(path);
        return fileRef;
    }

    public Resource getResource(String path) {
        Path relativePath = getRelativePath(path);

        Path root = getStorageRoot();
        if (root == null) {
            log.error("No storage directories available");
            throw new RuntimeException("File not found: " + path);
        }

        Path pathObj = root.resolve(relativePath);

        if (!pathObj.toFile().exists()) {
            log.error("File " + pathObj + " not found");
            throw new RuntimeException("File not found: " + pathObj);
        }

        return new FileSystemResource(pathObj);
    }

    private void save(String strPath, InputStream inputStream) {
        Path relativePath = getRelativePath(strPath);

        Path root = getStorageRoot();

        Path path = root.resolve(relativePath);
        if (!path.getParent().toFile().exists() && !path.getParent().toFile().mkdirs()) {
            throw new RuntimeException("Cannot create directory: " + path.getParent().toAbsolutePath());
        }

        checkStorageAccessible(root, strPath);

        try (OutputStream outputStream = Files.newOutputStream(path, CREATE_NEW)) {
            IOUtils.copyLarge(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            FileUtils.deleteQuietly(path.toFile());
            throw new RuntimeException("I/O Exception: " + path.toAbsolutePath(), e);
        }
    }

    protected Path createRelativeFilePath(String fileName) {
        return createDateDirPath().resolve(createUuidFilename(fileName));
    }

    protected Path createDateDirPath() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return Paths.get(String.valueOf(year),
                StringUtils.leftPad(String.valueOf(month), 2, '0'),
                StringUtils.leftPad(String.valueOf(day), 2, '0'));
    }

    protected void checkStorageAccessible(Path root, String fileName) {
        if (!root.toFile().exists() && !root.toFile().mkdirs()) {
            log.error("Inaccessible storage at {}", root);
            throw new RuntimeException("Inaccessible primary storage for file: " + fileName);
        }
    }

    /**
     * Converts string path to {@link Path}.
     */
    protected Path getRelativePath(String path) {
        String[] parts = path.split("/", 4);
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid path");
        }
        return Paths.get(parts[0], parts[1], parts[2], parts[3]);
    }


    /**
     * Converts path to a uniform string representation ("yyyy/mm/dd/uuid.ext").
     */
    protected String pathToString(Path path) {
        return path.toString().replace('\\', '/');
    }

    protected String createUuidFilename(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        if (StringUtils.isNotEmpty(extension)) {
            return UUID.randomUUID() + "." + extension;
        } else {
            return UUID.randomUUID().toString();
        }
    }

    protected Path getStorageRoot() {
        if (storageRoot == null) {
            String workDir = ".amplicode";
            Path dir = Paths.get(workDir, "filestorage");
            if (!dir.toFile().exists() && !dir.toFile().mkdirs()) {
                throw new RuntimeException("Cannot create filestorage directory: " + dir.toAbsolutePath());
            }
            storageRoot = dir;
        }
        return storageRoot;
    }
}
