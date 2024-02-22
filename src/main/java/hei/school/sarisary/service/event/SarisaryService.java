package hei.school.sarisary.service.event;

import hei.school.sarisary.repository.SarisaryRepository;
import hei.school.sarisary.repository.model.SarisaryModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SarisaryService {

    private final SarisaryRepository sarisaryRepository;

    public SarisaryService(SarisaryRepository sarisaryRepository) {
        this.sarisaryRepository = sarisaryRepository;
    }

    public List<SarisaryModel> findAll(){
        return sarisaryRepository.findAll();
    }
}