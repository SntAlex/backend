package ftc.shift.sample.repositories;

import ftc.shift.sample.exception.BadRequest;
import ftc.shift.sample.models.President;
import org.springframework.stereotype.Repository;

@Repository
public class PresidentRepository implements IPresident {
    private President president = new President();

    PresidentRepository() {
        president.setName("Polina");
        president.setPassword("11");
    }


    @Override
    public President getPresident() {
        return president;
    }
}
