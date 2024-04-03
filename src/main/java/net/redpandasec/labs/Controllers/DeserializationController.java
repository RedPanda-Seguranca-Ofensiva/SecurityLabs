package net.redpandasec.labs.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import net.redpandasec.labs.Classes.*; // Evil classes must be imported
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Base64;

@RequestMapping("/")
@Controller
public class DeserializationController {
    @GetMapping("/deser")
    public String deser() {
        return "deser";
    }

    @GetMapping("/deser1")
    public String deser1(@CookieValue(value = "blogEntry", required = false) String encodedBlog, Model model) throws Exception {
        if(encodedBlog == null)
        {
           return "deser1";
        }

        // Decode b64 cookie
        byte[] b64cookie = Base64.getDecoder().decode(encodedBlog);

        // Read Blog object
        ByteArrayInputStream byteArray = new ByteArrayInputStream(b64cookie);
        ObjectInputStream deser = new ObjectInputStream(byteArray);
        Blog blogentry = (Blog) deser.readObject();
        deser.close();

        model.addAttribute("title", blogentry.getTitle());
        model.addAttribute("body", blogentry.getBody());

        return "deser1";
    }

    @PostMapping("/deser1")
    public String deser1(HttpServletResponse response, @RequestParam(name="title", required=true) String title,
                         @RequestParam(name="body", required=true) String body,
                         Model model) throws IOException {

        // Create a new blog object
        Blog blogentry = new Blog(title, body);

        model.addAttribute("title", blogentry.getTitle());
        model.addAttribute("body", blogentry.getBody());

        // Create a new ByteArray and ObjectOutputStream
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream ser = new ObjectOutputStream(byteArray);

        // Write blog object to ByteArray
        ser.writeObject(blogentry);
        ser.flush();
        ser.close();

        // Create a base64 cookie from bytesArray
        byte[] b64cookie = Base64.getEncoder().encode(byteArray.toByteArray());
        Cookie cookie = new Cookie("blogEntry", new String(b64cookie));
        response.addCookie(cookie);

        return "deser1";
    }


    @GetMapping("/deser2")
    public String deser2(@CookieValue(value = "blogEntry2", required = false) String encodedBlog, Model model) throws Exception {
        if(encodedBlog == null)
        {
            return "deser2";
        }

        // Decode b64 cookie
        byte[] b64cookie = Base64.getDecoder().decode(encodedBlog);

        // Read Blog object
        ByteArrayInputStream byteArray = new ByteArrayInputStream(b64cookie);
        ObjectInputStream deser = new ObjectInputStream(byteArray);

        // Input filter, you cannot call Evil1 anymore
        ObjectInputFilter intFilter = ObjectInputFilter.rejectFilter(cl -> cl.equals(Evil1.class), ObjectInputFilter.Status.ALLOWED);
        deser.setObjectInputFilter(intFilter);

        Blog blogentry = (Blog) deser.readObject();
        deser.close();

        model.addAttribute("title", blogentry.getTitle());
        model.addAttribute("body", blogentry.getBody());

        return "deser2";
    }

    @PostMapping("/deser2")
    public String deser2(HttpServletResponse response, @RequestParam(name="title", required=true) String title,
                         @RequestParam(name="body", required=true) String body,
                         Model model) throws IOException {

        // Create a new blog object
        Blog blogentry = new Blog(title, body);

        model.addAttribute("title", blogentry.getTitle());
        model.addAttribute("body", blogentry.getBody());

        // Create a new ByteArray and ObjectOutputStream
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream ser = new ObjectOutputStream(byteArray);

        // Write blog object to ByteArray
        ser.writeObject(blogentry);
        ser.flush();
        ser.close();

        // Create a base64 cookie from bytesArray
        byte[] b64cookie = Base64.getEncoder().encode(byteArray.toByteArray());
        Cookie cookie = new Cookie("blogEntry2", new String(b64cookie));
        response.addCookie(cookie);

        return "deser2";
    }

    @GetMapping("/deser3")
    public String deser3(@CookieValue(value = "blogEntry3", required = false) String encodedBlog, Model model) throws Exception {
        if(encodedBlog == null)
        {
            return "deser3";
        }

        // Decode b64 cookie
        byte[] b64cookie = Base64.getDecoder().decode(encodedBlog);

        // Read Blog object
        ByteArrayInputStream byteArray = new ByteArrayInputStream(b64cookie);
        ObjectInputStream deser = new ObjectInputStream(byteArray);

        // Input filter, you cannot call Evil1 or Gadget2 anymore
        ObjectInputFilter intFilter = ObjectInputFilter.rejectFilter((cl) -> {
            if(cl.equals(Evil1.class)){
                return true;
            }
            if(cl.equals(Gadget2.class)){
                return true;
            }
            return false;
        }, ObjectInputFilter.Status.ALLOWED);
        deser.setObjectInputFilter(intFilter);

        // Deserialization of Object
        Blog blogentry = (Blog) deser.readObject();
        deser.close();

        model.addAttribute("title", blogentry.getTitle());
        model.addAttribute("body", blogentry.getBody());

        return "deser3";
    }

    @PostMapping("/deser3")
    public String deser3(HttpServletResponse response, @RequestParam(name="title", required=true) String title,
                         @RequestParam(name="body", required=true) String body,
                         Model model) throws IOException {

        // Create a new blog object
        Blog blogentry = new Blog(title, body);

        model.addAttribute("title", blogentry.getTitle());
        model.addAttribute("body", blogentry.getBody());

        // Create a new ByteArray and ObjectOutputStream
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream ser = new ObjectOutputStream(byteArray);

        // Write blog object to ByteArray
        ser.writeObject(blogentry);
        ser.flush();
        ser.close();

        // Create a base64 cookie from bytesArray
        byte[] b64cookie = Base64.getEncoder().encode(byteArray.toByteArray());
        Cookie cookie = new Cookie("blogEntry3", new String(b64cookie));
        response.addCookie(cookie);

        return "deser3";
    }
}
