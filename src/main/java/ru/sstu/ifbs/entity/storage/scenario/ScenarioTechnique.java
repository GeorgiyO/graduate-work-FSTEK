package ru.sstu.ifbs.entity.storage.scenario;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import ru.sstu.ifbs.entity.DefaultEntity;
import ru.sstu.ifbs.entity.storage.tactic.Technique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JmixEntity
@Table(name = "GWF_SCENARIO_TECHNIQUE", indexes = {
        @Index(name = "IDX_SCENARIOTECHNIQUE", columnList = "THREAT_SCENARIO_ID"),
        @Index(name = "IDX_SCENARIOTECHNIQUE", columnList = "TACTIC_ID")
})
@Entity(name = "gwf_ScenarioTechnique")
public class ScenarioTechnique extends DefaultEntity {

    @NotNull
    @JoinColumn(name = "VALUE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @InstanceName
    private Technique value;

    @JoinColumn(name = "TACTIC_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ScenarioTactic tactic;

    public void setValue(Technique value) {
        this.value = value;
    }

    public Technique getValue() {
        return value;
    }

    public ScenarioTactic getTactic() {
        return tactic;
    }

    public void setTactic(ScenarioTactic tactic) {
        this.tactic = tactic;
    }

}