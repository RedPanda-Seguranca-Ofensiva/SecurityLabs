package net.redpandasec.labs.Controllers;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

@RequestMapping("/")
@Controller
public class XxeController {

    @GetMapping("/xxe")
    public String xxe(Model model){
        return "xxe";
    }

    @GetMapping("/xxe1")
    public String xxe1(Model model){
        return "xxe1";
    }

    @PostMapping("/xxe1")
    public String xxe1(@RequestParam(name="xml", required=true) String xml, Model model) throws IOException, JDOMException {

        InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(stream);
        Element rootElement = document.getRootElement();
        List<Element> childrenElement = rootElement.getChildren();
        Element note = childrenElement.get(0);

        try{
            model.addAttribute("from", note.getChild("from").getText());
            model.addAttribute("body", note.getChild("body").getText());
        } catch (Exception e) {
            model.addAttribute("from", "nobody");
            model.addAttribute("body", "none");
        }

        return "xxe1";
    }


    @GetMapping("/xxe2")
    public String xxe2(Model model){
        return "xxe2";
    }

    @PostMapping("/xxe2")
    public String xxe2(@RequestParam(name="xml", required=true) String xml, Model model) throws IOException, JDOMException {

        InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(stream);
        Element rootElement = document.getRootElement();
        List<Element> childrenElement = rootElement.getChildren();
        Element note = childrenElement.get(0);

        return "xxe2";
    }

    @GetMapping("/xxe3")
    public String xxe3(Model model){
        return "xxe3";
    }

    @PostMapping("/xxe3")
    public String xxe3(@RequestParam(name="xml", required=true) String xml, Model model) throws IOException, JDOMException {

        InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        SAXBuilder saxBuilder = new SAXBuilder();

        saxBuilder.setFeature("http://xml.org/sax/features/external-general-entities", false);
        saxBuilder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        saxBuilder.setExpandEntities(false);

        Document document = saxBuilder.build(stream);
        Element rootElement = document.getRootElement();
        List<Element> childrenElement = rootElement.getChildren();
        Element note = childrenElement.get(0);

        try{
            model.addAttribute("from", note.getChild("from").getText());
            model.addAttribute("body", note.getChild("body").getText());
        } catch (Exception e) {
            model.addAttribute("from", "nobody");
            model.addAttribute("body", "none");
        }

        return "xxe3";
    }

    @GetMapping("/xxe4")
    public String xxe4(Model model){
        return "xxe4";
    }

    @PostMapping("/xxe4")
    public String xxe4(@RequestParam(name="xml", required=true) String xml, Model model) throws IOException, JDOMException {

        if(xml.contains("DOCTYPE")){
            return "xxe4";
        }

        InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        SAXBuilder saxBuilder = new SAXBuilder();

        Document document = saxBuilder.build(stream);

        return "xxe4";
    }
}
