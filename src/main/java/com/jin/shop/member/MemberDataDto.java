package com.jin.shop.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MemberDataDto {
    public String username;
    public String displayName;
    public Long id;
    MemberDataDto(String username, String displayName, Long id) {
        this.username = username;
        this.displayName = displayName;
        this.id = id;
    }
}