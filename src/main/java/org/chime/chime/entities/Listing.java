package org.chime.chime.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private BigDecimal buyoutPrice;

    @Column(nullable = false)
    private BigDecimal startingPrice;

    private BigDecimal currentPrice;

    private BigDecimal finalPrice;

    private List<BigDecimal> bidHistory;

    @Column(name = "listing_end_time", nullable = false)
    private LocalDateTime listingEndTime;


    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userprofile_id", nullable = false)
    private UserProfile userProfile;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
