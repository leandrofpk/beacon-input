package br.gov.inmetro.beacon.input.entropy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Entropies extends  JpaRepository<Entropy, Long>, IEntropyQueries {
}
