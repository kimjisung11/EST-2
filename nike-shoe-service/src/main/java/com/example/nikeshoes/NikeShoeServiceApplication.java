
package com.example.nikeshoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.nikeshoes.domain.entity.Shoe;
import com.example.nikeshoes.domain.entity.User;
import com.example.nikeshoes.domain.repository.ShoeRepository;
import com.example.nikeshoes.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class NikeShoeServiceApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(NikeShoeServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(ShoeRepository shoeRepo, UserRepository userRepo) {
        return args -> {
            // 기본 신발 데이터
            shoeRepo.save(Shoe.builder()
                .name("Nike Air Max")
                .price(169000)
                .build());

            // 관리자 계정 (id: admin / password: admin)
            if (userRepo.findByUsername("admin").isEmpty()) {
                userRepo.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role("ADMIN")
                    .build());
            }
        };
    }
}
