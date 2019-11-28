package org.apache.tomcat.util.digester;

import org.junit.Test;
import org.testng.Assert;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDigester {

    @Test
    public void testParse() throws IOException, SAXException {
        Digester digester = new Digester();
        digester.setClassLoader(TestDigester.class.getClassLoader());
        digester.setValidating(false);
        digester.setRulesValidation(true);
        // create department object
        digester.addObjectCreate("department", "org.apache.tomcat.util.digester.TestDigester$Department");
        digester.addSetProperties("department");
        digester.addObjectCreate("department/user","org.apache.tomcat.util.digester.TestDigester$User");
        // use setter process department/user's attribute
        digester.addSetProperties("department/user");
        // call addUser
        digester.addSetNext("department/user", "addUser","org.apache.tomcat.util.digester.TestDigester$User");
        digester.addCallMethod("department/extension","putExtension",2);
        digester.addCallParam("department/extension/property-name",0);
        digester.addCallParam("department/extension/property-value",1);

        Department department = (Department) digester.parse(TestDigester.class.getResourceAsStream("./test.xml"));
        Assert.assertEquals(department.getName(),"department001");
        Assert.assertEquals(department.getCode(),"deptcode001");
    }

   public static class User {
        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    // if not static will throw internal Class <init> error
    // because of normal internal class need a external call as constructor argument
    public static class Department {
        private String name;
        private String code;
        private Map<String,String> extension = new HashMap<>();
        private List<User> users = new ArrayList<>();

        public void addUser(User user) {
            this.users.add(user);
        }

        public void putExtension(String name, String value) {
            this.extension.put(name,value);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
