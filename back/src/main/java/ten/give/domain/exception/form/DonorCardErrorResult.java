package ten.give.domain.exception.form;

import lombok.Data;

@Data
public class DonorCardErrorResult {

    private String code;
    private String body;

    public DonorCardErrorResult() {
    }

    public DonorCardErrorResult(String code, String body) {
        this.code = code;
        this.body = body;
    }
}
