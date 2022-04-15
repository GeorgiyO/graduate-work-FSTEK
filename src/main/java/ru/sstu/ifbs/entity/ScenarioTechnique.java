package ru.sstu.ifbs.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "GWF_SCENARIO_TECHNIQUE", indexes = {
        @Index(name = "IDX_SCENARIOTECHNIQUE", columnList = "THREAT_SCENARIO_ID")
})
@Entity(name = "gwf_ScenarioTechnique")
public class ScenarioTechnique {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "THREAT_SCENARIO_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ThreatScenario threatScenario;

    @InstanceName
    @NotNull
    @Column(name = "VALUE_", nullable = false)
    private String value;

    @Composition
    @OneToMany(mappedBy = "technique")
    private List<ScenarioTactic> tactics;

    public List<ScenarioTactic> getTactics() {
        return tactics;
    }

    public void setTactics(List<ScenarioTactic> tactics) {
        this.tactics = tactics;
    }

    public Technique getValue() {
        return value == null ? null : Technique.fromId(value);
    }

    public void setValue(Technique value) {
        this.value = value == null ? null : value.getId();
    }

    public ThreatScenario getThreatScenario() {
        return threatScenario;
    }

    public void setThreatScenario(ThreatScenario threatScenario) {
        this.threatScenario = threatScenario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}