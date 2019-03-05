package br.gov.inmetro.beacon.input.randomness.entropy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Entropies extends  JpaRepository<Entropy, Long>, IEntropyQueries {
    List<Entropy> findBySentOrderById(boolean sent);
}
