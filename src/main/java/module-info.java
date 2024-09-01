import exec.mode.IModeExecutor;
import exec.mode.InitExecutor;
import exec.mode.SubmitExecutor;
import exec.mode.TestSolutionExecutor;

module advent.of.code {
    uses IModeExecutor;
    requires com.fasterxml.jackson.databind;
    requires org.jsoup;
    requires java.logging;
    provides IModeExecutor with InitExecutor,
            SubmitExecutor,
            TestSolutionExecutor;
    opens answer to com.fasterxml.jackson.databind;
}