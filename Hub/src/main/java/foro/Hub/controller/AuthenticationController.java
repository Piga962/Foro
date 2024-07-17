package foro.Hub.controller;

import foro.Hub.domain.usuarios.DatosAutenticacionUsuario;
import foro.Hub.domain.usuarios.Usuario;
import foro.Hub.infra.security.DatosJWTToken;
import foro.Hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        try {
            System.out.println("Attempting to authenticate user: " + datosAutenticacionUsuario);
            Authentication requestToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.password());
            System.out.println("Authentication token created: " + requestToken);

            Authentication resultToken = authenticationManager.authenticate(requestToken);
            System.out.println("User authenticated: " + resultToken);

            String JWTtoken = tokenService.generarToken((Usuario) resultToken.getPrincipal());
            System.out.println("JWT Token generated: " + JWTtoken);

            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

