package exec.mode;

import util.AocUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class InitExecutor implements IModeExecutor {

    @Override
    public ApplicationMode mode() {
        return ApplicationMode.INIT;
    }

    @Override
    public void run() {
        final Properties props = AocUtil.getProperties();
        final int day = Integer.parseInt(props.getProperty("day"));
        final Path templatePath = Path.of(STR."./src/main/resources/templates/Solution.java");
        final Path initPath = Path.of(STR."./src/main/java/solution/day\{day}/Solution.java");
        try {
            String template = new String(Files.readAllBytes(templatePath));
            template = template.replace("${dayNum}", String.valueOf(day));
            Files.createDirectory(initPath.getParent());
            Files.write(initPath, template.getBytes(), CREATE_NEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
