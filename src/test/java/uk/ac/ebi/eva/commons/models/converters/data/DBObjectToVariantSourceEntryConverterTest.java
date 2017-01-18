/*
 * Copyright 2015-2016 OpenCB
 * Copyright 2017 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.eva.commons.models.converters.data;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;
import org.opencb.biodata.models.variant.VariantSourceEntry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DBObjectToVariantSourceEntryConverterTest {

    private VariantSourceEntry file;
    private BasicDBObject mongoFile;
    private DBObject mongoFileWithIds;

    private Map<String, Integer> sampleIds;
    private List<String> sampleNames;

    @Before
    public void setUp() {
        // Java native class
        file = new VariantSourceEntry("f1", "s1");
        file.addAttribute("QUAL", "0.01");
        file.addAttribute("AN", "2");
        file.addAttribute("MAX.PROC", "2");
        file.setFormat("GT");

        Map<String, String> na001 = new HashMap<>();
        na001.put("GT", "0/0");
        file.addSampleData("NA001", na001);
        Map<String, String> na002 = new HashMap<>();
        na002.put("GT", "0/1");
        file.addSampleData("NA002", na002);
        Map<String, String> na003 = new HashMap<>();
        na003.put("GT", "1/1");
        file.addSampleData("NA003", na003);

        // MongoDB object
        mongoFile = new BasicDBObject(uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter.FILEID_FIELD, file.getFileId())
                .append(uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter.STUDYID_FIELD, file.getStudyId());
        mongoFile.append(uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter.ATTRIBUTES_FIELD,
                         new BasicDBObject("QUAL", "0.01").append("AN", "2")
                                                          .append("MAX" + uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter.CHARACTER_TO_REPLACE_DOTS + "PROC", "2"));
        mongoFile.append(uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter.FORMAT_FIELD, file.getFormat());
        BasicDBObject genotypeCodes = new BasicDBObject();
        genotypeCodes.append("def", "0/0");
        genotypeCodes.append("0/1", Arrays.asList(1));
        genotypeCodes.append("1/1", Arrays.asList(2));
        mongoFile.append(uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter.SAMPLES_FIELD, genotypeCodes);

        sampleIds = new HashMap<>();
        sampleIds.put("NA001", 15);
        sampleIds.put("NA002", 25);
        sampleIds.put("NA003", 35);

        sampleNames = Lists.newArrayList("NA001", "NA002", "NA003");


        mongoFileWithIds = new BasicDBObject((this.mongoFile.toMap()));
        mongoFileWithIds.put("samp", new BasicDBObject());
        ((DBObject) mongoFileWithIds.get("samp")).put("def", "0/0");
        ((DBObject) mongoFileWithIds.get("samp")).put("0/1", Arrays.asList(25));
        ((DBObject) mongoFileWithIds.get("samp")).put("1/1", Arrays.asList(35));
    }

    @Test
    public void testConvertToDataModelTypeWithoutStats() {
        file.getSamplesData().clear(); // TODO Samples can't be tested easily, needs a running Mongo instance
        List<String> sampleNames = null;

        // Test with no stats converter provided
        uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter converter = new uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter(
                VariantStorageManager.IncludeSrc.FULL, new DBObjectToSamplesConverter(sampleNames));
        VariantSourceEntry converted = converter.convert(mongoFile);
        assertEquals(file, converted);

        // Test with a stats converter provided but no stats object
        converter = new uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter(
                VariantStorageManager.IncludeSrc.FULL, new DBObjectToSamplesConverter(sampleNames));
        converted = converter.convert(mongoFile);
        assertEquals(file, converted);
    }

    @Test
    public void testConvertToDataTypeWithoutStatsWithSampleIds() {
        uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter converter;
        DBObject convertedMongo;
        VariantSourceEntry convertedFile;


        // Test with no stats converter provided
        converter = new uk.ac.ebi.eva.commons.models.converters.data.DBObjectToVariantSourceEntryConverter(
                VariantStorageManager.IncludeSrc.FULL,
                new DBObjectToSamplesConverter(true, sampleIds)
        );
        convertedFile = converter.convert(mongoFileWithIds);
        assertEquals(file, convertedFile);

        // Test with a stats converter provided but no stats object
        converter = new DBObjectToVariantSourceEntryConverter(
                VariantStorageManager.IncludeSrc.FULL,
                new DBObjectToSamplesConverter(true, sampleIds)
        );
        convertedFile = converter.convert(mongoFileWithIds);
        assertEquals(file, convertedFile);
    }

}