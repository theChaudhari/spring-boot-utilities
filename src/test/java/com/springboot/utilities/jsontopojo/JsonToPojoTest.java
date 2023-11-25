package com.springboot.utilities.jsontopojo;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@ExtendWith(MockitoExtension.class)
public class JsonToPojoTest {

    @Test
    void jsonToPojo() throws IOException {
        File jsonFile = new File("src/test/java/resources/car.json");
        URL inputJsonUrl = jsonFile.toURI().toURL();
        //provide in which directory you want to create package.
        File outputJavaClassDirectory = new File("src/main/java/com/springboot/utilities");
        //provide deserved packageName.
        String packageName = "pojo";
        //If You have embedded classes , provide deserved class name for final class orElse provided name will be final class name.
        String javaClassName = "Car";
        convertJsonToJavaClass(inputJsonUrl,outputJavaClassDirectory,packageName,javaClassName);

    }

    public void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName)
            throws IOException {
        JCodeModel jcodeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }
        };

        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

        jcodeModel.build(outputJavaClassDirectory);
    }
}
