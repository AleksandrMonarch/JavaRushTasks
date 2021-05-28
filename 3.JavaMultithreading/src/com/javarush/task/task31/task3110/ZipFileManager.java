package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {

    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        if (!Files.exists(zipFile.getParent())) Files.createDirectory(zipFile.getParent());
        addNewZipEntry(new ZipOutputStream(new ZipOutputStream((Files.newOutputStream(zipFile)))), source.getParent(), source.getFileName());
    }

    private static void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        try (InputStream in = Files.newInputStream(filePath)) {
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);
            copyData(in, zipOutputStream);
        } finally {
            zipOutputStream.closeEntry();
            zipOutputStream.close();
        }
    }

    private static void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[1024];
        int count;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
    }
}