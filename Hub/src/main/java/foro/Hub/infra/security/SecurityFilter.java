package foro.Hub.infra.security;

import foro.Hub.domain.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request);
        System.out.println(request.getHeaderNames());
        var authHeader = request.getHeader("Authorization");
        System.out.println("xd1");
        System.out.println(authHeader);
        System.out.println(response);
        if(authHeader != null){
            var token = authHeader.replace("Bearer ", "");
            System.out.println("xd2");
            System.out.println(token);
            var subject = tokenService.getSubject(token);
            if(subject != null){
                var usuario = usuarioRepository.findByLogin(subject);
                System.out.println("xd3");
                System.out.println(usuario);
                var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        System.out.println("xd344");
        filterChain.doFilter(request,response);
        System.out.println("dddddd");
        System.out.println(filterChain);
    }
}
