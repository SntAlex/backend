package ftc.shift.sample.api;


import ftc.shift.sample.services.PresidentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("ALL")
@RestController
@Api(description = "Запросы для работы со старостой")
public class PresidentController {
    private static final String PRESIDENT_PATH = "/api/v001/President";
    private PresidentService service;

    @Autowired
    public PresidentController(PresidentService service) {
        this.service = service;
    }

    @GetMapping(PRESIDENT_PATH + "/password")
    @ApiOperation(value = "Получение пароля старосты")
    public ResponseEntity<String> getPresidentPassword() {
        String password = service.getPresidentPassword();
        return ResponseEntity.ok(password);
    }

    @GetMapping(PRESIDENT_PATH + "/name")
    @ApiOperation(value = "Получение имени старосты")
    public ResponseEntity<String> getPresidentName() {
        String name = service.getPresidentName();
        return ResponseEntity.ok(name);
    }

    @PutMapping(PRESIDENT_PATH + "/password")
    @ApiOperation(value = "Изменение пароля старосты")
    public ResponseEntity<?> setPresidentPassword(@RequestHeader String userPassword) {
        service.setPresidentPassword(userPassword);
        return ResponseEntity.ok().build();
    }

    @PutMapping(PRESIDENT_PATH + "/name")
    @ApiOperation(value = "Изменение имени старосты")
    public ResponseEntity<?> setPresidentName(@RequestHeader String userName) {
        service.setPresidentName(userName);
        return ResponseEntity.ok().build();
    }

}
