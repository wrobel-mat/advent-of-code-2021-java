import exec.mode.IModeExecutor;
import exec.mode.InitExecutor;
import exec.mode.SubmitExecutor;

module advent.of.code {
    uses IModeExecutor;
    requires com.fasterxml.jackson.databind;
    requires org.jsoup;
    provides IModeExecutor with InitExecutor,
            SubmitExecutor;
    opens answer to com.fasterxml.jackson.databind;
}