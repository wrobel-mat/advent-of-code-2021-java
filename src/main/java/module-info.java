module advent.of.code {
    uses exec.IModeExecutor;
    requires com.fasterxml.jackson.databind;
    requires org.jsoup;
    provides exec.IModeExecutor with exec.InitExecutor,
            exec.SubmitExecutor;
    opens answer to com.fasterxml.jackson.databind;
}