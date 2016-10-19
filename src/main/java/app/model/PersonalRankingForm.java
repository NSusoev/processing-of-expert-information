package app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonalRankingForm {

    @NotNull
    @Size(min=1)
    private String sequence;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "PersonalRankingForm{" +
                "sequence='" + sequence + '\'' +
                '}';
    }
}
