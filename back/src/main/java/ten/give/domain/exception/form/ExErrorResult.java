package ten.give.domain.exception.form;

import lombok.Data;

@Data
public class ExErrorResult {

    private String code;
    private String body;

    public ExErrorResult() {
    }

    public ExErrorResult(String code, String body) {
        this.code = code;
        this.body = body;
    }
}
