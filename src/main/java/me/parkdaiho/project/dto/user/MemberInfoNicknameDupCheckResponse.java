package me.parkdaiho.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoNicknameDupCheckResponse {

    private Boolean flag;
    private Boolean identification;

    public MemberInfoNicknameDupCheckResponse(boolean flag, boolean identification) {
        this.flag = flag;
        this.identification = identification;
    }
}
