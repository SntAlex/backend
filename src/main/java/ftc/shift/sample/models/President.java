package ftc.shift.sample.models;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

public class President {
    @ApiModelProperty(value = "Имя старосты", required = true)
    private String name;
    @ApiModelProperty(value = "Пароль старосты", required = true)
    private String password;

    public President() {

    }

    public President(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() { return name; }
    public String getPassword() { return password; }

    public void setName(String name) {  this.name = name; }
    public void setPassword(String password) { this.password = password;}
}
