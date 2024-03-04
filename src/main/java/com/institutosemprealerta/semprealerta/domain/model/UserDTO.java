package com.institutosemprealerta.semprealerta.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Address;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.Contact;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.User;
import com.institutosemprealerta.semprealerta.infrastructure.entity.user.UserRoles;
import com.institutosemprealerta.semprealerta.utils.DateManipulation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record UserDTO(
        @NotBlank
        @Schema(description = "Nome do usuário", example = "Matheus Victor")
        String name,
        @Email
        @Schema(description = "Email do usuário", example = "muryllo@gg.com")
        String email,
        @NotBlank
        @Schema(description = "Senha do usuário", example = "123456")
        String password,
        @NotBlank
        @Schema(description = "Telefone do usuário", example = "123456")
        String phone,
        @Schema(description = "Gênero do usuário", example = "Masculino")
        String gender,
        @PastOrPresent
        @Schema(description = "Data de nascimento do usuário", example = "1999-12-12")
        LocalDate birthDate,
        @NotNull
        @Schema(description = "Papel do usuário", example = "ADMIN")
        UserRoles roles,
        @Schema(description = "Rua do usuário", example = "Rua 1")
        String street,
        @Schema(description = "Número da casa do usuário", example = "123")
        String number,
        @Schema(description = "Cidade do usuário", example = "Igarassu e Lima")
        String city,
        @Schema(description = "CEP do usuário", example = "123456")
        String zipCode

) {
    public User toDomain() {
        //LocalDate birth = DateManipulation.stringToLocalDate(birthDate);
        Contact contact = new Contact(
                email,
                phone
        );

        Address address = new Address(
                street,
                number,
                city,
                zipCode
        );
        return new User(
                name,
                password,
                gender,
                birthDate,
                roles,
                contact,
                address
        );
    }
}
