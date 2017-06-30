/*
 * Copyright 2015-2017 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.eva.commons.mongodb.entities.subdocuments;

import org.springframework.data.mongodb.core.mapping.Field;
import uk.ac.ebi.eva.commons.core.models.IConsequenceType;

import java.util.HashSet;
import java.util.Set;

/**
 * Mongo database representation of a consequence type in a genomic variant annotation.
 */
public class ConsequenceTypeMongo implements IConsequenceType {

    private static final String GENE_NAME_FIELD = "gn";

    private static final String ENSEMBL_GENE_ID_FIELD = "ensg";

    private static final String ENSEMBL_TRANSCRIPT_ID_FIELD = "enst";

    private static final String STRAND_FIELD = "strand";

    private static final String BIOTYPE_FIELD = "bt";

    private static final String C_DNA_POSITION_FIELD = "cDnaPos";

    private static final String CDS_POSITION_FIELD = "cdsPos";

    private static final String AA_POSITION_FIELD = "aaPos";

    private static final String AA_CHANGE_FIELD = "aaChange";

    private static final String CODON_FIELD = "codon";

    public static final String SIFT_FIELD = "sift";

    public static final String POLYPHEN_FIELD = "polyphen";

    public static final String SO_ACCESSION_FIELD = "so";

    private static final String RELATIVE_POS_FIELD = "relPos";

    @Field(value = GENE_NAME_FIELD)
    private String geneName;

    @Field(value = ENSEMBL_GENE_ID_FIELD)
    private String ensemblGeneId;

    @Field(value = ENSEMBL_TRANSCRIPT_ID_FIELD)
    private String ensemblTranscriptId;

    @Field(value = STRAND_FIELD)
    private String strand;

    @Field(value = BIOTYPE_FIELD)
    private String biotype;

    @Field(value = C_DNA_POSITION_FIELD)
    private Integer cDnaPosition;

    @Field(value = CDS_POSITION_FIELD)
    private Integer cdsPosition;

    @Field(value = AA_POSITION_FIELD)
    private Integer aaPosition;

    @Field(value = AA_CHANGE_FIELD)
    private String aaChange;

    @Field(value = CODON_FIELD)
    private String codon;

    @Field(value = SIFT_FIELD)
    private ScoreMongo sift;

    @Field(value = POLYPHEN_FIELD)
    private ScoreMongo polyphen;

    @Field(value = SO_ACCESSION_FIELD)
    private Set<Integer> soAccessions;

    @Field(value = RELATIVE_POS_FIELD)
    private Integer relativePosition;

    ConsequenceTypeMongo() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public ConsequenceTypeMongo(String geneName, String ensemblGeneId, String ensemblTranscriptId, String strand,
                                String biotype, Integer cDnaPosition, Integer cdsPosition, Integer aaPosition,
                                String aaChange, String codon, ScoreMongo sift, ScoreMongo polyphen, Set<Integer> soAccessions,
                                Integer relativePosition) {
        this.geneName = geneName;
        this.ensemblGeneId = ensemblGeneId;
        this.ensemblTranscriptId = ensemblTranscriptId;
        this.strand = strand;
        this.biotype = biotype;
        this.cDnaPosition = cDnaPosition;
        this.cdsPosition = cdsPosition;
        this.aaPosition = aaPosition;
        this.aaChange = aaChange;
        this.codon = codon;
        this.sift = sift;
        this.polyphen = polyphen;
        this.soAccessions = new HashSet<>();
        if (soAccessions != null && !soAccessions.isEmpty()) {
            this.soAccessions.addAll(soAccessions);
        }
        this.relativePosition = relativePosition;
    }

    public ConsequenceTypeMongo(IConsequenceType consequenceType) {
        this(
                consequenceType.getGeneName(),
                consequenceType.getEnsemblGeneId(),
                consequenceType.getEnsemblTranscriptId(),
                consequenceType.getStrand(),
                consequenceType.getBiotype(),
                consequenceType.getcDnaPosition(),
                consequenceType.getCdsPosition(),
                consequenceType.getAaPosition(),
                consequenceType.getAaChange(),
                consequenceType.getCodon(),
                new ScoreMongo(consequenceType.getSift()),
                new ScoreMongo(consequenceType.getPolyphen()),
                consequenceType.getSoAccessions(),
                consequenceType.getRelativePosition()
        );
    }

    public void setEnsemblTranscriptId(String ensemblTranscriptId) {
        this.ensemblTranscriptId = ensemblTranscriptId;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public void setEnsemblGeneId(String ensemblGeneId) {
        this.ensemblGeneId = ensemblGeneId;
    }

    public void setRelativePosition(Integer relativePosition) {
        this.relativePosition = relativePosition;
    }

    public void setCodon(String codon) {
        this.codon = codon;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public void setBiotype(String biotype) {
        this.biotype = biotype;
    }

    public void setcDnaPosition(Integer cDnaPosition) {
        this.cDnaPosition = cDnaPosition;
    }

    public void setCdsPosition(Integer cdsPosition) {
        this.cdsPosition = cdsPosition;
    }

    public void setAaPosition(Integer aaPosition) {
        this.aaPosition = aaPosition;
    }

    public void setAaChange(String aaChange) {
        this.aaChange = aaChange;
    }

    @Override
    public String getGeneName() {
        return geneName;
    }

    @Override
    public String getEnsemblGeneId() {
        return ensemblGeneId;
    }

    @Override
    public String getEnsemblTranscriptId() {
        return ensemblTranscriptId;
    }

    @Override
    public Integer getRelativePosition() {
        return relativePosition;
    }

    @Override
    public String getCodon() {
        return codon;
    }

    @Override
    public String getStrand() {
        return strand;
    }

    @Override
    public String getBiotype() {
        return biotype;
    }

    @Override
    public Integer getcDnaPosition() {
        return cDnaPosition;
    }

    @Override
    public Integer getCdsPosition() {
        return cdsPosition;
    }

    @Override
    public Integer getAaPosition() {
        return aaPosition;
    }

    @Override
    public String getAaChange() {
        return aaChange;
    }

    @Override
    public Set<Integer> getSoAccessions() {
        return soAccessions;
    }

    public void setSoAccessions(Set<Integer> soAccessions) {
        this.soAccessions = soAccessions;
    }

    @Override
    public ScoreMongo getSift() {
        return sift;
    }

    public void setSift(ScoreMongo sift) {
        this.sift = sift;
    }

    @Override
    public ScoreMongo getPolyphen() {
        return polyphen;
    }

    public void setPolyphen(ScoreMongo polyphen) {
        this.polyphen = polyphen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsequenceTypeMongo that = (ConsequenceTypeMongo) o;

        if (geneName != null ? !geneName.equals(that.geneName) : that.geneName != null) return false;
        if (ensemblGeneId != null ? !ensemblGeneId.equals(that.ensemblGeneId) : that.ensemblGeneId != null)
            return false;
        if (ensemblTranscriptId != null ? !ensemblTranscriptId
                .equals(that.ensemblTranscriptId) : that.ensemblTranscriptId != null) return false;
        if (strand != null ? !strand.equals(that.strand) : that.strand != null) return false;
        if (biotype != null ? !biotype.equals(that.biotype) : that.biotype != null) return false;
        if (cDnaPosition != null ? !cDnaPosition.equals(that.cDnaPosition) : that.cDnaPosition != null) return false;
        if (cdsPosition != null ? !cdsPosition.equals(that.cdsPosition) : that.cdsPosition != null) return false;
        if (aaPosition != null ? !aaPosition.equals(that.aaPosition) : that.aaPosition != null) return false;
        if (aaChange != null ? !aaChange.equals(that.aaChange) : that.aaChange != null) return false;
        if (codon != null ? !codon.equals(that.codon) : that.codon != null) return false;
        if (sift != null ? !sift.equals(that.sift) : that.sift != null) return false;
        if (polyphen != null ? !polyphen.equals(that.polyphen) : that.polyphen != null) return false;
        if (soAccessions != null ? !soAccessions.equals(that.soAccessions) : that.soAccessions != null) return false;
        return relativePosition != null ? relativePosition
                .equals(that.relativePosition) : that.relativePosition == null;
    }

    @Override
    public int hashCode() {
        int result = geneName != null ? geneName.hashCode() : 0;
        result = 31 * result + (ensemblGeneId != null ? ensemblGeneId.hashCode() : 0);
        result = 31 * result + (ensemblTranscriptId != null ? ensemblTranscriptId.hashCode() : 0);
        result = 31 * result + (strand != null ? strand.hashCode() : 0);
        result = 31 * result + (biotype != null ? biotype.hashCode() : 0);
        result = 31 * result + (cDnaPosition != null ? cDnaPosition.hashCode() : 0);
        result = 31 * result + (cdsPosition != null ? cdsPosition.hashCode() : 0);
        result = 31 * result + (aaPosition != null ? aaPosition.hashCode() : 0);
        result = 31 * result + (aaChange != null ? aaChange.hashCode() : 0);
        result = 31 * result + (codon != null ? codon.hashCode() : 0);
        result = 31 * result + (sift != null ? sift.hashCode() : 0);
        result = 31 * result + (polyphen != null ? polyphen.hashCode() : 0);
        result = 31 * result + (soAccessions != null ? soAccessions.hashCode() : 0);
        result = 31 * result + (relativePosition != null ? relativePosition.hashCode() : 0);
        return result;
    }
}
