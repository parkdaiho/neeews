package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NicknameDupCheckResponse {

    private Boolean flag;
    private Boolean identification;

    public NicknameDupCheckResponse(boolean flag, Boolean identification) {
        this.flag = flag;
        this.identification = identification;
    }
}
