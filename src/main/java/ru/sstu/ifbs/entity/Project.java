package ru.sstu.ifbs.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JmixEntity
@Table(name = "GWF_PROJECT", indexes = {
        @Index(name = "IDX_PROJECT_GROUP_ID", columnList = "GROUP_ID")
})
@Entity(name = "gwf_Project")
public class Project extends DefaultNamedEntity {

    @JoinTable(name = "GWF_PROJECT_IMPACT_SOURCE_LINK",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "IMPACT_SOURCE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<ImpactSource> impactSources = new ArrayList<>();

    @JoinTable(name = "GWF_PROJECT_IMPACT_TARGET_LINK",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "IMPACT_TARGET_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<ImpactTarget> impactTargets = new ArrayList<>();

    @NotNull
    @JoinColumn(name = "GROUP_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Group group;

    @OnDelete(DeletePolicy.CASCADE)
    @Composition
    @OneToMany(mappedBy = "project")
    private List<ActualThreat> actualThreats = new ArrayList<>();

    public List<ImpactSource> getImpactSources() {
        return impactSources;
    }

    public void setImpactSources(List<ImpactSource> impactSources) {
        this.impactSources = impactSources;
    }

    public List<ImpactTarget> getImpactTargets() {
        return impactTargets;
    }

    public void setImpactTargets(List<ImpactTarget> impactTargets) {
        this.impactTargets = impactTargets;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<ActualThreat> getActualThreats() {
        return actualThreats;
    }

    public void setActualThreats(List<ActualThreat> actualThreats) {
        this.actualThreats = actualThreats;
    }
}