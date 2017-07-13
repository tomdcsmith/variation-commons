/*
 * Copyright 2017 EMBL - European Bioinformatics Institute
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
package uk.ac.ebi.eva.commons.mongodb.services;

import uk.ac.ebi.eva.commons.core.models.IAnnotationMetadata;
import uk.ac.ebi.eva.commons.mongodb.entities.AnnotationMetadataMongo;

import java.util.List;

public class AnnotationMetadataNotFoundException extends Exception {

    public AnnotationMetadataNotFoundException() {
        super("No results returned from annotation metadata collection.");
    }

    public AnnotationMetadataNotFoundException(List<AnnotationMetadataMongo> annotationMetadataList,
                                               IAnnotationMetadata specifiedAnnotationMetadata) {
        super(buildRepositoryString(annotationMetadataList, specifiedAnnotationMetadata));
    }

    private static String buildRepositoryString(List<AnnotationMetadataMongo> annotationMetadataList,
                                                IAnnotationMetadata specifiedAnnotationMetadata) {
        return "Specified annotation metadata version (" + specifiedAnnotationMetadata.toString() + ") not found. " +
                "\nAvailable annotation metadata versions:\n" + annotationMetadataList.toString();
    }
}
