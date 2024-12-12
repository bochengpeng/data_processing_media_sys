//package com.netflix.api.netflix.controllers;
//
//import com.netflix.api.netflix.dto.AuthResponseDTO;
//import com.netflix.api.netflix.dto.LoginDto;
//import com.netflix.api.netflix.dto.RegisterDto;
//import com.netflix.api.netflix.models.Role;
//import com.netflix.api.netflix.models.User;
//import com.netflix.api.netflix.repository.RoleRepository;
//import com.netflix.api.netflix.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JWTGenerator jwtGenerator;
//
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager,
//                          UserRepository userRepository,
//                          RoleRepository roleRepository,
//                          PasswordEncoder passwordEncoder,
//                          JWTGenerator jwtGenerator) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtGenerator = jwtGenerator;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getEmail(), // Changed from username to email
//                        loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
//        // Check if email already exists
//        if (userRepository.existsByEmail(registerDto.getEmail())) {
//            return new ResponseEntity<>("Email is already in use!", HttpStatus.BAD_REQUEST);
//        }
//
//        User user = new User();
//        user.setEmail(registerDto.getEmail()); // Set email instead of username
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        user.setActivated(false); // Set initial activation state
//
//        Role role = roleRepository.findByName("USER")
//                .orElseThrow(() -> new RuntimeException("Default USER role not found"));
//        user.setRoles(Collections.singletonList(role));
//
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
//    }
//}