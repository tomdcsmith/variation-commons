/*
 * Copyright 2015 EMBL - European Bioinformatics Institute
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

package embl.ebi.variation.commons.models.metadata;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by parce on 02/10/15.
 */
class FileGenerator {

    protected Set<File> files;
    protected Dataset dataset;

    protected FileGenerator(){}

    protected FileGenerator(Dataset dataset){
        this(dataset, new HashSet<File>());
    }

    protected FileGenerator(Dataset dataset, Set<File> files) {
        this.dataset = dataset;
        setFiles(files);
    }

    void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Set<File> getFiles() {
        return Collections.unmodifiableSet(files);
    }

    public void addFile(File file){
        this.files.add(file);
    }

//    public void removeStudy(Study study){
//        this.studies.remove(study);
//    }

    public void setFiles(Set<File> files) {
        this.files.clear();
        for (File f : files) {
            addFile(f);
        }
    }

    public Dataset getDataset() {
        return dataset;
    }
}
