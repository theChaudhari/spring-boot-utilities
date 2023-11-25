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
        File jsonFile = new File("src/test/java/resources/customer.json");

        URL inputJsonUrl = jsonFile.toURI().toURL();

        //provide in which directory you want to create package.
        File outputJavaClassDirectory = new File("src/main/java/");
        //provide deserved packageName.

        String packageName = "com.springboot.utilities.pojo";
        //If You have embedded classes , provide deserved class name for final class orElse provided name will be final class name.

        String javaClassName = "Customer";
        convertJsonToJavaClass(inputJsonUrl, outputJavaClassDirectory, packageName, javaClassName);

    }

    public void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName) throws IOException {
        JCodeModel jcodeModel = new JCodeModel();

        GenerationConfig config = new DefaultGenerationConfig() {

            //You can simply return true if you want one of the property..
            @Override
            public boolean isGenerateBuilders() {
                return false;
            }

            @Override
            public boolean isIncludeGetters() {
                return false;
            }

            @Override
            public boolean isIncludeSetters() {
                return false;
            }

            @Override
            public boolean isIncludeAdditionalProperties() {
                return false;
            }

            @Override
            public boolean isIncludeToString() {
                return false;
            }

            @Override
            public boolean isIncludeHashcodeAndEquals() {
                return false;
            }

            @Override
            public boolean isIncludeGeneratedAnnotation() {
                return false;
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
