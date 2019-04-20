package techosolution.model;

import java.io.Serializable;
import java.util.List;

public class ResponseBody implements Serializable {
    private static final long serialVersionUID = 1L;


    protected String status;

    protected String statusDesc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
