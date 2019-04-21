package vokra.vokraapp.cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface CatRepository extends JpaRepository<Cat, String>
{
    @Override
    @GetMapping("/cats")
    List<Cat> findAll();
}
