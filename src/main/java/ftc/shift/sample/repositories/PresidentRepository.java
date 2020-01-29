package ftc.shift.sample.repositories;

import ftc.shift.sample.exception.BadRequest;
import ftc.shift.sample.models.President;
import org.springframework.stereotype.Repository;

@Repository
public class PresidentRepository implements IPresident {
    private President president = new President();

    PresidentRepository(){
        president.setName("Polina");
        president.setPassword("Utkonosososo");
    }


    @Override
    public void setPresidentName(String name) {
        if(name.isEmpty())
            throw new BadRequest();
        president.setName(name);
    }

    @Override
    public void setPresidentPassword(String password) {
        if(password.isEmpty())
            throw new BadRequest();
        president.setPassword(password);
    }

    @Override
    public String getPresidentName() {
        return president.getName();
    }

    @Override
    public String getPresidentPassword() {
        return president.getPassword();
    }

}
