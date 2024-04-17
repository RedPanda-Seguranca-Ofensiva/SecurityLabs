package net.redpandasec.labs.Controllers;

import freemarker.core.Environment;
import freemarker.core.OptInTemplateClassResolver;
import freemarker.core.TemplateClassResolver;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.redpandasec.labs.Classes.Utils;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

@RequestMapping("/")
@Controller
public class SstiController {
    @GetMapping("/ssti")
    public String ssti() {
        return "ssti";
    }

    @GetMapping("/ssti1")
    public String ssti1(@RequestParam(name="name", required=true) String name, Model model)
            throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> data = new HashMap<>();

        String templateString = "Hello " + name;

        Template template = new Template("template", new StringReader(templateString), cfg);
        template.process(data, stringWriter);

        model.addAttribute("message", stringWriter.toString());
        return "ssti1";
    }

    @GetMapping("/ssti2")
    public String ssti2(@RequestParam(name="name", required=true) String name, Model model)
            throws IOException, TemplateException, ParserConfigurationException, SAXException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setNewBuiltinClassResolver(TemplateClassResolver.SAFER_RESOLVER);

        StringWriter stringWriter = new StringWriter();
        Map<String, Object> data = new HashMap<>();

        // XML file
        ClassPathResource cpr = new ClassPathResource("static/t.xml");
        InputStream inputStream = cpr.getInputStream();
        File xmlFile = File.createTempFile("xmlFile", ".xml");
        FileUtils.copyInputStreamToFile(inputStream, xmlFile);


        data.put("xml", freemarker.ext.dom.NodeModel.parse(xmlFile));

        String templateString = "Hello " + name;

        Template template = new Template("template", new StringReader(templateString), cfg);
        template.process(data, stringWriter);

        model.addAttribute("message", stringWriter.toString());
        return "ssti2";
    }

    @GetMapping("/ssti3")
    public String ssti3(@RequestParam(name="name", required=true) String name, Model model)
            throws IOException, TemplateException, ParserConfigurationException, SAXException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setNewBuiltinClassResolver(TemplateClassResolver.ALLOWS_NOTHING_RESOLVER);

        StringWriter stringWriter = new StringWriter();
        Map<String, Object> data = new HashMap<>();

        data.put("evil", new Utils());

        String templateString = "Hello " + name;

        Template template = new Template("template", new StringReader(templateString), cfg);
        template.process(data, stringWriter);

        model.addAttribute("message", stringWriter.toString());
        return "ssti3";
    }
}
