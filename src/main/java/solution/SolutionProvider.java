package solution;

import java.lang.reflect.InvocationTargetException;

public class SolutionProvider {

    private SolutionProvider() {
    }

    public static ISolution getSolution(int day) {
        try {
            return (ISolution) Class.forName(STR."solution.day\{day}.Solution").getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(STR."Day \{day} has not been initialized - rerun the application with `init` argument", e);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
