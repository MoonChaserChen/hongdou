package ink.akira.common.pojo;

/**
 * Created by akira on 2019/3/28.
 */
public class JsonResult {
    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    public static final String OK = "OK";
    private int status;
    private String message;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonResult success(Object data) {
        return new JsonResult(SUCCESS, OK, data);
    }

    public static JsonResult fail(int status, String error) {
        return new JsonResult(status, error, null);
    }
}
