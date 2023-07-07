package dev.djigit.phonecontacts.dto;

import javax.validation.constraints.NotEmpty;

public class UserDto {
    private Integer id;
    @NotEmpty(message = "login is required")
    private String login;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
