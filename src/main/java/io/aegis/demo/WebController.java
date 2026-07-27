package io.aegis.demo;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    private static final List<Map<String, Object>> ITEMS = List.of(
        Map.of("id", 1, "name", "Widget", "price", 19.99),
        Map.of("id", 2, "name", "Gadget", "price", 24.50),
        Map.of("id", 3, "name", "Sprocket", "price", 8.75)
    );

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "<h1>Aegis demo (Java)</h1><a href='/login'>Sign in</a>";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        model.addAttribute("email", principal.getName());
        model.addAttribute("items", ITEMS);
        return "dashboard";
    }

    @GetMapping("/api/items")
    @ResponseBody
    public Map<String, Object> items() {
        return Map.of("items", ITEMS);
    }

    @GetMapping("/healthz")
    @ResponseBody
    public Map<String, String> healthz() {
        return Map.of("status", "ok");
    }
}
