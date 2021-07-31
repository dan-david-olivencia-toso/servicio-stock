package repository;


import domain.MovimientoStock;
import domain.Provision;
import frsf.isi.dan.InMemoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProvisionRepository extends InMemoryRepository<Provision> {

    @Override
    public Integer getId(Provision provision) {
        return provision.getId();
    }

    @Override
    public void setId(Provision provision, Integer integer) {
        provision.setId(integer);
    }
}
