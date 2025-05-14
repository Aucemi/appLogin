package br.appLogin.appLogin.controller;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam("email") String email,
                                 @RequestParam("senha") String senha,
                                 Model model, HttpSession session) {
        Optional<Usuario> usuarioAutenticado = usuarioService.autenticarUsuario(email, senha);

        if (usuarioAutenticado.isPresent()) {
            session.setAttribute("usuarioLogadoId", usuarioAutenticado.get().getIdUsuario());
            return "redirect:/home";
        } else {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Long usuarioLogadoId = (Long) session.getAttribute("usuarioLogadoId");
        if (usuarioLogadoId != null) {
            return "home";
        } else {
            return "redirect:/login";
        }
    }
}