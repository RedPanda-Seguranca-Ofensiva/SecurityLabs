package net.redpandasec.labs.Controllers;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
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

@RequestMapping("/")
@Controller
public class SpelController {
    @GetMapping("/spel")
    public String spel() {
        return "spel";
    }

    @GetMapping("/spel1")
    public String spel1() {
        return "spel1";
    }

    @PostMapping("/spel1")
    public String spel1(@RequestParam(name="expression", required=true) String expression, Model model){


        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        model.addAttribute("name", "You're " + exp.getValue());
        return "spel1";
    }

    @GetMapping("/spel2")
    public String spel2() {
        return "spel2";
    }

    @PostMapping("/spel2")
    public String spel2(@RequestParam(name="expression", required=true) String expression, Model model){

        if(expression.contains("Runtime")){
            model.addAttribute("name", "Runtime detected!!!");
            return "spel2";
        }
        if(expression.contains("ProcessBuilder")){
            model.addAttribute("name", "ProcessBuilder detected!!!");
            return "spel2";
        }

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        model.addAttribute("name", "You're " + exp.getValue());
        return "spel2";
    }
}
