package ftc.shift.sample.api;

import ftc.shift.sample.models.President;
import ftc.shift.sample.services.PresidentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(PRESIDENT_PATH)
    @ApiOperation(value = "Получение объекта старосты")
    public ResponseEntity<President> fetchPresident() {
        President president = service.fetchPresident();
        return ResponseEntity.ok(president);
    }
}
