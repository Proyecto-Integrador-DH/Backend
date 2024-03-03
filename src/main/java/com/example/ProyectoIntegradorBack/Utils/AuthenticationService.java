package com.example.ProyectoIntegradorBack.Utils;

import com.example.ProyectoIntegradorBack.Model.RolDTOUsuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {
    @Autowired
    private ClaveService claveService;
    public boolean getRolesFromToken(String token) {
        Key key = null;
        try{
            key = claveService.obtenerClavePublica();
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        Claims claims = null;
        claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        List<Map<String, Object>> rolesMap = (List<Map<String, Object>>) claims.get("roles");
        List<RolDTOUsuario> roles = rolesMap.stream()
                .map(map -> new RolDTOUsuario((Integer) map.get("id"), (String) map.get("nombre")))
                .collect(Collectors.toList());
        boolean tieneRolAdmin = false;
        for (RolDTOUsuario rol : roles) {
            if (rol.getNombre().equals("Administrador")) {
                tieneRolAdmin = true;
                break;
            }
        }
        return tieneRolAdmin;
    }

}
