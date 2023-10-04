package ten.give.domain.exception.form;

import lombok.Data;

@Data
public class ResultForm {

    private boolean result;
    private String body;

    public ResultForm() {
    }

    public ResultForm(boolean result, String body) {
        this.result = result;
        this.body = body;
    }
}
