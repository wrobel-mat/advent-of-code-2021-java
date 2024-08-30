package exec;

import exec.mode.ApplicationMode;


public interface IModeExecutor {
    ApplicationMode mode();
    void run();
}
