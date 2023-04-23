package com.cscb845f89497.sqlinjection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private AccountDAO target;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("account", new AccountDTO());
        return "login";
    }

    @PostMapping("/")
    public String postLogin(@ModelAttribute AccountDTO dto, Model model) {
        // List<AccountDTO> dtos =
        // target.unsafeFindAccountsByUsernameAndPassword(dto.getUsername(),
        // dto.getPassword());
        List<AccountDTO> dtos = target.safeFindAccountsByUsernameAndPassword(dto.getUsername(),
                dto.getPassword());
        if (!dtos.isEmpty()) {
            return "redirect:/customerId/" + dtos.get(0).getCustomerId();
        } else {
            model.addAttribute("account", new AccountDTO());
            return "login-error";
        }
    }

    @GetMapping("/customerId/{id}")
    public String home(@PathVariable("id") String stdId, Model model) {
        // List<AccountDTO> dtos = target.unsafeFindAccountsByCustomerId(stdId);
        List<AccountDTO> dtos = target.safeFindAccountsByCustomerId(stdId);
        model.addAttribute("account", dtos.get(0));
        return "home";
    }

}
