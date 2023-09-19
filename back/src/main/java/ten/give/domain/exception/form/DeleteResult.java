package ten.give.domain.exception.form;

import lombok.Data;

@Data
public class DeleteResult {

    private boolean delete;

    public DeleteResult() {
    }

    public DeleteResult(boolean delete) {
        this.delete = delete;
    }
}
