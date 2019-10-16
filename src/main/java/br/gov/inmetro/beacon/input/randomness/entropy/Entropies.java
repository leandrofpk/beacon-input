package br.gov.inmetro.beacon.input.randomness.entropy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Entropies extends  JpaRepository<Entropy, Long>, IEntropyQueries {
    List<Entropy> findBySentOrderById(boolean sent);
}
