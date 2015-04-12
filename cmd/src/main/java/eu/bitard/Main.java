package eu.bitard;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

public class Main {

    private static final Path parentDirectory = Paths.get("dataFolder");
    private static final Path data = Paths.get(parentDirectory.toString(), "data");

    public static void main(String[] args) throws IOException, InterruptedException {
        if (!Files.exists(data)) {
            Files.createDirectories(parentDirectory);
            Files.createFile(data);
        }
        List<String> lines = Files.readAllLines(data);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                Charset charset = Charset.forName("utf-8");
                try (BufferedWriter writer = Files.newBufferedWriter(data, charset)) {
                    for (String line : lines) {
                        writer.write(line, 0, line.length());
                        writer.newLine();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException("Couldn't save lines");
                }
            }
        });

        while (true) {
            lines.add(Instant.now().toString());
            Thread.sleep(1000);
        }
    }
}
