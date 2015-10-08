package embl.ebi.variation.commons.models.metadata;

import java.util.Collections;
import java.util.Set;

/**
 * Created by tom on 08/10/15.
 */
class StudyComponent {

    private Set<Study> studies;

    public StudyComponent(Study study) {
        this.addStudy(study);
    }

    public Set<Study> getStudies() {
        return Collections.unmodifiableSet(studies);
    }

    public void removeStudy(Study study){
        studies.remove(study);
    }

    public void addStudy(Study study){
        studies.add(study);
    }
}
