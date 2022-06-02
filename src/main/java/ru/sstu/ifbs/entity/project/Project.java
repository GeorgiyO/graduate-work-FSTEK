package ru.sstu.ifbs.entity.project;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import ru.sstu.ifbs.entity.DefaultNamedEntity;
import ru.sstu.ifbs.entity.project.securityinfo.ProjectSecurityInfoType;
import ru.sstu.ifbs.entity.storage.ImpactSource;
import ru.sstu.ifbs.entity.storage.ImpactTarget;

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

    @OneToMany(mappedBy = "project")
    private List<ActualSecurityMeasure> actualMeasures;

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

    @Column(name = "SECURITY_INFO_TYPE", nullable = false)
    @NotNull
    private String securityInfoType = ProjectSecurityInfoType.GIS_SECURITY_INFO.getId();

    public ProjectSecurityInfoType getSecurityInfoType() {
        return securityInfoType == null ? null : ProjectSecurityInfoType.fromId(securityInfoType);
    }

    public void setSecurityInfoType(ProjectSecurityInfoType securityInfoType) {
        this.securityInfoType = securityInfoType == null ? null : securityInfoType.getId();
    }

    public List<ActualSecurityMeasure> getActualMeasures() {
        return actualMeasures;
    }

    public void setActualMeasures(List<ActualSecurityMeasure> actualMeasures) {
        this.actualMeasures = actualMeasures;
    }

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