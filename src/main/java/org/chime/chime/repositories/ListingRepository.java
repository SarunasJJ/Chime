package org.chime.chime.repositories;

import org.chime.chime.entities.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {
    Optional<Listing> findById(UUID id);


}
