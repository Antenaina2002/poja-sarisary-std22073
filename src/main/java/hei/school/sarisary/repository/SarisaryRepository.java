package hei.school.sarisary.repository;
import hei.school.sarisary.repository.model.SarisaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SarisaryRepository extends JpaRepository <SarisaryModel, String> {
    SarisaryModel findAllById(String id);
}
