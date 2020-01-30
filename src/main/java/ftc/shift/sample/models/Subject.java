package ftc.shift.sample.models;

import io.swagger.annotations.ApiModelProperty;

public class Subject {
    @ApiModelProperty(value = "Уникальный идентификатор предмета", required = true)
    private Integer id;
    @ApiModelProperty(value = "Название предмета", required = true)
    private String name;
    @ApiModelProperty(value = "Аудитория", required = true)
    private String audience;
    @ApiModelProperty(value = "Время", required = true)
    private String time;
    @ApiModelProperty(value = "Заметки")
    private String note;


    public Subject() {

    }

    public Subject(Integer id, String name, String audience, String time, String note) {
        this.id = id;
        this.name = name;
        this.audience = audience;
        this.note = note;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAudience() {
        return audience;
    }

    public String getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
